package app.dassana.ruleengine;

import app.dassana.ruleengine.grammar.IRuleSet;

public interface IRuleSetCompiler {
    IRuleSet getCompiledRuleSet(String rule);
}
