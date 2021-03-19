package app.dassana.ruleengine;

import app.dassana.ruleengine.grammar.specification.CompositeSpecification;
import app.dassana.ruleengine.grammar.IRuleSet;
import app.dassana.ruleengine.grammar.RuleSet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class RuleSetTest {

    CompositeSpecification ruleReturnsTrue = mock(CompositeSpecification.class);
    CompositeSpecification ruleReturnsFalse = mock(CompositeSpecification.class);
    CompositeSpecification ruleAlsoReturnsTrue = mock(CompositeSpecification.class);

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
