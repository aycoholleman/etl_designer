package org.domainobject.etl.engine.parser;

import org.domainobject.etl.engine.tokenizer.OperatorToken;
import org.domainobject.etl.engine.tokenizer.Token;

class Comparison extends Operation<StringExpression> {

  @Override
  StringExpression child() {
    return new StringExpression();
  }

  @Override
  boolean isOperator(Token token) {
    return (((OperatorToken) token).getOperator().isComparisonOperator());
  }
}
