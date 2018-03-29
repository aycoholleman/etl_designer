package org.domainobject.etl.engine.tokenizer;

class Cursor {

  static final char END_OF_RULE = 0x00;
  static final char TAB = 0x09;
  static final char LF = 0x0A;
  static final char CR = 0x0D;
  static final char DOUBLE_QUOTE = 0x22;
  static final char APOSTROPHE = 0x27;
  static final char BACKSLASH = 0x5C;

  private final String rule;

  private int pos = 0;
  private int line;
  private int col;
  private char curr;

  Cursor(String rule) {
    this.rule = rule;
    this.curr = rule.length() == 0 ? END_OF_RULE : rule.charAt(0);
  }

  char at() {
    return curr;
  }

  boolean at(char c) {
    return c == curr;
  }

  char prev() {
    if (pos == 0) {
      return END_OF_RULE;
    }
    return rule.charAt(pos - 1);
  }

  char peek() {
    return (curr == END_OF_RULE || pos + 1 == rule.length()) ? END_OF_RULE : rule.charAt(pos + 1);
  }

  Cursor forward() {
    if (curr == END_OF_RULE) {
      return this;
    }
    curr = ++pos == rule.length() ? END_OF_RULE : rule.charAt(pos);
    setLineAndColumn();
    return this;
  }

  int position() {
    return pos;
  }

  int line() {
    return line;
  }

  int column() {
    return col;
  }

  Cursor copy() {
    Cursor cursor = new Cursor(this.rule);
    cursor.pos = this.pos;
    cursor.line = this.line;
    cursor.col = this.col;
    cursor.curr = this.curr;
    return cursor;
  }

  private void setLineAndColumn() {
    if (curr == CR) {
      ++line;
      col = 0;
    } else if (curr == LF) {
      if (prev() != CR) {
        ++line;
        col = 0;
      }
    } else {
      ++col;
    }
  }

}
