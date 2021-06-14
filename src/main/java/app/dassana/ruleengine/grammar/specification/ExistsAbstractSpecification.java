package app.dassana.ruleengine.grammar.specification;

import app.dassana.ruleengine.IJsonPathParser;
import com.jayway.jsonpath.PathNotFoundException;

public class ExistsAbstractSpecification extends AbstractSpecification {


  public ExistsAbstractSpecification(IJsonPathParser jsonPathParser, String jsonPathExpression, String value) {
    super(jsonPathParser, jsonPathExpression, value);
  }


  @Override
  public boolean isSatisfiedBy(String jsonData) {

    try {
      String query = jsonPathParser.query(jsonData, jsonPathExpression);

      if(query.contentEquals("[]")){
        return false;
      }

      return true;
    } catch (PathNotFoundException e) {
      return false;
    }


  }
}
