package app.dassana.ruleengine.grammar;

import app.dassana.ruleengine.grammar.specification.AbstractSpecification;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RuleSet implements IRuleSet {
    public final List<AbstractSpecification> rules;

    public RuleSet() {
        this.rules = new ArrayList<>();
    }

    public List<AbstractSpecification> getRules() {
        return Collections.unmodifiableList(rules);
    }

    public void addRule(AbstractSpecification rule) {
        this.rules.add(rule);
    }

    public Boolean isSatisfiedBy(String jsonData)
    {
        boolean result = true;
        for(AbstractSpecification rule:rules)
            if (!rule.isSatisfiedBy(jsonData)) {
                result = false;
            }
        return result;
    }
}
