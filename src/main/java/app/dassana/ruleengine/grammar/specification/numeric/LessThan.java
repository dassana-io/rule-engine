package app.dassana.ruleengine.grammar.specification.numeric;

import app.dassana.ruleengine.IJsonPathParser;
import app.dassana.ruleengine.grammar.specification.ArithmeticExpression;

public class LessThan extends NumericSpecificationExpression {

    public LessThan(IJsonPathParser jsonPathParser, ArithmeticExpression leftArithmeticExpression, ArithmeticExpression rightArithmeticExpression) {
        super(jsonPathParser, leftArithmeticExpression , rightArithmeticExpression);
    }

    @Override
    public boolean isSatisfiedBy(String jsonData) {
        return leftArithmeticExpression.calculate(jsonData) < rightArithmeticExpression.calculate(jsonData);
    }
}
