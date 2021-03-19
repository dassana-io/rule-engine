package app.dassana.ruleengine.grammar.specification;

import app.dassana.ruleengine.IJsonPathParser;
import app.dassana.ruleengine.JsonPathParser;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NotSpecificationTest {

    IJsonPathParser jsonPathParser = new JsonPathParser();

    ISpecification specification = mock(ISpecification.class);

    @Test
    public void isSatisfiedBy_true() {
        when(specification.isSatisfiedBy(anyString())).thenReturn(false);
        assertTrue((new NotSpecification(jsonPathParser, specification)).isSatisfiedBy(""));
    }

    @Test
    public void isSatisfiedBy_false() {
        when(specification.isSatisfiedBy(anyString())).thenReturn(true);
        assertFalse((new NotSpecification(jsonPathParser, specification)).isSatisfiedBy(""));
    }
}
