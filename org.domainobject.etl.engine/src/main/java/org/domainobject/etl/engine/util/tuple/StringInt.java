package org.domainobject.etl.engine.util.tuple;

public final class StringInt {

  private final String s;
  private final int i;

  public StringInt(String s, int i) {
    this.s = s;
    this.i = i;
  }

  public String getString() {
    return s;
  }

  public int getInt() {
    return i;
  }

}
