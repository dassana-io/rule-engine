package app.dassana.ruleengine.grammar;

import app.dassana.ruleengine.IJqPathParser;
import app.dassana.ruleengine.grammar.specification.AbstractSpecification;
import app.dassana.ruleengine.grammar.specification.AndSpecification;
import app.dassana.ruleengine.grammar.specification.DoesNotExistsSpecification;
import app.dassana.ruleengine.grammar.specification.EmptySpecification;
import app.dassana.ruleengine.grammar.specification.ExistsSpecification;
import app.dassana.ruleengine.grammar.specification.NotSpecification;
import app.dassana.ruleengine.grammar.specification.OrSpecification;
import app.dassana.ruleengine.grammar.specification.bool.IsTrue;
import app.dassana.ruleengine.grammar.specification.numeric.GreaterThan;
import app.dassana.ruleengine.grammar.specification.string.StringContains;
import app.dassana.ruleengine.grammar.specification.string.StringEquals;
import app.dassana.rules.RuleSetBaseListener;
import app.dassana.rules.RuleSetParser;
import app.dassana.rules.RuleSetParser.BoolIstrueOperatorContext;
import app.dassana.rules.RuleSetParser.BoolOperationsContext;
import app.dassana.rules.RuleSetParser.DoesNotExistOperatorContext;
import app.dassana.rules.RuleSetParser.EmptyOperatorContext;
import app.dassana.rules.RuleSetParser.ExistsOperatorContext;
import app.dassana.rules.RuleSetParser.GenericOperationsContext;
import app.dassana.rules.RuleSetParser.LogicalExpressionNotContext;
import app.dassana.rules.RuleSetParser.NumberGreaterThanOperatorContext;
import app.dassana.rules.RuleSetParser.NumberOperationsContext;
import app.dassana.rules.RuleSetParser.ParenExpressionContext;
import app.dassana.rules.RuleSetParser.StringContainsExpressionContext;
import app.dassana.rules.RuleSetParser.StringEqualsOperatorContext;
import app.dassana.rules.RuleSetParser.StringOperationsContext;
import java.util.Stack;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.Interval;

public class RuleSetTreeBuilder extends RuleSetBaseListener {

  private final Stack<AbstractSpecification> specifications = new Stack<>();
  private final Stack<ParserRuleContext> operatorContextStack = new Stack<>();

  protected IJqPathParser jqPathParser;
  private RuleSet ruleSet = null;


  public RuleSetTreeBuilder(IJqPathParser jsonPathParser) {
    this.jqPathParser = jsonPathParser;
  }

  public RuleSet getRuleSet() {
    return ruleSet;
  }

  @Override
  public void enterRule_set(RuleSetParser.Rule_setContext ctx) {
    assert ruleSet == null;
    assert specifications.empty();

    this.ruleSet = new RuleSet();
  }

  @Override
  public void exitRule_set(RuleSetParser.Rule_setContext ctx) {
    while (!specifications.isEmpty()) {
      ruleSet.addRule(specifications.pop());
    }
  }

  @Override
  public void exitParenExpression(ParenExpressionContext ctx) {
    super.exitParenExpression(ctx);
  }

  @Override
  public void exitLogicalExpressionAnd(RuleSetParser.LogicalExpressionAndContext ctx) {
    AbstractSpecification right = specifications.pop();
    AbstractSpecification left = specifications.pop();
    this.specifications.push(new AndSpecification(left, right));
  }


  @Override
  public void exitLogicalExpressionOr(RuleSetParser.LogicalExpressionOrContext ctx) {
    AbstractSpecification right = specifications.pop();
    AbstractSpecification left = specifications.pop();
    this.specifications.push(new OrSpecification(left, right));
  }

  @Override
  public void exitLogicalExpressionNot(LogicalExpressionNotContext ctx) {
    AbstractSpecification pop = specifications.pop();
    NotSpecification notSpecification = new NotSpecification(null, null, null);
    notSpecification.setSpecification(pop);
    this.specifications.push(notSpecification);
  }

