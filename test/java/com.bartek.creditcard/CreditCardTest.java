package com.bartek.creditcard;

import com.bartek.creditcard.exception.CardLimitAlreadyAssignedException;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CreditCardTest {
    private static BigDecimal toBigDecimal(long value) {
        return BigDecimal.valueOf(value);
    }

    @Test
    public void shouldAllowAssignLimitToCard() {
        // given
        CreditCard creditCard = new CreditCard();

        // when
        creditCard.assignLimit(toBigDecimal(2000));

        // then
        assertEquals(toBigDecimal(2000), creditCard.getLimit());
    }

    @Test(expected = CardLimitAlreadyAssignedException.class)
    public void shouldNotReassignLimit() {
        // given
        CreditCard creditCard = new CreditCard();
        creditCard.assignLimit(toBigDecimal(2000));

        // when
        creditCard.assignLimit(toBigDecimal(2000));

        // then
        assertEquals(toBigDecimal(2000), creditCard.getLimit());
    }

    @Test(expected = NegativeLimitAssignException.class)
    public void shouldNotAssignNegativeLimit() {
        // given
        CreditCard creditCard = new CreditCard();

        // when
        creditCard.assignLimit(toBigDecimal(-10));
    }

    @Test
    public void shouldBlockCard() {
        // given
        CreditCard creditCard = new CreditCard();
        boolean creditCardStatusBeforeBlock = creditCard.isBlocked();

        // when
        creditCard.block();

        // then
        assertFalse(creditCardStatusBeforeBlock);
        Assert.assertTrue(creditCard.isBlocked());
    }
}
