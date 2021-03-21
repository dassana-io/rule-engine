package app.dassana.ruleengine.grammar;

import app.dassana.ruleengine.grammar.specification.AbstractSpecification;

import java.util.List;

public interface IRuleSet {
    List<AbstractSpecification> getRules();
    void addRule(AbstractSpecification rule);
    Boolean isSatisfiedBy(String jsonData);
}
