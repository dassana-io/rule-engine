package app.dassana.ruleengine.grammar.specification.numeric;


import app.dassana.ruleengine.IJsonPathParser;
import app.dassana.ruleengine.grammar.specification.ArithmeticExpression;
import app.dassana.ruleengine.grammar.specification.CompositeSpecification;

public abstract class NumericSpecificationExpression extends CompositeSpecification {

    protected ArithmeticExpression leftArithmeticExpression;
    protected ArithmeticExpression rightArithmeticExpression;

    public NumericSpecificationExpression(IJsonPathParser jsonPathParser, ArithmeticExpression leftArithmeticExpression , ArithmeticExpression rightArithmeticExpression) {
        super(jsonPathParser);
        this.leftArithmeticExpression  = leftArithmeticExpression;
        this.rightArithmeticExpression = rightArithmeticExpression;
    }
}
