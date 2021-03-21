package app.dassana.ruleengine.grammar.specification.numeric;


import app.dassana.ruleengine.IJsonPathParser;
import app.dassana.ruleengine.grammar.specification.AbstractSpecification;

public class GreaterThan extends AbstractSpecification {


  public GreaterThan(IJsonPathParser jsonPathParser, String jsonPathExpression, String value) {
    super(jsonPathParser, jsonPathExpression, value);
  }

  @Override
  public boolean isSatisfiedBy(String jsonData) {

    float v = Float.parseFloat(jsonPathParser.query(jsonData, jsonPathExpression));
    float userInput = Float.parseFloat(value);

    return v > userInput;
  }
}