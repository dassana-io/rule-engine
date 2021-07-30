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

  private final RuleSetCompiler ruleSetCompiler = new RuleSetCompiler(new JqPathParser());
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
        {true, "\".organic == false\"", true},
        {true, "\".organic == true\"", false},
        {true, ".status is approved", true},
        {true, ".status contains app", true},
        {true, ".status contains foo", false},
        {true, ".status is foo", false},
        {true, ".organic is true", false},
        {true, ".organic is false", true},
        {true, ".trueKey is true", true},
        {false, ".trueKey is blah", false},
        {true, ".applicationArea greater than 0", true},
        {true, ".applicationArea greater than 10", false},
        {true, ".number greater than 0 and .organic is false", true},
        {true, ".trueKey is true or .organic is true", true},
        {true, ".trueKey is true or .organic is false", true},
        {true, ".trueKey is false or .organic is true", false},
        {true, "not .trueKey is false", true},
        {true, "not not .trueKey is false", false},
        {true, "not not not .trueKey is false", true},
        {true, ".blah exists", false},
        {true, ".blah does not exist", true},
        {true, ".emptyObject is empty", true},
        {true, ".emptyArray is empty", true},
        {true, ".emptyString is empty", true},
        {true, ".array is empty", false},
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
