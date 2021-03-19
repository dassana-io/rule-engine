package app.dassana.ruleengine.grammar.specification.bool;

import app.dassana.ruleengine.IJsonPathParser;
import app.dassana.ruleengine.JsonPathParser;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IsTrueTest {

    IJsonPathParser jsonPathParser = new JsonPathParser();

    @Test
    public void isSatisfiedBy_true() {
        assertTrue((new IsTrue(jsonPathParser, "flag")).isSatisfiedBy("{\"flag\": \"true\"}"));
    }

    @Test
    public void isSatisfiedBy_false() {
        assertFalse((new IsTrue(jsonPathParser, "flag")).isSatisfiedBy("{\"flag\": \"false\"}"));
    }
}
