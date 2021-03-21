package app.dassana.ruleengine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import app.dassana.ruleengine.grammar.IRuleSet;
import app.dassana.ruleengine.grammar.RuleSet;
import app.dassana.ruleengine.grammar.specification.AbstractSpecification;
import org.junit.Before;
import org.junit.Test;

public class RuleSetTest {

    AbstractSpecification ruleReturnsTrue = mock(AbstractSpecification.class);
    AbstractSpecification ruleReturnsFalse = mock(AbstractSpecification.class);
    AbstractSpecification ruleAlsoReturnsTrue = mock(AbstractSpecification.class);

    @Before
    public void setup() {
        when(ruleReturnsTrue.isSatisfiedBy(anyString())).thenReturn(true);
        when(ruleReturnsFalse.isSatisfiedBy(anyString())).thenReturn(false);
        when(ruleAlsoReturnsTrue.isSatisfiedBy(anyString())).thenReturn(true);
    }

    @Test
    public void isSatisfiedBy_true() {
        IRuleSet ruleSet = new RuleSet();
        ruleSet.addRule(ruleReturnsTrue);
        ruleSet.addRule(ruleAlsoReturnsTrue);

        assertTrue(ruleSet.isSatisfiedBy("json"));
    }

    @Test
    public void isSatisfiedBy_false() {
        IRuleSet ruleSet = new RuleSet();
        ruleSet.addRule(ruleReturnsTrue);
        ruleSet.addRule(ruleReturnsFalse);

        assertFalse(ruleSet.isSatisfiedBy("json"));
    }
}
