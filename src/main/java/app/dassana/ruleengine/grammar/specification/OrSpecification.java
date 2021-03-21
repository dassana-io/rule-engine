package app.dassana.ruleengine.grammar.specification;


public class OrSpecification extends AbstractSpecification {

  ISpecification leftSpecification;
  ISpecification rightSpecification;

  public OrSpecification(ISpecification leftSpecification,
      ISpecification rightSpecification) {
    super(null, null, null);
    this.leftSpecification = leftSpecification;
    this.rightSpecification = rightSpecification;
  }


  public boolean isSatisfiedBy(String jsonData) {
    return this.leftSpecification.isSatisfiedBy(jsonData) || this.rightSpecification.isSatisfiedBy(jsonData);
  }

}
