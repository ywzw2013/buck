/**
 * Autogenerated by Thrift Compiler (0.12.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.facebook.buck.artifact_cache.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.12.0)")
public class BuckCacheDeleteRequest implements org.apache.thrift.TBase<BuckCacheDeleteRequest, BuckCacheDeleteRequest._Fields>, java.io.Serializable, Cloneable, Comparable<BuckCacheDeleteRequest> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("BuckCacheDeleteRequest");

  private static final org.apache.thrift.protocol.TField RULE_KEYS_FIELD_DESC = new org.apache.thrift.protocol.TField("ruleKeys", org.apache.thrift.protocol.TType.LIST, (short)1);
  private static final org.apache.thrift.protocol.TField REPOSITORY_FIELD_DESC = new org.apache.thrift.protocol.TField("repository", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField SCHEDULE_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("scheduleType", org.apache.thrift.protocol.TType.STRING, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new BuckCacheDeleteRequestStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new BuckCacheDeleteRequestTupleSchemeFactory();

  public @org.apache.thrift.annotation.Nullable java.util.List<RuleKey> ruleKeys; // optional
  public @org.apache.thrift.annotation.Nullable java.lang.String repository; // optional
  public @org.apache.thrift.annotation.Nullable java.lang.String scheduleType; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    RULE_KEYS((short)1, "ruleKeys"),
    REPOSITORY((short)2, "repository"),
    SCHEDULE_TYPE((short)3, "scheduleType");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // RULE_KEYS
          return RULE_KEYS;
        case 2: // REPOSITORY
          return REPOSITORY;
        case 3: // SCHEDULE_TYPE
          return SCHEDULE_TYPE;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final _Fields optionals[] = {_Fields.RULE_KEYS,_Fields.REPOSITORY,_Fields.SCHEDULE_TYPE};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.RULE_KEYS, new org.apache.thrift.meta_data.FieldMetaData("ruleKeys", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, RuleKey.class))));
    tmpMap.put(_Fields.REPOSITORY, new org.apache.thrift.meta_data.FieldMetaData("repository", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.SCHEDULE_TYPE, new org.apache.thrift.meta_data.FieldMetaData("scheduleType", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(BuckCacheDeleteRequest.class, metaDataMap);
  }

  public BuckCacheDeleteRequest() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public BuckCacheDeleteRequest(BuckCacheDeleteRequest other) {
    if (other.isSetRuleKeys()) {
      java.util.List<RuleKey> __this__ruleKeys = new java.util.ArrayList<RuleKey>(other.ruleKeys.size());
      for (RuleKey other_element : other.ruleKeys) {
        __this__ruleKeys.add(new RuleKey(other_element));
      }
      this.ruleKeys = __this__ruleKeys;
    }
    if (other.isSetRepository()) {
      this.repository = other.repository;
    }
    if (other.isSetScheduleType()) {
      this.scheduleType = other.scheduleType;
    }
  }

  public BuckCacheDeleteRequest deepCopy() {
    return new BuckCacheDeleteRequest(this);
  }

  @Override
  public void clear() {
    this.ruleKeys = null;
    this.repository = null;
    this.scheduleType = null;
  }

  public int getRuleKeysSize() {
    return (this.ruleKeys == null) ? 0 : this.ruleKeys.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<RuleKey> getRuleKeysIterator() {
    return (this.ruleKeys == null) ? null : this.ruleKeys.iterator();
  }

  public void addToRuleKeys(RuleKey elem) {
    if (this.ruleKeys == null) {
      this.ruleKeys = new java.util.ArrayList<RuleKey>();
    }
    this.ruleKeys.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.List<RuleKey> getRuleKeys() {
    return this.ruleKeys;
  }

  public BuckCacheDeleteRequest setRuleKeys(@org.apache.thrift.annotation.Nullable java.util.List<RuleKey> ruleKeys) {
    this.ruleKeys = ruleKeys;
    return this;
  }

  public void unsetRuleKeys() {
    this.ruleKeys = null;
  }

  /** Returns true if field ruleKeys is set (has been assigned a value) and false otherwise */
  public boolean isSetRuleKeys() {
    return this.ruleKeys != null;
  }

  public void setRuleKeysIsSet(boolean value) {
    if (!value) {
      this.ruleKeys = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getRepository() {
    return this.repository;
  }

  public BuckCacheDeleteRequest setRepository(@org.apache.thrift.annotation.Nullable java.lang.String repository) {
    this.repository = repository;
    return this;
  }

  public void unsetRepository() {
    this.repository = null;
  }

  /** Returns true if field repository is set (has been assigned a value) and false otherwise */
  public boolean isSetRepository() {
    return this.repository != null;
  }

  public void setRepositoryIsSet(boolean value) {
    if (!value) {
      this.repository = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getScheduleType() {
    return this.scheduleType;
  }

  public BuckCacheDeleteRequest setScheduleType(@org.apache.thrift.annotation.Nullable java.lang.String scheduleType) {
    this.scheduleType = scheduleType;
    return this;
  }

  public void unsetScheduleType() {
    this.scheduleType = null;
  }

  /** Returns true if field scheduleType is set (has been assigned a value) and false otherwise */
  public boolean isSetScheduleType() {
    return this.scheduleType != null;
  }

  public void setScheduleTypeIsSet(boolean value) {
    if (!value) {
      this.scheduleType = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case RULE_KEYS:
      if (value == null) {
        unsetRuleKeys();
      } else {
        setRuleKeys((java.util.List<RuleKey>)value);
      }
      break;

    case REPOSITORY:
      if (value == null) {
        unsetRepository();
      } else {
        setRepository((java.lang.String)value);
      }
      break;

    case SCHEDULE_TYPE:
      if (value == null) {
        unsetScheduleType();
      } else {
        setScheduleType((java.lang.String)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case RULE_KEYS:
      return getRuleKeys();

    case REPOSITORY:
      return getRepository();

    case SCHEDULE_TYPE:
      return getScheduleType();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case RULE_KEYS:
      return isSetRuleKeys();
    case REPOSITORY:
      return isSetRepository();
    case SCHEDULE_TYPE:
      return isSetScheduleType();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof BuckCacheDeleteRequest)
      return this.equals((BuckCacheDeleteRequest)that);
    return false;
  }

  public boolean equals(BuckCacheDeleteRequest that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_ruleKeys = true && this.isSetRuleKeys();
    boolean that_present_ruleKeys = true && that.isSetRuleKeys();
    if (this_present_ruleKeys || that_present_ruleKeys) {
      if (!(this_present_ruleKeys && that_present_ruleKeys))
        return false;
      if (!this.ruleKeys.equals(that.ruleKeys))
        return false;
    }

    boolean this_present_repository = true && this.isSetRepository();
    boolean that_present_repository = true && that.isSetRepository();
    if (this_present_repository || that_present_repository) {
      if (!(this_present_repository && that_present_repository))
        return false;
      if (!this.repository.equals(that.repository))
        return false;
    }

    boolean this_present_scheduleType = true && this.isSetScheduleType();
    boolean that_present_scheduleType = true && that.isSetScheduleType();
    if (this_present_scheduleType || that_present_scheduleType) {
      if (!(this_present_scheduleType && that_present_scheduleType))
        return false;
      if (!this.scheduleType.equals(that.scheduleType))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetRuleKeys()) ? 131071 : 524287);
    if (isSetRuleKeys())
      hashCode = hashCode * 8191 + ruleKeys.hashCode();

    hashCode = hashCode * 8191 + ((isSetRepository()) ? 131071 : 524287);
    if (isSetRepository())
      hashCode = hashCode * 8191 + repository.hashCode();

    hashCode = hashCode * 8191 + ((isSetScheduleType()) ? 131071 : 524287);
    if (isSetScheduleType())
      hashCode = hashCode * 8191 + scheduleType.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(BuckCacheDeleteRequest other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetRuleKeys()).compareTo(other.isSetRuleKeys());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRuleKeys()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ruleKeys, other.ruleKeys);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetRepository()).compareTo(other.isSetRepository());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRepository()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.repository, other.repository);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetScheduleType()).compareTo(other.isSetScheduleType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetScheduleType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.scheduleType, other.scheduleType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("BuckCacheDeleteRequest(");
    boolean first = true;

    if (isSetRuleKeys()) {
      sb.append("ruleKeys:");
      if (this.ruleKeys == null) {
        sb.append("null");
      } else {
        sb.append(this.ruleKeys);
      }
      first = false;
    }
    if (isSetRepository()) {
      if (!first) sb.append(", ");
      sb.append("repository:");
      if (this.repository == null) {
        sb.append("null");
      } else {
        sb.append(this.repository);
      }
      first = false;
    }
    if (isSetScheduleType()) {
      if (!first) sb.append(", ");
      sb.append("scheduleType:");
      if (this.scheduleType == null) {
        sb.append("null");
      } else {
        sb.append(this.scheduleType);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class BuckCacheDeleteRequestStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public BuckCacheDeleteRequestStandardScheme getScheme() {
      return new BuckCacheDeleteRequestStandardScheme();
    }
  }

  private static class BuckCacheDeleteRequestStandardScheme extends org.apache.thrift.scheme.StandardScheme<BuckCacheDeleteRequest> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, BuckCacheDeleteRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // RULE_KEYS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list82 = iprot.readListBegin();
                struct.ruleKeys = new java.util.ArrayList<RuleKey>(_list82.size);
                @org.apache.thrift.annotation.Nullable RuleKey _elem83;
                for (int _i84 = 0; _i84 < _list82.size; ++_i84)
                {
                  _elem83 = new RuleKey();
                  _elem83.read(iprot);
                  struct.ruleKeys.add(_elem83);
                }
                iprot.readListEnd();
              }
              struct.setRuleKeysIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // REPOSITORY
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.repository = iprot.readString();
              struct.setRepositoryIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // SCHEDULE_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.scheduleType = iprot.readString();
              struct.setScheduleTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, BuckCacheDeleteRequest struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.ruleKeys != null) {
        if (struct.isSetRuleKeys()) {
          oprot.writeFieldBegin(RULE_KEYS_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.ruleKeys.size()));
            for (RuleKey _iter85 : struct.ruleKeys)
            {
              _iter85.write(oprot);
            }
            oprot.writeListEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      if (struct.repository != null) {
        if (struct.isSetRepository()) {
          oprot.writeFieldBegin(REPOSITORY_FIELD_DESC);
          oprot.writeString(struct.repository);
          oprot.writeFieldEnd();
        }
      }
      if (struct.scheduleType != null) {
        if (struct.isSetScheduleType()) {
          oprot.writeFieldBegin(SCHEDULE_TYPE_FIELD_DESC);
          oprot.writeString(struct.scheduleType);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class BuckCacheDeleteRequestTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public BuckCacheDeleteRequestTupleScheme getScheme() {
      return new BuckCacheDeleteRequestTupleScheme();
    }
  }

  private static class BuckCacheDeleteRequestTupleScheme extends org.apache.thrift.scheme.TupleScheme<BuckCacheDeleteRequest> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, BuckCacheDeleteRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetRuleKeys()) {
        optionals.set(0);
      }
      if (struct.isSetRepository()) {
        optionals.set(1);
      }
      if (struct.isSetScheduleType()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetRuleKeys()) {
        {
          oprot.writeI32(struct.ruleKeys.size());
          for (RuleKey _iter86 : struct.ruleKeys)
          {
            _iter86.write(oprot);
          }
        }
      }
      if (struct.isSetRepository()) {
        oprot.writeString(struct.repository);
      }
      if (struct.isSetScheduleType()) {
        oprot.writeString(struct.scheduleType);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, BuckCacheDeleteRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list87 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.ruleKeys = new java.util.ArrayList<RuleKey>(_list87.size);
          @org.apache.thrift.annotation.Nullable RuleKey _elem88;
          for (int _i89 = 0; _i89 < _list87.size; ++_i89)
          {
            _elem88 = new RuleKey();
            _elem88.read(iprot);
            struct.ruleKeys.add(_elem88);
          }
        }
        struct.setRuleKeysIsSet(true);
      }
      if (incoming.get(1)) {
        struct.repository = iprot.readString();
        struct.setRepositoryIsSet(true);
      }
      if (incoming.get(2)) {
        struct.scheduleType = iprot.readString();
        struct.setScheduleTypeIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

