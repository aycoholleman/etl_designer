package org.domainobject.etl.engine.compile;

import static com.google.common.collect.Maps.newHashMapWithExpectedSize;
import java.util.HashMap;

public enum Keyword {

  NULL, NOT, AND, OR, TRUE, FALSE;

  private static final HashMap<String, Keyword> table;

  static {
    table = newHashMapWithExpectedSize(values().length);
    for (Keyword kw : values()) {
      table.put(kw.word, kw);
    }
  }

  public static Keyword parse(String word) {
    return table.get(word);
  }

  private String word;

  public String toString() {
    return word;
  }

  private Keyword() {
    this.word = name().toLowerCase();
  }

}
