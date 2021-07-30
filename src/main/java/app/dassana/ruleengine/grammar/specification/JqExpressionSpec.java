package app.dassana.ruleengine.grammar.specification;

import app.dassana.ruleengine.IJqPathParser;
import com.fasterxml.jackson.core.JsonProcessingException;

public class JqExpressionSpec extends AbstractSpecification {

  String expression;

  public JqExpressionSpec(IJqPathParser jsonPathParser, String jsonPathExpression,
      String value) {
    super(jsonPathParser, jsonPathExpression, value);
    this.expression = jsonPathExpression;
  }

  @Override
  public boolean isSatisfiedBy(String jsonData) throws JsonProcessingException {

    return jsonPathParser.query(jsonData, expression).asBoolean();
  }
}
