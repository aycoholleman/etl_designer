package org.domainobject.etl.engine.parser;

import org.domainobject.etl.engine.tokenizer.Token;

class Terminal extends AbstractExpression {

  final TerminalType type;
  final Token token;

  Terminal(TerminalType type, Token token) {
    this.type = type;
    this.token = token;
  }

  @Override
  public void parse() {}

}
