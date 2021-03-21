package app.dassana.ruleengine.grammar.specification;

import app.dassana.ruleengine.IJsonPathParser;

public class NotSpecification extends AbstractSpecification {

  ISpecification specification;

  public NotSpecification(IJsonPathParser jsonPathParser, String jsonPathExpression,
      String value) {
    super(jsonPathParser, jsonPathExpression, value);
  }

  public ISpecification getSpecification() {
    return specification;
  }

  public void setSpecification(ISpecification specification) {
    this.specification = specification;
  }

  public boolean isSatisfiedBy(String jsonData) {
    return !this.specification.isSatisfiedBy(jsonData);
  }
}
