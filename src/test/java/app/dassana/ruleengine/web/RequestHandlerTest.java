package app.dassana.ruleengine.web;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import java.util.HashMap;
import java.util.Map;
import junit.framework.TestCase;
import org.junit.Assert;

public class RequestHandlerTest extends TestCase {

  public void testExecute() {

    RequestHandler requestHandler=new RequestHandler();
    APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent = new APIGatewayProxyRequestEvent();
    Map<String,String> headers=new HashMap<>();
    headers.put("x-dassana-check-type","grammar");
    headers.put("x-dassana-rule","$.x contains foo");
    apiGatewayProxyRequestEvent.setHeaders(headers);
    APIGatewayV2HTTPResponse apiGatewayV2HTTPResponse = requestHandler.execute(apiGatewayProxyRequestEvent);
    String body = apiGatewayV2HTTPResponse.getBody();
    Assert.assertEquals("(rule_set (single_rule (logical_expr (json_path_condition (json_path $. x) (operator "
        + "(string_operators contains)) (comparison_value foo)))) <EOF>)",body);

    headers.put("x-dassana-rule","$x");
    APIGatewayV2HTTPResponse badResponse = requestHandler.execute(apiGatewayProxyRequestEvent);
    Assert.assertEquals(400,badResponse.getStatusCode());

  }

}