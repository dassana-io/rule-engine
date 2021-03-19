package app.dassana.ruleengine.grammar.specification.string;

import app.dassana.ruleengine.IJsonPathParser;

public class StringEquals extends StringSpecificationExpression {

    public StringEquals(IJsonPathParser jsonPathParser, String jsonPathExpression , String value) {
        super(jsonPathParser, jsonPathExpression , value);
    }

    @Override
    public boolean isSatisfiedBy(String jsonData) {
        return jsonPathParser.query(jsonData, jsonPathExpression).equals(value);
    }
}