  @Override
  public void exitStringOperations(StringOperationsContext ctx) {
    String path = ctx.getChild(0).getText();
    String value = ctx.getChild(2).getText();

    ParserRuleContext parserRuleContext = operatorContextStack.pop();
    if (parserRuleContext instanceof StringContainsExpressionContext) {
      this.specifications.push(new StringContains(jqPathParser, path, value));
    }
    if (parserRuleContext instanceof StringEqualsOperatorContext) {
      this.specifications.push(new StringEquals(jqPathParser, path, value));
    }
  }

  @Override
  public void exitNumberOperations(NumberOperationsContext ctx) {
    String path = ctx.getChild(0).getText();
    String value = ctx.getChild(2).getText();

    ParserRuleContext parserRuleContext = operatorContextStack.pop();
    if (parserRuleContext instanceof NumberGreaterThanOperatorContext) {
      this.specifications.push(new GreaterThan(jqPathParser, path, value));
    }

  }

  @Override
  public void exitBoolOperations(BoolOperationsContext ctx) {
    String path = ctx.getChild(0).getText();
    String value = ctx.getChild(1).getText();
    if (value.contentEquals("is true")) {
      this.specifications.push(new IsTrue(jqPathParser, path, "true"));
    } else if (value.contentEquals("is false")) {
      this.specifications.push(new IsTrue(jqPathParser, path, "false"));
    } else {
      throw new RuntimeException("Unexpected operator for boolean: ".concat(value));
    }
  }

  @Override
  public void exitExistsOperator(ExistsOperatorContext ctx) {
    specifications.push(new ExistsSpecification(jqPathParser, null, null));
  }

  @Override
  public void exitStringEqualsOperator(StringEqualsOperatorContext ctx) {
    operatorContextStack.push(ctx);
  }


  @Override
  public void exitGenericOperations(GenericOperationsContext ctx) {
    String path = ctx.getChild(0).getText();

    int a = ctx.jq_path().start.getStartIndex();
    int b = ctx.jq_path().stop.getStopIndex();
    Interval interval = new Interval(a, b);
    //String path = ctx.json_path().start.getInputStream().getText(interval);

    AbstractSpecification abstractSpecification = specifications.pop();

    if (abstractSpecification instanceof DoesNotExistsSpecification) {
      this.specifications.push(new DoesNotExistsSpecification(jqPathParser, path, null));
    }

    if (abstractSpecification instanceof ExistsSpecification) {
      this.specifications.push(new ExistsSpecification(jqPathParser, path, null));
    }

    if (abstractSpecification instanceof EmptySpecification) {
      this.specifications.push(new EmptySpecification(jqPathParser, path, null));
    }
  }

  @Override
  public void exitStringContainsExpression(StringContainsExpressionContext ctx) {
    operatorContextStack.push(ctx);
  }

  @Override
  public void exitNumberGreaterThanOperator(NumberGreaterThanOperatorContext ctx) {
    operatorContextStack.push(ctx);
  }


  @Override
  public void exitDoesNotExistOperator(DoesNotExistOperatorContext ctx) {
    specifications.push(new DoesNotExistsSpecification(jqPathParser, null, null));
  }

  @Override
  public void exitEmptyOperator(EmptyOperatorContext ctx) {
    specifications.push(new EmptySpecification(jqPathParser, null, null));
  }

  /*  protected List<String> getArray(RuleSetParser.String_arrayContext string_array) {
    String arrayString = string_array.getText()
        .replace("(", "")
        .replace(")", "");

    return Arrays.asList(arrayString.split("\\s*,\\s*"));
  }*/


/*  protected String getValueExpression(RuleSetParser.Json_pathContext value_expr) {
    if (value_expr.jsonpath_expr() != null) {
      return getJsonPathExpression(value_expr.jsonpath_expr());
    }

    return value_expr.getText();
  }*/

/*  protected String getJsonPathExpression(RuleSetParser.Jsonpath_exprContext jsonpath_expr) {
    return jsonpath_expr.getText();
  }*/
}
