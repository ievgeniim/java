/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package avrofile;

import org.apache.avro.specific.SpecificData;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Automobile extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 1665772345876888284L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Automobile\",\"namespace\":\"avrofile\",\"fields\":[{\"name\":\"id\",\"type\":\"int\"},{\"name\":\"markname\",\"type\":[\"string\",\"null\"]},{\"name\":\"modelname\",\"type\":[\"string\",\"null\"]},{\"name\":\"year\",\"type\":[\"int\",\"null\"]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public int id;
  @Deprecated public java.lang.CharSequence markname;
  @Deprecated public java.lang.CharSequence modelname;
  @Deprecated public java.lang.Integer year;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Automobile() {}

  /**
   * All-args constructor.
   * @param id The new value for id
   * @param markname The new value for markname
   * @param modelname The new value for modelname
   * @param year The new value for year
   */
  public Automobile(java.lang.Integer id, java.lang.CharSequence markname, java.lang.CharSequence modelname, java.lang.Integer year) {
    this.id = id;
    this.markname = markname;
    this.modelname = modelname;
    this.year = year;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return markname;
    case 2: return modelname;
    case 3: return year;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.Integer)value$; break;
    case 1: markname = (java.lang.CharSequence)value$; break;
    case 2: modelname = (java.lang.CharSequence)value$; break;
    case 3: year = (java.lang.Integer)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return The value of the 'id' field.
   */
  public java.lang.Integer getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.lang.Integer value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'markname' field.
   * @return The value of the 'markname' field.
   */
  public java.lang.CharSequence getMarkname() {
    return markname;
  }

  /**
   * Sets the value of the 'markname' field.
   * @param value the value to set.
   */
  public void setMarkname(java.lang.CharSequence value) {
    this.markname = value;
  }

  /**
   * Gets the value of the 'modelname' field.
   * @return The value of the 'modelname' field.
   */
  public java.lang.CharSequence getModelname() {
    return modelname;
  }

  /**
   * Sets the value of the 'modelname' field.
   * @param value the value to set.
   */
  public void setModelname(java.lang.CharSequence value) {
    this.modelname = value;
  }

  /**
   * Gets the value of the 'year' field.
   * @return The value of the 'year' field.
   */
  public java.lang.Integer getYear() {
    return year;
  }

  /**
   * Sets the value of the 'year' field.
   * @param value the value to set.
   */
  public void setYear(java.lang.Integer value) {
    this.year = value;
  }

  /**
   * Creates a new Automobile RecordBuilder.
   * @return A new Automobile RecordBuilder
   */
  public static avrofile.Automobile.Builder newBuilder() {
    return new avrofile.Automobile.Builder();
  }

  /**
   * Creates a new Automobile RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Automobile RecordBuilder
   */
  public static avrofile.Automobile.Builder newBuilder(avrofile.Automobile.Builder other) {
    return new avrofile.Automobile.Builder(other);
  }

  /**
   * Creates a new Automobile RecordBuilder by copying an existing Automobile instance.
   * @param other The existing instance to copy.
   * @return A new Automobile RecordBuilder
   */
  public static avrofile.Automobile.Builder newBuilder(avrofile.Automobile other) {
    return new avrofile.Automobile.Builder(other);
  }

  /**
   * RecordBuilder for Automobile instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Automobile>
    implements org.apache.avro.data.RecordBuilder<Automobile> {

    private int id;
    private java.lang.CharSequence markname;
    private java.lang.CharSequence modelname;
    private java.lang.Integer year;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(avrofile.Automobile.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.markname)) {
        this.markname = data().deepCopy(fields()[1].schema(), other.markname);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.modelname)) {
        this.modelname = data().deepCopy(fields()[2].schema(), other.modelname);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.year)) {
        this.year = data().deepCopy(fields()[3].schema(), other.year);
        fieldSetFlags()[3] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Automobile instance
     * @param other The existing instance to copy.
     */
    private Builder(avrofile.Automobile other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.markname)) {
        this.markname = data().deepCopy(fields()[1].schema(), other.markname);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.modelname)) {
        this.modelname = data().deepCopy(fields()[2].schema(), other.modelname);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.year)) {
        this.year = data().deepCopy(fields()[3].schema(), other.year);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public java.lang.Integer getId() {
      return id;
    }

    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public avrofile.Automobile.Builder setId(int value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * @return This builder.
      */
    public avrofile.Automobile.Builder clearId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'markname' field.
      * @return The value.
      */
    public java.lang.CharSequence getMarkname() {
      return markname;
    }

    /**
      * Sets the value of the 'markname' field.
      * @param value The value of 'markname'.
      * @return This builder.
      */
    public avrofile.Automobile.Builder setMarkname(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.markname = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'markname' field has been set.
      * @return True if the 'markname' field has been set, false otherwise.
      */
    public boolean hasMarkname() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'markname' field.
      * @return This builder.
      */
    public avrofile.Automobile.Builder clearMarkname() {
      markname = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'modelname' field.
      * @return The value.
      */
    public java.lang.CharSequence getModelname() {
      return modelname;
    }

    /**
      * Sets the value of the 'modelname' field.
      * @param value The value of 'modelname'.
      * @return This builder.
      */
    public avrofile.Automobile.Builder setModelname(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.modelname = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'modelname' field has been set.
      * @return True if the 'modelname' field has been set, false otherwise.
      */
    public boolean hasModelname() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'modelname' field.
      * @return This builder.
      */
    public avrofile.Automobile.Builder clearModelname() {
      modelname = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'year' field.
      * @return The value.
      */
    public java.lang.Integer getYear() {
      return year;
    }

    /**
      * Sets the value of the 'year' field.
      * @param value The value of 'year'.
      * @return This builder.
      */
    public avrofile.Automobile.Builder setYear(java.lang.Integer value) {
      validate(fields()[3], value);
      this.year = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'year' field has been set.
      * @return True if the 'year' field has been set, false otherwise.
      */
    public boolean hasYear() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'year' field.
      * @return This builder.
      */
    public avrofile.Automobile.Builder clearYear() {
      year = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    public Automobile build() {
      try {
        Automobile record = new Automobile();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.Integer) defaultValue(fields()[0]);
        record.markname = fieldSetFlags()[1] ? this.markname : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.modelname = fieldSetFlags()[2] ? this.modelname : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.year = fieldSetFlags()[3] ? this.year : (java.lang.Integer) defaultValue(fields()[3]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  private static final org.apache.avro.io.DatumWriter
    WRITER$ = new org.apache.avro.specific.SpecificDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  private static final org.apache.avro.io.DatumReader
    READER$ = new org.apache.avro.specific.SpecificDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
