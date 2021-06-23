package app.dassana.ruleengine.grammar.specification;


import app.dassana.ruleengine.IJsonPathParser;
import org.apache.commons.text.StringEscapeUtils;

public abstract class AbstractSpecification implements ISpecification {

    protected IJsonPathParser jsonPathParser;

    protected String jsonPathExpression ;
    protected String value;

    public AbstractSpecification(IJsonPathParser jsonPathParser, String jsonPathExpression, String value) {

        String unescapeJava = StringEscapeUtils.unescapeJava(jsonPathExpression);

        this.jsonPathParser = jsonPathParser;
        this.jsonPathExpression = unescapeJava;
        this.value = value;
    }

    public abstract boolean isSatisfiedBy(String jsonData);

}
