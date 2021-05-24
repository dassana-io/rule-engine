package app.dassana.ruleengine.web;

import app.dassana.ruleengine.JsonPathParser;
import app.dassana.ruleengine.RuleSetCompiler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.util.StringUtils;
import io.micronaut.function.aws.MicronautRequestHandler;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

@Introspected
public class RequestHandler extends
    MicronautRequestHandler<APIGatewayProxyRequestEvent, APIGatewayV2HTTPResponse> {

  private final RuleSetCompiler ruleSetCompiler = new RuleSetCompiler(new JsonPathParser());


  @Override
  public APIGatewayV2HTTPResponse execute(APIGatewayProxyRequestEvent input) {

    String jsonData = input.getBody();
    String rule = input.getHeaders().get("x-dassana-rule");
    APIGatewayV2HTTPResponse httpResponse = new APIGatewayV2HTTPResponse();

    if (StringUtils.isNotEmpty(rule)) {
      Map<String, Boolean> response = new HashMap<>();

      Boolean satisfiedBy;
      try {
        satisfiedBy = ruleSetCompiler.getCompiledRuleSet(rule).isSatisfiedBy(jsonData);
      } catch (Exception e) {
        if (e.getMessage().contentEquals("org.antlr.v4.runtime.NoViableAltException")) {
          httpResponse.setBody("invalid rule");
          httpResponse.setStatusCode(400);

        } else {
          StringWriter sw = new StringWriter();
          PrintWriter pw = new PrintWriter(sw);
          e.printStackTrace(pw);
          httpResponse.setBody(sw.toString());
          httpResponse.setStatusCode(500);
        }
        return httpResponse;
      }
      if (satisfiedBy) {
        response.put("match", true);
      } else {
        response.put("match", false);
      }

      httpResponse.setBody(new JSONObject(response).toString());


    } else {
      httpResponse.setBody("x-dassana-rule header is missing");
      httpResponse.setStatusCode(400);
      return httpResponse;
    }
    httpResponse.setStatusCode(200);
    return httpResponse;
  }
}
