package org.domainobject.etl.engine.tokenizer;

/*
 * Extracts keywords, variable names and function identifiers.
 */
class WordToken extends Token {

  WordToken(Cursor cursor) {
    super(cursor);
  }

  @Override
  public TokenType type() {
    return TokenType.WORD;
  }

  @Override
  String doExtract() throws TokenExtractionException {
    // Cursor now points either to an underscore ('_') or to a letter
    TokenBuilder token = new TokenBuilder(16);
    do {
      token.add(cursor.at());
      cursor.forward();
    } while (Character.isLetterOrDigit(cursor.at()) || cursor.at('_'));
    return token.toString();
  }

}
