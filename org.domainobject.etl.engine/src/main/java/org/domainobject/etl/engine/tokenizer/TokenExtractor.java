package org.domainobject.etl.engine.tokenizer;

import static org.domainobject.etl.engine.tokenizer.Cursor.APOSTROPHE;
import static org.domainobject.etl.engine.tokenizer.Cursor.CR;
import static org.domainobject.etl.engine.tokenizer.Cursor.DOUBLE_QUOTE;
import static org.domainobject.etl.engine.tokenizer.Cursor.LF;
import static org.domainobject.etl.engine.tokenizer.Cursor.END_OF_RULE;
import org.domainobject.etl.engine.compile.Operator;

class TokenExtractor {

	private final Cursor cursor;

	TokenExtractor(Cursor cursor)
	{
		this.cursor = cursor;
	}

	Token nextToken() throws IllegalCharacterException, TokenExtractionException
	{
		skipWhitespace();
		Token token = null;
		if (cursor.at(END_OF_RULE))
			return null;
		if (cursor.at(DOUBLE_QUOTE))
			token = new DoubleQuotedStringToken(cursor);
		else if (cursor.at(APOSTROPHE))
			token = new SingleQuotedStringToken(cursor);
		else if (NumberToken.isNumberStart(cursor.at()))
			token = new NumberToken(cursor);
		else if (cursor.at('('))
			token = new LeftParenthesisToken(cursor);
		else if (cursor.at(')'))
			token = new RightParenthesisToken(cursor);
		else if (cursor.at(','))
			token = new CommaToken(cursor);
		else if (Operator.isOperatorStart(cursor.at()))
			token = new OperatorToken(cursor);
		else if (Character.isJavaIdentifierStart(cursor.at()) && !cursor.at('$'))
			token = new WordToken(cursor);
		else
			throw new IllegalCharacterException(cursor.copy());
		token.extract();
		return token;
	}

	boolean hasMoreTokens()
	{
		skipWhitespace();
		return !cursor.at(END_OF_RULE);
	}

	private void skipWhitespace()
	{
		while (true) {
			if (Character.isWhitespace(cursor.at()))
				cursor.forward();
			else if (Character.isISOControl(cursor.at()) && !cursor.at(END_OF_RULE))
				cursor.forward();
			else if (isCommentStart())
				do {
					cursor.forward();
				}
				while (!isCommentEnd());
			else
				break;
		}
	}

	private boolean isCommentStart()
	{
		return cursor.at('#')
				&& (cursor.position() == 0 || cursor.prev() == LF || cursor.prev() == CR);
	}

	private boolean isCommentEnd()
	{
		return cursor.at(END_OF_RULE) || cursor.at(LF) || cursor.at(CR);
	}

}
