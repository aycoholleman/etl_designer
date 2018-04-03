package org.domainobject.etl.engine.parser;

import org.domainobject.etl.api.Rule;
import org.domainobject.etl.api.TemplateInspector;
import org.domainobject.etl.engine.fnc.def.LibDef;
import org.domainobject.etl.engine.tokenizer.Tokenizer;

abstract class AbstractExpression {

	TemplateInspector inspector;
	Rule rule;
	Tokenizer tokenizer;
	LibDef<?>[] libDefs;

	abstract void parse() throws ParseException;

	<T extends AbstractExpression> T parse(T child) throws ParseException
	{
		child.inspector = this.inspector;
		child.rule = this.rule;
		child.tokenizer = this.tokenizer;
		child.libDefs = this.libDefs;
		child.parse();
		return child;
	}

}
