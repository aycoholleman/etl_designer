package org.domainobject.etl.engine.tokenizer;

public abstract class Token {

  final Cursor cursor;

  private final int start;
  private final int line;

  private String data;
  private int end;

  Token(Cursor cursor) {
    this.cursor = cursor;
    this.start = cursor.position();
    this.line = cursor.line();
  }

  /*
   * Since subclasses of Token only have package visibility, calling this method is the only way to
   * figure out the actual type of the token.
   */
  public abstract TokenType type();

  public final void extract() throws TokenExtractionException {
    data = doExtract();
    end = cursor.position();
  }

  /*
   * Method implementing the actual extraction logic. Subclasses are expected to move the cursor to
   * the character just after the last character of the token, even if that means moving it beyond
   * the end of the rule. Subclasses can and must assume that the cursor points at the first
   * character of the token.
   */
  abstract String doExtract() throws TokenExtractionException;

  public int start() {
    return start;
  }

  public int line() {
    return line;
  }

  /*
   * Get the data index right __after__ the last character of the data, or the index at which data
   * extraction encountered an error.
   */
  public int end() {
    return end;
  }

  public String data() {
    if (data == null) {
      throw new IllegalStateException("Token not extracted yet");
    }
    return data;
  }

  @Override
  public String toString() {
    return data;
  }

}
