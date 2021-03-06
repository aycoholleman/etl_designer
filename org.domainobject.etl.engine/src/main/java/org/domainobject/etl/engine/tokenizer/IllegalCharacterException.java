package org.domainobject.etl.engine.tokenizer;

public class IllegalCharacterException extends TokenizerException {

  private static final String MSG =
      "Illegal character at position %s (line %s, column %s): '%s' (%s)";

  private static String createMessage(Cursor cursor) {
    int pos = cursor.position();
    int line = cursor.line();
    int col = cursor.column();
    char c = cursor.at();
    String unicode = "\\u" + Integer.toHexString(c | 0x10000).substring(1);
    return String.format(MSG, pos, line, col, c, unicode);
  }

  private final Cursor cursor;

  public IllegalCharacterException(Cursor cursor) {
    super(createMessage(cursor));
    this.cursor = cursor;
  }

  public Cursor getCursor() {
    return cursor;
  }

}
