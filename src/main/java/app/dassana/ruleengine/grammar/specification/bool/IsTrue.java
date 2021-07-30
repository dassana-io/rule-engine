package app.dassana.ruleengine.grammar.specification.bool;

import app.dassana.ruleengine.grammar.specification.AbstractSpecification;
import app.dassana.ruleengine.IJqPathParser;
import com.fasterxml.jackson.core.JsonProcessingException;

public class IsTrue extends AbstractSpecification {

  protected String jsonPathExpression;
  boolean value;

  public IsTrue(IJqPathParser jsonPathParser, String jsonPathExpression, String value) {
    super(jsonPathParser, jsonPathExpression, value);
    this.jsonPathExpression = jsonPathExpression;
    this.value = Boolean.parseBoolean(value);
  }


  @Override
  public boolean isSatisfiedBy(String jsonData) throws JsonProcessingException {
    return jsonPathParser.query(jsonData, jsonPathExpression).asBoolean() == value;

  }
}
