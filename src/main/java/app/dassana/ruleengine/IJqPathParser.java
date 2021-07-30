package app.dassana.ruleengine;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import net.thisptr.jackson.jq.exception.JsonQueryException;

public interface IJqPathParser {
    JsonNode query(String jsonData, String jqExpression) throws JsonProcessingException;
}
