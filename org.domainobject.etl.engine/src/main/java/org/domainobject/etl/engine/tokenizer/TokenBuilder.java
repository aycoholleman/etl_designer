package org.domainobject.etl.engine.tokenizer;

import java.util.Arrays;

class TokenBuilder {

  private char[] value;
  private int count;

  TokenBuilder(int capacity) {
    value = new char[capacity];
  }

  int length() {
    return count;
  }

  void add(char c) {
    if (count == value.length) {
      value = Arrays.copyOf(value, value.length * 2);
    }
    value[count++] = c;
  }

  void add(char c1, char c2) {
    if (count + 1 == value.length) {
      value = Arrays.copyOf(value, value.length * 2);
    }
    value[count++] = c1;
    value[count++] = c2;
  }

  @Override
  public String toString() {
    return new String(value, 0, count);
  }

}
