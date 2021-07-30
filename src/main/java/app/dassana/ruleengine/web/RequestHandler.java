package app.dassana.ruleengine.web;

import app.dassana.ruleengine.JqPathParser;
import app.dassana.ruleengine.RuleSetCompiler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.util.StringUtils;
import io.micronaut.function.aws.MicronautRequestHandler;
import io.micronaut.http.HttpStatus;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONObject;

@Introspected
public class RequestHandler extends
    MicronautRequestHandler<APIGatewayProxyRequestEvent, APIGatewayV2HTTPResponse> {

  private final RuleSetCompiler ruleSetCompiler = new RuleSetCompiler(new JqPathParser());


  @Override
  public APIGatewayV2HTTPResponse execute(APIGatewayProxyRequestEvent input) {

    String jsonData = input.getBody();
    String rule = input.getHeaders().get("x-dassana-rule");
    APIGatewayV2HTTPResponse httpResponse = new APIGatewayV2HTTPResponse();

    if (StringUtils.isNotEmpty(rule)) {
      Map<String, Boolean> response = new HashMap<>();
      Boolean satisfiedBy;
      String parseTreeAsString = "";

      String matchType = input.getHeaders().getOrDefault("x-dassana-check-type", "match");

      if (matchType.contentEquals("grammar")) {

        try {
          parseTreeAsString = ruleSetCompiler.getParseTreeAsString(rule);
        } catch (Exception e) {
          StringWriter sw = new StringWriter();
          PrintWriter pw = new PrintWriter(sw);
          e.printStackTrace(pw);
          httpResponse.setBody(sw.toString());
          httpResponse.setStatusCode(HttpStatus.BAD_REQUEST.getCode());
          return httpResponse;
        }
        httpResponse.setBody(parseTreeAsString);
        httpResponse.setStatusCode(200);
        return httpResponse;
      }

      try {
        satisfiedBy = ruleSetCompiler.getCompiledRuleSet(rule).isSatisfiedBy(jsonData);
        parseTreeAsString = ruleSetCompiler.getParseTreeAsString(rule);
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

      Map<String, String> headers = httpResponse.getHeaders();
      if (headers == null) {
        headers = new HashMap<>();
      }
      headers.put("x-dassana-parsed-rule-tree", StringEscapeUtils.unescapeJava(parseTreeAsString));
      httpResponse.setBody(new JSONObject(response).toString());
      httpResponse.setHeaders(headers);


    } else {
      httpResponse.setBody("x-dassana-rule header is missing");
      httpResponse.setStatusCode(400);
      return httpResponse;
    }
    httpResponse.setStatusCode(200);
    return httpResponse;
  }
}
