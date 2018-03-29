package org.domainobject.etl.engine.tokenizer;

import static org.domainobject.etl.engine.tokenizer.Cursor.END_OF_RULE;
import org.domainobject.etl.engine.compile.Operator;

public class OperatorToken extends Token {

  private Operator operator;

  OperatorToken(Cursor cursor) {
    super(cursor);
  }

  @Override
  public TokenType type() {
    return TokenType.OPERATOR;
  }

  public Operator getOperator() {
    return operator;
  }

  @Override
  String doExtract() throws TokenExtractionException {
    // Cursor now points at the first character of an operator
    char c0 = cursor.at();
    char c1 = cursor.forward().at();
    String token = String.valueOf(c0);
    operator = Operator.parse(token);
    if (c1 == END_OF_RULE) {
      return token;
    }
    String tmpTok = String.valueOf(new char[] {c0, c1});
    Operator tmpOp = Operator.parse(tmpTok);
    tmpOp = Operator.parse(tmpTok);
    if (operator != null) {
      token = tmpTok;
      operator = tmpOp;
      cursor.forward();
    }
    return token;
  }

}
