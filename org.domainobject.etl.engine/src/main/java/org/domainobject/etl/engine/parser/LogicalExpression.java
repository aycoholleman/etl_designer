package org.domainobject.etl.engine.parser;

import org.domainobject.etl.engine.tokenizer.OperatorToken;
import org.domainobject.etl.engine.tokenizer.Token;

class LogicalExpression extends Operation<Assignment> {

  @Override
  boolean isOperator(Token token) {
    return (((OperatorToken) token).getOperator().isLogicalOperator());
  }

  @Override
  Assignment child() {
    return new Assignment();
  }
}
