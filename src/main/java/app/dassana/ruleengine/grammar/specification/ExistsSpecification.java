package app.dassana.ruleengine.grammar.specification;

import app.dassana.ruleengine.IJsonPathParser;

public  class ExistsSpecification extends CompositeSpecification {

  protected String jsonPathExpression ;
  protected IJsonPathParser iJsonPathParser;

  public ExistsSpecification(IJsonPathParser jsonPathParser, String jsonPathExpression) {
    super(jsonPathParser);
    this.jsonPathExpression  = jsonPathExpression ;
    this.iJsonPathParser=jsonPathParser;
  }

  @Override
  public boolean isSatisfiedBy(String jsonData) {

    String query = iJsonPathParser.query(jsonData, jsonPathExpression);

    return query != null;
  }
}
