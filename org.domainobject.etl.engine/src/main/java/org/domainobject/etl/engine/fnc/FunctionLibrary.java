package org.domainobject.etl.engine.fnc;

import org.domainobject.etl.api.Rule;
import org.domainobject.etl.api.Template;
import org.domainobject.etl.engine.rt.TemplateRunner;

public abstract class FunctionLibrary {

	protected TemplateRunner templateRunner;
	protected Template template;
	protected Rule[] rules;

}
