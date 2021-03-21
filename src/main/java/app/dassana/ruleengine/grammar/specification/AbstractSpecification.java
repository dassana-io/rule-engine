package app.dassana.ruleengine.grammar.specification;


import app.dassana.ruleengine.IJsonPathParser;

public abstract class AbstractSpecification implements ISpecification {

    protected IJsonPathParser jsonPathParser;

    protected String jsonPathExpression ;
    protected String value;

    public AbstractSpecification(IJsonPathParser jsonPathParser, String jsonPathExpression, String value) {
        this.jsonPathParser = jsonPathParser;
        this.jsonPathExpression = jsonPathExpression;
        this.value = value;
    }

    public abstract boolean isSatisfiedBy(String jsonData);

}
