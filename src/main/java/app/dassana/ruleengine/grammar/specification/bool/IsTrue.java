package app.dassana.ruleengine.grammar.specification.bool;

import app.dassana.ruleengine.grammar.specification.CompositeSpecification;
import app.dassana.ruleengine.IJsonPathParser;

public class IsTrue extends CompositeSpecification {

    protected String jsonPathExpression ;

    public IsTrue(IJsonPathParser jsonPathParser, String jsonPathExpression ) {
        super(jsonPathParser);
        this.jsonPathExpression  = jsonPathExpression ;
    }

    @Override
    public boolean isSatisfiedBy(String jsonData) {
        return Boolean.parseBoolean(jsonPathParser.query(jsonData, jsonPathExpression ));
    }
}
