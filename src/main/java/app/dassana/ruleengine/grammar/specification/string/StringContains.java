package app.dassana.ruleengine.grammar.specification.string;

import app.dassana.ruleengine.IJsonPathParser;

public class StringContains extends StringSpecificationExpression {

    public StringContains(IJsonPathParser jsonPathParser, String jsonPathExpression , String value) {
        super(jsonPathParser, jsonPathExpression , value);
    }

    @Override
    public boolean isSatisfiedBy(String jsonData) {
        return jsonPathParser.query(jsonData, jsonPathExpression ).contains(value);
    }
}
