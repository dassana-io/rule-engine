package app.dassana.ruleengine.grammar;

import app.dassana.ruleengine.grammar.specification.CompositeSpecification;

import java.util.List;

public interface IRuleSet {
    List<CompositeSpecification> getRules();
    void addRule(CompositeSpecification rule);
    Boolean isSatisfiedBy(String jsonData);
}
