package org.domainobject.etl.engine.parser;

import static org.domainobject.etl.engine.parser.TerminalType.NUMBER_LITERAL;
import static org.domainobject.etl.engine.parser.TerminalType.REFERENCE;
import static org.domainobject.etl.engine.parser.TerminalType.STRING_LITERAL;
import org.domainobject.etl.engine.compile.Keyword;
import org.domainobject.etl.engine.tokenizer.OperatorToken;
import org.domainobject.etl.engine.tokenizer.Token;

class ArithmeticExpression extends Operation<AbstractExpression> {

  @Override
  AbstractExpression child() {
    Token token = tokenizer.at();
    switch (token.type()) {
      case SINGLE_QUOTED_STRING:
      case DOUBLE_QUOTED_STRING:
        return new Terminal(STRING_LITERAL, token);
      case NUMBER:
        return new Terminal(NUMBER_LITERAL, token);
      case LPAREN:
        return new SimpleExpression();
      case OPERATOR:
        OperatorToken ot = (OperatorToken) token;
        if (ot.getOperator().isUnaryOperator()) {
          return new UnaryOperation();
        }
      case WORD:
        if (Keyword.parse(token.data()) == Keyword.NOT) {
          return new UnaryOperation();
        }
        if (inspector.findRule(token.data()) != null) {
          return new Terminal(REFERENCE, token);
        }
      default:
        return null;
    }
  }

  @Override
  boolean isOperator(Token t) {
    return (((OperatorToken) t).getOperator().isArithmeticOperator());
  }

}
