package app.dassana.ruleengine.grammar.specification;

import app.dassana.ruleengine.IJqPathParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import io.micronaut.core.util.StringUtils;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmptySpecification extends AbstractSpecification {

  private static final Logger logger = LoggerFactory.getLogger(EmptySpecification.class);

  public EmptySpecification(IJqPathParser jsonPathParser, String jsonPathExpression,
      String value) {
    super(jsonPathParser, jsonPathExpression, value);
  }


  @Override
  public boolean isSatisfiedBy(String jsonData) throws JsonProcessingException {
    JsonNode jsonNode = jsonPathParser.query(jsonData, jsonPathExpression);
    JsonNodeType nodeType = jsonNode.getNodeType();

    if (nodeType.equals(JsonNodeType.BOOLEAN) || nodeType.equals(JsonNodeType.NUMBER)) {
      throw new RuntimeException("The JSON path expression cannot return BOOLEAN or NUMBER data type for this "
          + "operator");
    }

    if (nodeType.equals(JsonNodeType.STRING)) {
      return StringUtils.isEmpty(value);
    }

    if (nodeType.equals(JsonNodeType.OBJECT)) {
      return jsonNode.size() == 0;
    }

    if (nodeType.equals(JsonNodeType.ARRAY)) {
      return jsonNode.size() == 0;
    }

    return false;
  }


}
