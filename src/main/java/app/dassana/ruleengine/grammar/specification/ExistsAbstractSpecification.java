package app.dassana.ruleengine.grammar.specification;

import app.dassana.ruleengine.IJsonPathParser;

public  class ExistsAbstractSpecification extends AbstractSpecification {


  public ExistsAbstractSpecification(IJsonPathParser jsonPathParser, String jsonPathExpression, String value) {
    super(jsonPathParser, jsonPathExpression, value);
  }


  @Override
  public boolean isSatisfiedBy(String jsonData) {

    String query = jsonPathParser.query(jsonData, jsonPathExpression);

    return query != null;
  }
}
