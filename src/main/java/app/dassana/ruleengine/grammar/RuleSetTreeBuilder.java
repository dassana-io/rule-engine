package app.dassana.ruleengine.grammar;

import app.dassana.ruleengine.IJsonPathParser;
import app.dassana.ruleengine.grammar.specification.AbstractSpecification;
import app.dassana.ruleengine.grammar.specification.AndSpecification;
import app.dassana.ruleengine.grammar.specification.DoesNotExistsAbstractSpecification;
import app.dassana.ruleengine.grammar.specification.ExistsAbstractSpecification;
import app.dassana.ruleengine.grammar.specification.NotSpecification;
import app.dassana.ruleengine.grammar.specification.OrSpecification;
import app.dassana.ruleengine.grammar.specification.numeric.GreaterThan;
import app.dassana.ruleengine.grammar.specification.string.StringContains;
import app.dassana.ruleengine.grammar.specification.string.StringEquals;
import app.dassana.rules.RuleSetBaseListener;
import app.dassana.rules.RuleSetParser;
import app.dassana.rules.RuleSetParser.DoesNotExistOperatorContext;
import app.dassana.rules.RuleSetParser.ExistsOperatorContext;
import app.dassana.rules.RuleSetParser.GenericJsonPathConditionContext;
import app.dassana.rules.RuleSetParser.JsonPathConditionContext;
import app.dassana.rules.RuleSetParser.LogicalExpressionNotContext;
import app.dassana.rules.RuleSetParser.NumberGreaterThanOperatorContext;
import app.dassana.rules.RuleSetParser.ParenExpressionContext;
import app.dassana.rules.RuleSetParser.StringContainsExpressionContext;
import app.dassana.rules.RuleSetParser.StringEqualsOperatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import org.antlr.v4.runtime.ParserRuleContext;

public class RuleSetTreeBuilder extends RuleSetBaseListener {

  private final Stack<AbstractSpecification> specifications = new Stack<>();
  private final Stack<ParserRuleContext> operatorContextStack = new Stack<>();

  protected IJsonPathParser jsonPathParser;
  private RuleSet ruleSet = null;


  public RuleSetTreeBuilder(IJsonPathParser jsonPathParser) {
    this.jsonPathParser = jsonPathParser;
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
  public void exitJsonPathCondition(JsonPathConditionContext ctx) {

    String path = ctx.getChild(0).getText();
    String value = ctx.getChild(2).getText();


    ParserRuleContext parserRuleContext = operatorContextStack.pop();

    if (parserRuleContext instanceof StringContainsExpressionContext) {
      this.specifications.push(new StringContains(jsonPathParser, path, value));
    }
    if (parserRuleContext instanceof NumberGreaterThanOperatorContext) {
      this.specifications.push(new GreaterThan(jsonPathParser, path, value));
    }
    if(parserRuleContext instanceof StringEqualsOperatorContext){
      this.specifications.push(new StringEquals(jsonPathParser, path, value));
    }


  }

  @Override
  public void exitExistsOperator(ExistsOperatorContext ctx) {
    specifications.push(new ExistsAbstractSpecification(jsonPathParser, null, null));
  }

  @Override
  public void exitStringEqualsOperator(StringEqualsOperatorContext ctx) {
    operatorContextStack.push(ctx);
  }

  @Override
  public void exitGenericJsonPathCondition(GenericJsonPathConditionContext ctx) {
    String path = ctx.getChild(0).getText();
    AbstractSpecification abstractSpecification = specifications.pop();

    if (abstractSpecification instanceof DoesNotExistsAbstractSpecification) {
      this.specifications.push(new DoesNotExistsAbstractSpecification(jsonPathParser, path, null));
    }

    if (abstractSpecification instanceof ExistsAbstractSpecification) {
      this.specifications.push(new ExistsAbstractSpecification(jsonPathParser, path, null));
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
    specifications.push(new DoesNotExistsAbstractSpecification(jsonPathParser, null, null));
  }

  protected List<String> getArray(RuleSetParser.String_arrayContext string_array) {
    String arrayString = string_array.getText()
        .replace("(", "")
        .replace(")", "");

    return Arrays.asList(arrayString.split("\\s*,\\s*"));
  }


  protected String getValueExpression(RuleSetParser.Json_pathContext value_expr) {
    if (value_expr.jsonpath_expr() != null) {
      return getJsonPathExpression(value_expr.jsonpath_expr());
    }

    return value_expr.getText();
  }

  protected String getJsonPathExpression(RuleSetParser.Jsonpath_exprContext jsonpath_expr) {
    return jsonpath_expr.getText();
  }
}
