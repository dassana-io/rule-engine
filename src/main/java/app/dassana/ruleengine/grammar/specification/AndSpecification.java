package app.dassana.ruleengine.grammar.specification;


import com.fasterxml.jackson.core.JsonProcessingException;

public class AndSpecification extends AbstractSpecification {

  ISpecification leftSpecification;
  ISpecification rightSpecification;

  public AndSpecification(ISpecification leftSpecification,
      ISpecification rightSpecification) {
    super(null, null, null);
    this.leftSpecification = leftSpecification;
    this.rightSpecification = rightSpecification;
  }

  public boolean isSatisfiedBy(String jsonData) throws JsonProcessingException {
    return this.leftSpecification.isSatisfiedBy(jsonData) && this.rightSpecification.isSatisfiedBy(jsonData);
  }

}
