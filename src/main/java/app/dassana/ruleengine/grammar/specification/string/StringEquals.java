package app.dassana.ruleengine.grammar.specification.string;

import app.dassana.ruleengine.IJqPathParser;
import app.dassana.ruleengine.grammar.specification.AbstractSpecification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;

public class StringEquals extends AbstractSpecification {

  public StringEquals(IJqPathParser jsonPathParser, String jsonPathExpression, String value) {
    super(jsonPathParser, jsonPathExpression, value);
  }

  @Override
  public boolean isSatisfiedBy(String jsonData) throws JsonProcessingException {

    JsonNode jsonNode = jsonPathParser.query(jsonData, jsonPathExpression);

    if (jsonNode.getNodeType().equals(JsonNodeType.STRING)) {
      return jsonNode.asText().equals(value);
    } else {
      throw new RuntimeException("Expected to see a string from jq expression");
    }


  }
}
