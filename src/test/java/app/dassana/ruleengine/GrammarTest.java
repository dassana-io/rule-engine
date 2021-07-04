package app.dassana.ruleengine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import org.apache.commons.io.IOUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class GrammarTest {

  private final RuleSetCompiler ruleSetCompiler = new RuleSetCompiler(new JsonPathParser());
  private final boolean validRule;
  private final String rule;
  private final boolean expectedResult;
  private final String sampleJson =
      IOUtils.toString(Thread.currentThread().getContextClassLoader().getResourceAsStream("sample.json"),
          Charset.defaultCharset());

  public GrammarTest(boolean validRule, String rule, boolean expectedResult) throws IOException {
    this.validRule = validRule;
    this.rule = rule;
    this.expectedResult = expectedResult;
  }

  @Parameterized.Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][]{
        {false,"$.nullValue is empty",false},
        {false,"$.number is empty",false},
        {false,"$.organic is empty",false},
        {true,"$.arrayOfObjects is empty",false},
        {true,"$.names is empty",false},
        {true,"$.emptyString is empty",true},
        {true,"$.emptyArray is empty",true},
        {true,"$.names exists",true},
        {true,"not $.names exists",false},

        {true,"$.emptyObject exists",true},
        {false,"\\\"$.xyz\" exists ",false},
        {true,"\"$.options[?(@.code contains 'x')].area\" exists",false},
        {true, "\"$.options[?(@.code contains \\\"AB1\\\")].area\" exists", true},
        {true, "\"$.options[?(@.code == 'AB1')].area\" exists", true},
        {true, "\"$.options[?(@.code contains \\\"x\\\")].area\" exists", false},

        {true, "\"negativeNumber\" exists", true},
        {true, "\"$.xyz\" exists", false},
        {true, "\"$.xyz\" does not exist", true},
        {false, "\"$.foo\" is bar", false},
        {false, "\"$.foo\" contains bar", false},
        {true, "(\"status\" contains foo) or (\"status\" contains bar or (\"status\" contains app) ) ", true},
        {true, "\"status\" contains app", true},
        {true, "status contains app and status contains foo and status contains x", false},
        {true, "not not not status contains foo", true},
        {true, "not not not status contains foo and status contains app", true},
        {true, "applicationArea greater than 9.1", true},
        {true, "number greater than 1", true},
        {true, "negativeNumber greater than 1", false},
        {true, "foo does not exist", true},
        {true, "foo exists", false},
        {true, "status is approved", true},
        {true, "status is foo", false},
        {true, "status exists", true},
        {true, "\"array[?(@.ProductFields.aws/securityhub/ProductName == \\\"Security Hub\\\" )]\" exists", true},
        {true, "\"array[?(@.ProductFields.aws/securityhub/ProductName contains \\\"Security Hub\\\" )]\" exists", true},
        {true, "\"array[?(@.ProductFields.aws/securityhub/ProductName contains \\\"bar\\\" )]\" exists", false}
    });
  }

  @Test
  public void testRule() throws IOException {

    String message = "RULE: " + rule;

    try {
      // Outputs rule parsed as LISP style tree, useful for debugging
      System.out.println("Rule:" + rule);
      System.out.println("Rule tree: " + StringEscapeUtils.unescapeJava(ruleSetCompiler.getParseTreeAsString(rule)));

      Boolean result = ruleSetCompiler.getCompiledRuleSet(rule).isSatisfiedBy(sampleJson);
      if (!validRule) {
        fail("Expected invalid rule, " + rule);
      }

      if (expectedResult) {
        assertTrue("Expected true, " + message, result);
      } else {
        assertFalse("Expected false, " + message, result);
      }

    } catch (RuntimeException ex) {
      if (validRule) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);

        fail("Expected valid rule, " + rule + "\n" + sw.toString());
      }
    }
  }
}
