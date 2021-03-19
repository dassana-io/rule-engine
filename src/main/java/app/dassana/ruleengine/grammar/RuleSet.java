package app.dassana.ruleengine.grammar;

import app.dassana.ruleengine.grammar.specification.CompositeSpecification;

import com.jayway.jsonpath.PathNotFoundException;
import java.util.*;

public class RuleSet implements IRuleSet {
    public final List<CompositeSpecification> rules;

    public RuleSet() {
        this.rules = new ArrayList<>();
    }

    public List<CompositeSpecification> getRules() {
        return Collections.unmodifiableList(rules);
    }

    public void addRule(CompositeSpecification rule) {
        this.rules.add(rule);
    }

    public Boolean isSatisfiedBy(String jsonData)
    {
        Boolean result = true;
        for(CompositeSpecification rule:rules)
        {
            try {
                if(!rule.isSatisfiedBy(jsonData))
                {
                    result = false;
                }
            } catch (PathNotFoundException e) {
                return false;
            }
        }
        return result;
    }
}
