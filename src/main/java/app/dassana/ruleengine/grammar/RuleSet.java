package app.dassana.ruleengine.grammar;

import app.dassana.ruleengine.grammar.specification.AbstractSpecification;

import com.jayway.jsonpath.PathNotFoundException;
import java.util.*;

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
