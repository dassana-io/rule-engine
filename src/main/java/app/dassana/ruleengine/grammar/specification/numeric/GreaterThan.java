package app.dassana.ruleengine.grammar.specification.numeric;


import app.dassana.ruleengine.IJqPathParser;
import app.dassana.ruleengine.grammar.specification.AbstractSpecification;
import com.fasterxml.jackson.core.JsonProcessingException;

public class GreaterThan extends AbstractSpecification {


  public GreaterThan(IJqPathParser jsonPathParser, String jsonPathExpression, String value) {
    super(jsonPathParser, jsonPathExpression, value);
  }

  @Override
  public boolean isSatisfiedBy(String jsonData) throws JsonProcessingException {

    float v = Float.parseFloat(String.valueOf(jsonPathParser.query(jsonData, jsonPathExpression).asDouble()));
    float userInput = Float.parseFloat(value);

    return v > userInput;
  }
}