package app.dassana.ruleengine.grammar.specification.numeric;


import app.dassana.ruleengine.grammar.specification.ArithmeticExpression;
import app.dassana.ruleengine.IJsonPathParser;

public class GreaterThan extends NumericSpecificationExpression {

    public GreaterThan(IJsonPathParser jsonPathParser, ArithmeticExpression leftArithmeticExpression, ArithmeticExpression rightArithmeticExpression) {
        super(jsonPathParser, leftArithmeticExpression , rightArithmeticExpression);
    }

    @Override
    public boolean isSatisfiedBy(String jsonData) {
        return leftArithmeticExpression.calculate(jsonData) > rightArithmeticExpression.calculate(jsonData);
    }
}