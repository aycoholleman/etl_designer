package org.domainobject.etl.engine.parser;

import static org.domainobject.etl.engine.tokenizer.TokenType.OPERATOR;
import java.util.ArrayList;
import org.domainobject.etl.engine.tokenizer.OperatorToken;
import org.domainobject.etl.engine.tokenizer.Token;

abstract class Operation<T extends AbstractExpression> extends AbstractExpression {

  final ArrayList<T> operands = new ArrayList<>();
  final ArrayList<OperatorToken> operators = new ArrayList<>();

  @Override
  void parse() throws ParseException {
    while (true) {
      operands.add(parse(child()));
      if (done(tokenizer.peek())) {
        break;
      }
      operators.add((OperatorToken) tokenizer.nextToken());
      if (tokenizer.nextToken() == null) {
        throw new ParseException(/* TODO */);
      }
    }
  }

  abstract T child();

  abstract boolean isOperator(Token t);

  private boolean done(Token t) {
    if (t == null || t.type() != OPERATOR) {
      return false;
    }
    return isOperator(t);
  }

}
