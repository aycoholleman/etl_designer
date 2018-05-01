package org.domainobject.etl.engine.tokenizer;

public class InvalidNumberException extends TokenExtractionException {

  public InvalidNumberException(Token token) {
    super(token);
  }

  public InvalidNumberException(Token token, String message) {
    super(token, message);
  }

}
