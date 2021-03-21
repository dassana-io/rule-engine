package app.dassana.ruleengine.grammar.specification.bool;

import app.dassana.ruleengine.grammar.specification.AbstractSpecification;
import app.dassana.ruleengine.IJsonPathParser;

public class IsTrue extends AbstractSpecification {

    protected String jsonPathExpression ;

    public IsTrue(IJsonPathParser jsonPathParser, String jsonPathExpression, String value) {
        super(jsonPathParser, jsonPathExpression, value);
    }


    @Override
    public boolean isSatisfiedBy(String jsonData) {
        return Boolean.parseBoolean(jsonPathParser.query(jsonData, jsonPathExpression ));
    }
}
