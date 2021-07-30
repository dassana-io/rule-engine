package app.dassana.ruleengine.grammar.specification;

import app.dassana.ruleengine.IJqPathParser;
import com.fasterxml.jackson.core.JsonProcessingException;

public class NotSpecification extends AbstractSpecification {

  ISpecification specification;

  public NotSpecification(IJqPathParser jsonPathParser, String jsonPathExpression,
      String value) {
    super(jsonPathParser, jsonPathExpression, value);
  }

  public void setSpecification(ISpecification specification) {
    this.specification = specification;
  }

  public boolean isSatisfiedBy(String jsonData) throws JsonProcessingException {
    return !this.specification.isSatisfiedBy(jsonData);
  }
}
