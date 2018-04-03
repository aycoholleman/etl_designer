package org.domainobject.etl.engine.parser;

import org.domainobject.etl.engine.tokenizer.OperatorToken;
import org.domainobject.etl.engine.tokenizer.Token;

class StringExpression extends Operation<ArithmeticExpression> {

	@Override
	ArithmeticExpression child()
	{
		return new ArithmeticExpression();
	}

	@Override
	boolean isOperator(Token token)
	{
		return (((OperatorToken) token).getOperator().isStringOperator());
	}
}
