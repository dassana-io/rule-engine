package app.dassana.ruleengine.grammar.specification;

import app.dassana.ruleengine.IJqPathParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;

public class ExistsSpecification extends AbstractSpecification {


  public ExistsSpecification(IJqPathParser jsonPathParser, String jsonPathExpression, String value) {
    super(jsonPathParser, jsonPathExpression, value);
  }


  @Override
  public boolean isSatisfiedBy(String jsonData) throws JsonProcessingException {
    return !jsonPathParser.query(jsonData, jsonPathExpression).getNodeType().equals(JsonNodeType.NULL);

  }
}
