package app.dassana.ruleengine;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.atomic.AtomicReference;
import net.thisptr.jackson.jq.BuiltinFunctionLoader;
import net.thisptr.jackson.jq.JsonQuery;
import net.thisptr.jackson.jq.Scope;
import net.thisptr.jackson.jq.Versions;

public class JqPathParser implements IJqPathParser {

  Scope rootScope = Scope.newEmptyScope();
  private static final ObjectMapper MAPPER = new ObjectMapper();

  public JqPathParser() {
    BuiltinFunctionLoader.getInstance().loadFunctions(Versions.JQ_1_6, rootScope);
  }

  @Override
  public JsonNode query(String jsonData, String jqExpression) throws JsonProcessingException {
    JsonQuery jsonQuery = JsonQuery.compile(jqExpression, Versions.JQ_1_6);

    Scope childScope = Scope.newChildScope(rootScope);
    JsonNode in = MAPPER.readTree(jsonData);

    AtomicReference<JsonNode> jsonNode = new AtomicReference<>();

    jsonQuery.apply(childScope, in, jsonNode::set);

    return jsonNode.get();
  }

}
