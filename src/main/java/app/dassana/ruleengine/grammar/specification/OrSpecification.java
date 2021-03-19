package app.dassana.ruleengine.grammar.specification;


import app.dassana.ruleengine.IJsonPathParser;

public class OrSpecification extends CompositeSpecification {

    ISpecification leftSpecification;
    ISpecification rightSpecification;

    public OrSpecification(IJsonPathParser jsonPathParser, ISpecification left, ISpecification right) {
        super(jsonPathParser);
        this.leftSpecification = left;
        this.rightSpecification = right;
    }

    public boolean isSatisfiedBy(String jsonData) {
        return this.leftSpecification.isSatisfiedBy(jsonData) || this.rightSpecification.isSatisfiedBy(jsonData);
    }
}
