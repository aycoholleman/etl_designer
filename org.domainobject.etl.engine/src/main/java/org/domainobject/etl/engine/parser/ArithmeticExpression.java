package org.domainobject.etl.engine.parser;

import static org.domainobject.etl.engine.parser.TerminalType.NUMBER_LITERAL;
import static org.domainobject.etl.engine.parser.TerminalType.REFERENCE;
import static org.domainobject.etl.engine.parser.TerminalType.STRING_LITERAL;
import static org.domainobject.etl.engine.tokenizer.TokenType.DOUBLE_QUOTED_STRING;
import static org.domainobject.etl.engine.tokenizer.TokenType.LPAREN;
import static org.domainobject.etl.engine.tokenizer.TokenType.NUMBER;
import static org.domainobject.etl.engine.tokenizer.TokenType.OPERATOR;
import static org.domainobject.etl.engine.tokenizer.TokenType.SINGLE_QUOTED_STRING;
import static org.domainobject.etl.engine.tokenizer.TokenType.WORD;
import org.domainobject.etl.api.Rule;
import org.domainobject.etl.engine.compile.Keyword;
import org.domainobject.etl.engine.tokenizer.OperatorToken;
import org.domainobject.etl.engine.tokenizer.Token;
import org.domainobject.etl.engine.tokenizer.TokenType;


class ArithmeticExpression extends Operation<AbstractExpression> {

	@Override
	AbstractExpression child()
	{
		Token token = tokenizer.at();
		TokenType tt = token.type();
		if (tt == SINGLE_QUOTED_STRING || tt == DOUBLE_QUOTED_STRING)
			return new Terminal(STRING_LITERAL, token);
		if (tt == NUMBER)
			return new Terminal(NUMBER_LITERAL, token);
		if (tt == LPAREN)
			return new SimpleExpression();
		if (tt == OPERATOR) {
			OperatorToken ot = (OperatorToken) token;
			if (ot.getOperator().isUnaryOperator())
				return new UnaryOperation();
		}
		if (tt == WORD) {
			if (Keyword.parse(token.data()) == Keyword.NOT)
				return new UnaryOperation();
			Rule rule = inspector.findRule(token.data());
			if (rule != null)
				return new Terminal(REFERENCE, token);
		}
		return null;
	}

	@Override
	boolean isOperator(Token t)
	{
		return (((OperatorToken) t).getOperator().isArithmeticOperator());
	}

}
