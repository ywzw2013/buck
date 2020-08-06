/*
 * Copyright (c) Facebook, Inc. and its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.facebook.buck.jvm.java;

import com.facebook.buck.core.model.BuildTarget;
import com.facebook.buck.event.EventKey;
import com.facebook.buck.event.SimplePerfEvent;
import com.facebook.buck.event.WorkAdvanceEvent;
import com.google.common.base.CaseFormat;
import com.google.common.collect.ImmutableMap;

/** Base class for events about Java annotation processing. */
public abstract class AnnotationProcessingEvent extends SimplePerfEvent
    implements WorkAdvanceEvent {

  public enum Operation {
    GET_COMPLETIONS,
    GET_SUPPORTED_ANNOTATION_TYPES,
    GET_SUPPORTED_OPTIONS,
    GET_SUPPORTED_SOURCE_VERSION,
    INIT,
    PROCESS,
  }

  private final BuildTarget buildTarget;
  private final String annotationProcessorName;
  private final Operation operation;
  private final int round;
  private final boolean isLastRound;

  protected AnnotationProcessingEvent(
      EventKey eventKey,
      BuildTarget buildTarget,
      String annotationProcessorName,
      Operation operation,
      int round,
      boolean isLastRound) {
    super(eventKey);
    this.buildTarget = buildTarget;
    this.annotationProcessorName = annotationProcessorName;
    this.operation = operation;
    this.round = round;
    this.isLastRound = isLastRound;
  }

  public BuildTarget getBuildTarget() {
    return buildTarget;
  }

  public Operation getOperation() {
    return operation;
  }

  public int getRound() {
    return round;
  }

  public boolean isLastRound() {
    return isLastRound;
  }

  @Override
  protected String getValueString() {
    return buildTarget.toString();
  }

  @Override
  public String getCategory() {
    return annotationProcessorName;
  }

  @Override
  public PerfEventId getEventId() {
    return PerfEventId.of(
        annotationProcessorName
            + "."
            + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, operation.toString()));
  }

  @Override
  public ImmutableMap<String, Object> getEventInfo() {
    return ImmutableMap.of();
  }

  public static Started started(
      BuildTarget buildTarget,
      String annotationProcessorName,
      Operation operation,
      int round,
      boolean isLastRound) {
    return new Started(buildTarget, annotationProcessorName, operation, round, isLastRound);
  }

  public static Finished finished(Started started) {
    return new Finished(started);
  }

  public static class Started extends AnnotationProcessingEvent {
    public Started(
        BuildTarget buildTarget,
        String annotationProcessorName,
        Operation operation,
        int round,
        boolean isLastRound) {
      super(EventKey.unique(), buildTarget, annotationProcessorName, operation, round, isLastRound);
    }

    @Override
    public String getEventName() {
      return String.format(
          "%s.%sStarted",
          getCategory(),
          CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, getOperation().toString()));
    }

    @Override
    public Type getEventType() {
      return Type.STARTED;
    }
  }

  public static class Finished extends AnnotationProcessingEvent {
    public Finished(AnnotationProcessingEvent.Started started) {
      super(
          started.getEventKey(),
          started.getBuildTarget(),
          started.getCategory(),
          started.getOperation(),
          started.getRound(),
          started.isLastRound());
    }

    public Finished(
        EventKey eventKey,
        BuildTarget buildTarget,
        String annotationProcessorName,
        Operation operation,
        int round,
        boolean isLastRound) {
      super(eventKey, buildTarget, annotationProcessorName, operation, round, isLastRound);
    }

    @Override
    public String getEventName() {
      return String.format(
          "%s.%sFinished",
          getCategory(),
          CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, getOperation().toString()));
    }

    @Override
    public Type getEventType() {
      return Type.FINISHED;
    }
  }
}
