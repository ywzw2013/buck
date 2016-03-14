/*
 * Copyright 2013-present Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.facebook.buck.cxx;

import com.facebook.buck.model.BuildTarget;
import com.facebook.buck.model.Flavor;
import com.facebook.buck.model.FlavorDomain;
import com.facebook.buck.model.Flavored;
import com.facebook.buck.parser.NoSuchBuildTargetException;
import com.facebook.buck.rules.BuildRule;
import com.facebook.buck.rules.BuildRuleParams;
import com.facebook.buck.rules.BuildRuleResolver;
import com.facebook.buck.rules.BuildRuleType;
import com.facebook.buck.rules.Description;
import com.facebook.buck.rules.ImplicitDepsInferringDescription;
import com.facebook.buck.rules.MetadataProvidingDescription;
import com.facebook.buck.rules.SourcePathResolver;
import com.facebook.buck.rules.TargetGraph;
import com.facebook.buck.rules.macros.MacroException;
import com.facebook.buck.util.HumanReadableException;
import com.facebook.infer.annotation.SuppressFieldNotInitialized;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import java.nio.file.Path;
import java.util.Set;

public class CxxBinaryDescription implements
    Description<CxxBinaryDescription.Arg>,
    Flavored,
    ImplicitDepsInferringDescription<CxxBinaryDescription.Arg>,
    MetadataProvidingDescription<CxxBinaryDescription.Arg> {

  public static final BuildRuleType TYPE = BuildRuleType.of("cxx_binary");

  private final InferBuckConfig inferBuckConfig;
  private final CxxPlatform defaultCxxPlatform;
  private final FlavorDomain<CxxPlatform> cxxPlatforms;
  private final CxxPreprocessMode preprocessMode;

  public CxxBinaryDescription(
      InferBuckConfig inferBuckConfig,
      CxxPlatform defaultCxxPlatform,
      FlavorDomain<CxxPlatform> cxxPlatforms,
      CxxPreprocessMode preprocessMode) {
    this.inferBuckConfig = inferBuckConfig;
    this.defaultCxxPlatform = defaultCxxPlatform;
    this.cxxPlatforms = cxxPlatforms;
    this.preprocessMode = preprocessMode;
  }

  /**
   * @return a {@link com.facebook.buck.cxx.HeaderSymlinkTree} for the headers of this C/C++ binary.
   */
  public static <A extends Arg> HeaderSymlinkTree createHeaderSymlinkTreeBuildRule(
      BuildRuleParams params,
      BuildRuleResolver resolver,
      CxxPlatform cxxPlatform,
      A args) {
    return CxxDescriptionEnhancer.createHeaderSymlinkTree(
        params,
        resolver,
        new SourcePathResolver(resolver),
        cxxPlatform,
        CxxDescriptionEnhancer.parseHeaders(
            params.getBuildTarget(),
            new SourcePathResolver(resolver),
            Optional.of(cxxPlatform),
            args),
        HeaderVisibility.PRIVATE);
  }

  @Override
  public Arg createUnpopulatedConstructorArg() {
    return new Arg();
  }

  @Override
  public <A extends Arg> BuildRule createBuildRule(
      TargetGraph targetGraph,
      BuildRuleParams params,
      BuildRuleResolver resolver,
      A args) throws NoSuchBuildTargetException {

    // Extract the platform from the flavor, falling back to the default platform if none are
    // found.
    ImmutableSet<Flavor> flavors = ImmutableSet.copyOf(params.getBuildTarget().getFlavors());
    CxxPlatform cxxPlatform = cxxPlatforms
        .getValue(flavors)
        .or(defaultCxxPlatform);
    if (flavors.contains(CxxDescriptionEnhancer.HEADER_SYMLINK_TREE_FLAVOR)) {
      flavors = ImmutableSet.copyOf(
          Sets.difference(
              flavors,
              ImmutableSet.of(CxxDescriptionEnhancer.HEADER_SYMLINK_TREE_FLAVOR)));
      BuildTarget target = BuildTarget
          .builder(params.getBuildTarget().getUnflavoredBuildTarget())
          .addAllFlavors(flavors)
          .build();
      BuildRuleParams typeParams =
          params.copyWithChanges(
              target,
              params.getDeclaredDeps(),
              params.getExtraDeps());

      return createHeaderSymlinkTreeBuildRule(
          typeParams,
          resolver,
          cxxPlatform,
          args);
    }

    SourcePathResolver pathResolver = new SourcePathResolver(resolver);

    if (flavors.contains(CxxCompilationDatabase.COMPILATION_DATABASE)) {
      CxxLinkAndCompileRules cxxLinkAndCompileRules = CxxDescriptionEnhancer
          .createBuildRulesForCxxBinaryDescriptionArg(
              params.withoutFlavor(CxxCompilationDatabase.COMPILATION_DATABASE),
              resolver,
              cxxPlatform,
              args,
              preprocessMode);
      return CxxCompilationDatabase.createCompilationDatabase(
          params,
          pathResolver,
          preprocessMode,
          cxxLinkAndCompileRules.compileRules);
    }

    if (flavors.contains(CxxCompilationDatabase.UBER_COMPILATION_DATABASE)) {
      return CxxDescriptionEnhancer.createUberCompilationDatabase(
          cxxPlatforms.getValue(flavors).isPresent() ?
              params :
              params.withFlavor(defaultCxxPlatform.getFlavor()),
          resolver);
    }

    if (flavors.contains(CxxInferEnhancer.InferFlavors.INFER.get())) {
      return CxxInferEnhancer.requireInferAnalyzeAndReportBuildRuleForCxxDescriptionArg(
          params,
          resolver,
          pathResolver,
          cxxPlatform,
          new CxxInferTools(inferBuckConfig),
          new CxxInferSourceFilter(inferBuckConfig),
          args);
    }

    if (flavors.contains(CxxInferEnhancer.InferFlavors.INFER_ANALYZE.get())) {
      return CxxInferEnhancer.requireInferAnalyzeBuildRuleForCxxDescriptionArg(
          params,
          resolver,
          pathResolver,
          cxxPlatform,
          new CxxInferTools(inferBuckConfig),
          new CxxInferSourceFilter(inferBuckConfig),
          args);
    }

    CxxLinkAndCompileRules cxxLinkAndCompileRules =
        CxxDescriptionEnhancer.createBuildRulesForCxxBinaryDescriptionArg(
            params,
            resolver,
            cxxPlatform,
            args,
            preprocessMode);

    // Return a CxxBinary rule as our representative in the action graph, rather than the CxxLink
    // rule above for a couple reasons:
    //  1) CxxBinary extends BinaryBuildRule whereas CxxLink does not, so the former can be used
    //     as executables for genrules.
    //  2) In some cases, users add dependencies from some rules onto other binary rules, typically
    //     if the binary is executed by some test or library code at test time.  These target graph
    //     deps should *not* become build time dependencies on the CxxLink step, otherwise we'd
    //     have to wait for the dependency binary to link before we could link the dependent binary.
    //     By using another BuildRule, we can keep the original target graph dependency tree while
    //     preventing it from affecting link parallelism.
    return new CxxBinary(
        params.appendExtraDeps(cxxLinkAndCompileRules.executable.getDeps(pathResolver)),
        resolver,
        pathResolver,
        cxxLinkAndCompileRules.cxxLink.getOutput(),
        cxxLinkAndCompileRules.cxxLink,
        cxxLinkAndCompileRules.executable,
        args.frameworks.get(),
        args.tests.get());
  }

  @Override
  public BuildRuleType getBuildRuleType() {
    return TYPE;
  }

  @Override
  public Iterable<BuildTarget> findDepsForTargetFromConstructorArgs(
      BuildTarget buildTarget,
      Function<Optional<String>, Path> cellRoots,
      Arg constructorArg) {
    ImmutableSet.Builder<BuildTarget> deps = ImmutableSet.builder();

    // Get any parse time deps from the C/C++ platforms.
    deps.addAll(
        CxxPlatforms.getParseTimeDeps(
            cxxPlatforms
                .getValue(buildTarget.getFlavors())
                .or(defaultCxxPlatform)));

    Iterable<Iterable<String>> macroStrings =
        ImmutableList.<Iterable<String>>builder()
            .add(constructorArg.linkerFlags.get())
            .addAll(constructorArg.platformLinkerFlags.get().getValues())
            .build();
    for (String macroString : Iterables.concat(macroStrings)) {
      try {
        deps.addAll(
            CxxDescriptionEnhancer.MACRO_HANDLER.extractParseTimeDeps(
                buildTarget,
                cellRoots,
                macroString));
      } catch (MacroException e) {
        throw new HumanReadableException(e, "%s: %s", buildTarget, e.getMessage());
      }
    }

    return deps.build();
  }

  @Override
  public boolean hasFlavors(ImmutableSet<Flavor> inputFlavors) {
    Set<Flavor> flavors = inputFlavors;

    Set<Flavor> platformFlavors = Sets.intersection(flavors, cxxPlatforms.getFlavors());
    if (platformFlavors.size() > 1) {
      return false;
    }
    flavors = Sets.difference(flavors, platformFlavors);

    flavors = Sets.difference(
        flavors,
        ImmutableSet.of(
            CxxDescriptionEnhancer.HEADER_SYMLINK_TREE_FLAVOR,
            CxxCompilationDatabase.COMPILATION_DATABASE,
            CxxCompilationDatabase.UBER_COMPILATION_DATABASE,
            CxxInferEnhancer.InferFlavors.INFER.get(),
            CxxInferEnhancer.InferFlavors.INFER_ANALYZE.get()));

    return flavors.isEmpty();
  }

  public FlavorDomain<CxxPlatform> getCxxPlatforms() {
    return cxxPlatforms;
  }

  public CxxPlatform getDefaultCxxPlatform() {
    return defaultCxxPlatform;
  }

  @Override
  public <A extends Arg, U> Optional<U> createMetadata(
      BuildTarget buildTarget,
      BuildRuleResolver resolver,
      A args,
      final Class<U> metadataClass) throws NoSuchBuildTargetException {
    if (!metadataClass.isAssignableFrom(CxxCompilationDatabaseDependencies.class) ||
        !buildTarget.getFlavors().contains(CxxCompilationDatabase.COMPILATION_DATABASE)) {
      return Optional.absent();
    }
    return CxxDescriptionEnhancer
        .createCompilationDatabaseDependencies(buildTarget, cxxPlatforms, resolver, args)
        .transform(
            new Function<CxxCompilationDatabaseDependencies, U>() {
              @Override
              public U apply(CxxCompilationDatabaseDependencies input) {
                return metadataClass.cast(input);
              }
            }
        );
  }

  @SuppressFieldNotInitialized
  public static class Arg extends LinkableCxxConstructorArg {
  }

}
