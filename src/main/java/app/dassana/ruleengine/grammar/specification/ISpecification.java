package app.dassana.ruleengine.grammar.specification;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ISpecification {

    boolean isSatisfiedBy(String jsonData) throws JsonProcessingException;


}
