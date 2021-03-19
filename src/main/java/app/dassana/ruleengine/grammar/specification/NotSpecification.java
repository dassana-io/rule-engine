package app.dassana.ruleengine.grammar.specification;

import app.dassana.ruleengine.IJsonPathParser;

public class NotSpecification extends CompositeSpecification {
    ISpecification specification;

    public NotSpecification(IJsonPathParser jsonPathParser, ISpecification specification)  {
        super(jsonPathParser);
        this.specification = specification;
    }

    public boolean isSatisfiedBy(String jsonData)   {
        return !this.specification.isSatisfiedBy(jsonData);
    }
}
