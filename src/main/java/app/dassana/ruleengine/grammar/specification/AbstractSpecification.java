package app.dassana.ruleengine.grammar.specification;


import app.dassana.ruleengine.IJqPathParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.text.StringEscapeUtils;

public abstract class AbstractSpecification implements ISpecification {

    protected IJqPathParser jsonPathParser;

    protected String jsonPathExpression ;
    protected String value;

    public AbstractSpecification(IJqPathParser jsonPathParser, String jsonPathExpression, String value) {

        String unescapeJava = StringEscapeUtils.unescapeJava(jsonPathExpression);

        this.jsonPathParser = jsonPathParser;
        this.jsonPathExpression = unescapeJava;
        this.value = value;
    }

    public abstract boolean isSatisfiedBy(String jsonData) throws JsonProcessingException;

}
