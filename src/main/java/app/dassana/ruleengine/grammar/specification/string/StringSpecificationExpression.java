package app.dassana.ruleengine.grammar.specification.string;

import app.dassana.ruleengine.grammar.specification.CompositeSpecification;
import app.dassana.ruleengine.IJsonPathParser;

public abstract class StringSpecificationExpression extends CompositeSpecification {

    protected String jsonPathExpression ;
    protected String value;

    public StringSpecificationExpression(IJsonPathParser jsonPathParser, String jsonPathExpression , String value) {
        super(jsonPathParser);
        this.jsonPathExpression  = jsonPathExpression ;
        this.value = value;
    }
}
