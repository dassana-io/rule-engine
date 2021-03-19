package app.dassana.ruleengine.grammar.specification.array;

import app.dassana.ruleengine.grammar.specification.CompositeSpecification;
import app.dassana.ruleengine.IJsonPathParser;

import java.util.List;

public class ArrayIncludesOne extends CompositeSpecification {

    protected String jsonPathExpression ;
    protected List<String> values;

    public ArrayIncludesOne(IJsonPathParser jsonPathParser, String jsonPathExpression , List<String> values) {
        super(jsonPathParser);
        this.jsonPathExpression = jsonPathExpression;
        this.values = values;
    }

    public boolean isSatisfiedBy(String jsonData) {
        List<String> jsonPathExpressionResults = jsonPathParser.queryArray(jsonData, jsonPathExpression);

        for(String value : values) {
            if (jsonPathExpressionResults.contains(value)) {
                return true;
            }
        }

        return false;
    }
}
