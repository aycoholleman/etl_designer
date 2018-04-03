package org.domainobject.etl.engine.util.tuple;

public final class DoubleInt {

  private final double d;
  private final int i;

  public DoubleInt(double d, int i) {
    this.d = d;
    this.i = i;
  }

  public double getDouble() {
    return d;
  }

  public int getInt() {
    return i;
  }

}
