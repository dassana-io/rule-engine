package app.dassana.ruleengine;

import app.dassana.ruleengine.grammar.IRuleSet;
import app.dassana.ruleengine.grammar.RuleSetTreeBuilder;
import app.dassana.ruleengine.grammar.ExceptionThrowingErrorHandler;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

public class RuleSetCompiler implements IRuleSetCompiler {

    protected IJsonPathParser jsonPathParser;

    public RuleSetCompiler(IJsonPathParser jsonPathParser) {
        this.jsonPathParser = jsonPathParser;
    }

    public IRuleSet getCompiledRuleSet(String rule) {
        CharStream input = new ANTLRInputStream(rule);
        app.dassana.rules.RuleSetLexer lexer = new app.dassana.rules.RuleSetLexer(input);
        TokenStream tokens = new CommonTokenStream(lexer);
        app.dassana.rules.RuleSetParser parser = new app.dassana.rules.RuleSetParser(tokens);

        RuleSetTreeBuilder ruleSetTreeBuilder = new RuleSetTreeBuilder(jsonPathParser);

        parser.addParseListener(ruleSetTreeBuilder);
        parser.setErrorHandler(new ExceptionThrowingErrorHandler());
        parser.rule_set();

        return ruleSetTreeBuilder.getRuleSet();
    }

    public String getParseTreeAsString(String rule) {
        CharStream input = new ANTLRInputStream(rule);
        app.dassana.rules.RuleSetLexer lexer = new app.dassana.rules.RuleSetLexer(input);
        TokenStream tokens = new CommonTokenStream(lexer);
        app.dassana.rules.RuleSetParser parser = new app.dassana.rules.RuleSetParser(tokens);

        return parser.rule_set().toStringTree(parser);
    }
}
