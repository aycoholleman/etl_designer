package org.domainobject.etl.engine.tokenizer;

class CommaToken extends Token {

  CommaToken(Cursor cursor) {
    super(cursor);
  }

  @Override
  public TokenType type() {
    return TokenType.COMMA;
  }

  @Override
  String doExtract() throws TokenExtractionException {
    cursor.forward();
    return ",";
  }

}
