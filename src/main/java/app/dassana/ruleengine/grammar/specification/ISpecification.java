package app.dassana.ruleengine.grammar.specification;

public interface ISpecification {

    boolean isSatisfiedBy(String jsonData);

    ISpecification and(ISpecification specification);
    ISpecification or (ISpecification specification);
    ISpecification not(ISpecification specification);
}
