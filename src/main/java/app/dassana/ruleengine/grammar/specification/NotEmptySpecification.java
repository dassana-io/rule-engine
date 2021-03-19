package app.dassana.ruleengine.grammar.specification;

import app.dassana.ruleengine.IJsonPathParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotEmptySpecification extends CompositeSpecification {

  private static final Logger logger = LoggerFactory.getLogger(NotEmptySpecification.class);

  protected String jsonPathExpression;
  protected IJsonPathParser iJsonPathParser;

  public NotEmptySpecification(IJsonPathParser jsonPathParser, String jsonPathExpression) {
    super(jsonPathParser);
    this.jsonPathExpression = jsonPathExpression;
    this.iJsonPathParser = jsonPathParser;
  }


  @Override
  public boolean isSatisfiedBy(String jsonData) {

    String query = jsonPathParser.query(jsonData, jsonPathExpression);

    if (query != null && query.contentEquals("")) {
      return false;
    }

    if (query != null && query.contentEquals("{}")) {
      return false;
    }

    return true;
  }
}
