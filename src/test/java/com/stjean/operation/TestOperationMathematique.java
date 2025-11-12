package com.stjean.operation;

import org.junit.Assert;
import org.testng.annotations.Test;

class TestOperationMathematique {

    @Test
    void estPositif_returnsTrue_forZeroAndPositive() {
        var op = new OperationMathematique();
        Assert.assertTrue(op.estPositif(0));
        Assert.assertTrue(op.estPositif(7));
        Assert.assertFalse(op.estPositif(-1));
    }

    @Test
    void factoriel_ok_forSmallValues() {
        var op = new OperationMathematique();
        Assert.assertEquals(1, op.factoriel(0));   // AAA: Arrange/Act/Assert
        Assert.assertEquals(1, op.factoriel(1));
        Assert.assertEquals(120, op.factoriel(5));
    }
}
