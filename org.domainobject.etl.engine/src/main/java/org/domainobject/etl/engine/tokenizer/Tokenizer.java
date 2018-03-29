package org.domainobject.etl.engine.tokenizer;

import java.util.ArrayList;

public class Tokenizer {

  private final TokenExtractor extractor;
  private final ArrayList<Token> tokens;

  private int ptr = -1;

  public Tokenizer(Cursor cursor) throws IllegalCharacterException, TokenExtractionException {
    extractor = new TokenExtractor(cursor);
    tokens = new ArrayList<>();
    while (extractor.hasMoreTokens())
      tokens.add(extractor.nextToken());
  }

  public int numTokens() {
    return tokens.size();
  }

  public boolean hasMoreTokens() {
    return (ptr + 1) < tokens.size();
  }

  public Token at() {
    if (tokens.size() > 0 && ptr != -1)
      return tokens.get(ptr);
    return null;
  }

  public Token nextToken() {
    if (hasMoreTokens())
      return tokens.get(++ptr);
    return null;
  }

  public Token peek() {
    return peek(1);
  }

  public Token peek(int ahead) {
    int i = ptr + ahead;
    if (i >= 0 && i < tokens.size())
      return tokens.get(i);
    return null;
  }

}
