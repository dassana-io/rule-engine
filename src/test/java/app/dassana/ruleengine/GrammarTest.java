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
          Charset.defaultCharset()); //use sh-notification.json when you want to use real life example
   /*IOUtils.toString(Thread.currentThread().getContextClassLoader().getResourceAsStream("sh-notification.json"),
          Charset.defaultCharset())
*/
  public GrammarTest(boolean validRule, String rule, boolean expectedResult) throws IOException {
    this.validRule = validRule;
    this.rule = rule;
    this.expectedResult = expectedResult;
  }

  @Parameterized.Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][]{
       // {true, "$.detail.findings[?(@.Workflow.Status contains \"N\")] exists", true},
        /* Valid rules. */
        {true, "$.options[?(@.code contains \"AB1\")].area exists", true},
        {true, "$.options[?(@.code == 'AB1')].area exists", true},
        {true, "$.options[?(@.code contains \"x\")].area exists", false},

        {true, "negativeNumber exists", true},
        {true, "$.xyz exists", false},
        {true, "(status contains foo) or (status contains bar or (status contains app) ) ", true},
        {true, "status contains app", true},
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

/*        {true, "emptyString is not empty", false},
        {true, "emptyObject is not empty", false},
        {true, "applicationArea is not empty", true},
        {true, "options is not empty", true},
        {true, "organic is not empty", true},
        {true, "landUseCodes is not empty", true},
           {true,"foo exists",false},
           {true,"applicationArea exists",true},
           {true,"$.applicationArea exists",true},
          ,


           {true, "applicationArea greater than 7.00", true}, // GreaterThan
           {true, "applicationArea greater than 9.1", false},

           {true, "applicationArea less than 9.99", true},    // LessThan
           {true, "applicationArea less than -1.00", false},

           {true, "status equals approved", true},            // StringEquals
           {true, "status equals rejected", false},

           {true, "landUseCodes contains GH", true},          // StringContains
           {true, "landUseCodes contains XX", false},

           {true, "organic is true", false},                  // IsTrue
           {true, "organic is false", true},                  // IsFalse

           {true, "not status equals rejected", true},        // NotSpecification
           {true, "not status equals approved", false},

           {true, "applicationArea greater than 7.00 and applicationArea less than 9.99", true}, // AndSpecification
           {true, "applicationArea greater than 9.99 and applicationArea less than 7.00", false},

           {true, "status equals approved or landUseCodes contains XX", true},        // OrSpecification
           {true, "status equals rejected or landUseCodes contains XX", false},

           {true, "applicationArea greater than 7.00\nstatus equals approved", true}, // Composite
           {true, "applicationArea greater than 7.00\nstatus equals rejected", false},

           {true, "$.applicationArea greater than 1", true},                     // JsonPath dot notation
           {true, "$.options[0].area greater than 4", false},
           {true, "$.options[?(@.code=='AB1')].area equals 3", true},
           {true, "$.options[?(@.code=='AB8')].area less than 3", true},

           {true, "$.options[*].code includes one (AB1,XX99)", true}, // ArrayIncludesOne
           {true, "$.options[*].code includes one (XX99)", false},

           {true, "SUM($.options[*].area) greater than 4", true},            // NumericTotalledJsonPathExpression
           {true, "SUM($.options[*].area) less than applicationArea", true}, // NumericTotalledJsonPathExpression with rightArithmeticExpression

           {true, "(4 + 2) greater than (1 + 2)", true},
           {true, "(4 + 2) greater than 3", true},
           {true, "6 greater than (1 + 2)", true},
           {true, "6 greater than 3", true},

           {true, "(4 + 2) less than (1 + 8)", true},
           {true, "(4 + 2) less than 9", true},
           {true, "6 less than (1 + 8)", true},
           {true, "6 less than 9", true},*/

        //Invalid rules.
        /* {false, "", false} // empty rule*/
    });
  }

  @Test
  public void testRule() throws IOException {

    String message = "RULE: " + rule;

    try {
      // Outputs rule parsed as LISP style tree, useful for debugging
      System.out.println("Rule tree: " + ruleSetCompiler.getParseTreeAsString(rule));

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
