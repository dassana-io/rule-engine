package app.dassana.ruleengine.grammar.specification.string;

import app.dassana.ruleengine.IJqPathParser;
import app.dassana.ruleengine.grammar.specification.AbstractSpecification;
import com.fasterxml.jackson.core.JsonProcessingException;

public class StringContains extends AbstractSpecification {


  public StringContains(IJqPathParser jsonPathParser, String jsonPathExpression, String value) {
    super(jsonPathParser, jsonPathExpression, value);
  }

  @Override
  public boolean isSatisfiedBy(String jsonData) throws JsonProcessingException {
    return jsonPathParser.query(jsonData, jsonPathExpression).asText().contains(value);
  }
}
