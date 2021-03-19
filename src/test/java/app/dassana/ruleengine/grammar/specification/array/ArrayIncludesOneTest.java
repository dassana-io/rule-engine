package app.dassana.ruleengine.grammar.specification.array;

import app.dassana.ruleengine.IJsonPathParser;
import app.dassana.ruleengine.JsonPathParser;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ArrayIncludesOneTest {

    IJsonPathParser jsonPathParser = new JsonPathParser();

    @Test
    public void isSatisfiedBy_true() {
        assertTrue((
            new ArrayIncludesOne(jsonPathParser, "options", new ArrayList<String>() {{
                add("R1");
                add("R2");
            }}))
            .isSatisfiedBy("{\"options\": [\"R1\", \"R3\"]}"));
    }

    @Test
    public void isSatisfiedBy_false() {
        assertFalse((
            new ArrayIncludesOne(jsonPathParser, "options", new ArrayList<String>() {{
                add("R1");
                add("R2");
            }}))
            .isSatisfiedBy("{\"options\": [\"NOPE\", \"NAH\"]}"));
    }
}
