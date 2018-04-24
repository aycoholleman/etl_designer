package org.domainobject.etl.engine.tokenizer;

import java.util.Arrays;

class TokenBuilder {

  private char[] val;
  private int cnt;

  TokenBuilder(int capacity) {
    val = new char[capacity];
  }

  int length() {
    return cnt;
  }

  void add(char c) {
    if (cnt == val.length) {
      val = Arrays.copyOf(val, val.length * 2);
    }
    val[cnt++] = c;
  }

  void add(char c1, char c2) {
    if (cnt + 1 == val.length) {
      val = Arrays.copyOf(val, val.length * 2);
    }
    val[cnt++] = c1;
    val[cnt++] = c2;
  }

  @Override
  public String toString() {
    return new String(val, 0, cnt);
  }

}
