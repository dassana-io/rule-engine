package app.dassana.ruleengine.grammar.specification.string;

import app.dassana.ruleengine.IJsonPathParser;
import app.dassana.ruleengine.grammar.specification.AbstractSpecification;

public class StringContains extends AbstractSpecification {


  public StringContains(IJsonPathParser jsonPathParser, String jsonPathExpression, String value) {
    super(jsonPathParser, jsonPathExpression, value);
  }

  @Override
  public boolean isSatisfiedBy(String jsonData) {
    return jsonPathParser.query(jsonData, jsonPathExpression).contains(value);
  }
}
