package app.dassana.ruleengine.grammar.specification;

import app.dassana.ruleengine.IJsonPathParser;
import com.jayway.jsonpath.PathNotFoundException;

public class DoesNotExistsAbstractSpecification extends AbstractSpecification {


  public DoesNotExistsAbstractSpecification(IJsonPathParser jsonPathParser, String jsonPathExpression, String value) {
    super(jsonPathParser, jsonPathExpression, value);
  }


  @Override
  public boolean isSatisfiedBy(String jsonData) {

    try {
      jsonPathParser.query(jsonData, jsonPathExpression);
    } catch (PathNotFoundException e) {
      return true;
    }

    return false;
  }

}
