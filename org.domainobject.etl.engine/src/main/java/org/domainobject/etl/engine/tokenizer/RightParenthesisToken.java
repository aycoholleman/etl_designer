package org.domainobject.etl.engine.tokenizer;

/**
 * @author Ayco Holleman
 */
class RightParenthesisToken extends Token {

	RightParenthesisToken(Cursor cursor)
	{
		super(cursor);
	}


	@Override
	public TokenType type()
	{
		return TokenType.RPAREN;
	}


	@Override
	String doExtract() throws TokenExtractionException
	{
		cursor.forward(); // Move cursor past token
		return ")";
	}

}
