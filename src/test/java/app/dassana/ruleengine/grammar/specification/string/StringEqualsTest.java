package app.dassana.ruleengine.grammar.specification.string;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import app.dassana.ruleengine.IJsonPathParser;
import app.dassana.ruleengine.JsonPathParser;
import org.junit.Test;

public class StringEqualsTest {

    IJsonPathParser jsonPathParser = new JsonPathParser();

    @Test
    public void isSatisfiedBy_true() {
        assertTrue((new StringContains(jsonPathParser, "code", "C1")).isSatisfiedBy("{\"code\": \"C1\"}"));
    }

    @Test
    public void isSatisfiedBy_false() {
        assertFalse((new StringContains(jsonPathParser, "code", "NOPE")).isSatisfiedBy("{\"code\": \"C1\"}"));
    }
}
