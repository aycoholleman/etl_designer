package org.domainobject.etl.engine.parser;

import org.domainobject.etl.engine.tokenizer.OperatorToken;
import org.domainobject.etl.engine.tokenizer.Token;

class Assignment extends Operation<Comparison> {

  @Override
  Comparison child() {
    return new Comparison();
  }

  @Override
  boolean isOperator(Token token) {
    return (((OperatorToken) token).getOperator().isAssignmentOperator());
  }

}
