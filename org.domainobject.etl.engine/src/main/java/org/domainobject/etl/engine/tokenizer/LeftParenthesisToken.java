package org.domainobject.etl.engine.tokenizer;

/**
 * @author Ayco Holleman
 */
class LeftParenthesisToken extends Token {

	LeftParenthesisToken(Cursor cursor)
	{
		super(cursor);
	}


	@Override
	public TokenType type()
	{
		return TokenType.LPAREN;
	}


	@Override
	String doExtract() throws TokenExtractionException
	{
		// Move cursor past token
		cursor.forward();
		return "(";
	}

}
