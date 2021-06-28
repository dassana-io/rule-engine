package app.dassana.ruleengine;

import app.dassana.ruleengine.grammar.ExceptionThrowingErrorHandler;
import app.dassana.ruleengine.grammar.IRuleSet;
import app.dassana.ruleengine.grammar.RuleSetTreeBuilder;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

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

        parser.removeErrorListeners();
        parser.removeParseListeners();

        parser.setErrorHandler(new ExceptionThrowingErrorHandler());

        parser.addParseListener(new ParseTreeListener() {
            @Override
            public void visitTerminal(TerminalNode node) {

            }

            @Override
            public void visitErrorNode(ErrorNode node) {
               throw new RuntimeException("Error near ".concat(node.toString()));

            }

            @Override
            public void enterEveryRule(ParserRuleContext ctx) {

            }

            @Override
            public void exitEveryRule(ParserRuleContext ctx) {
            }
        });


        return parser.rule_set().toStringTree(parser);
    }
}
