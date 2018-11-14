package com.bartek.creditcard;

import com.bartek.creditcard.exception.CardBlockedException;
import com.bartek.creditcard.exception.CardLimitAlreadyAssignedException;
import com.bartek.creditcard.exception.LimitExceededException;
import com.bartek.creditcard.exception.NotEnaughBalanceException;
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

    @Test
    public void shouldWithdrawMoney() {
        // given
        CreditCard creditCard = new CreditCard(toBigDecimal(1000));
        creditCard.assignLimit(toBigDecimal(1000));
        BigDecimal balanceBefore = creditCard.getBalance();

        // when
        creditCard.withdraw(toBigDecimal(500));

        // then
        assertEquals(toBigDecimal(1000), balanceBefore);
        assertEquals(toBigDecimal(500), creditCard.getBalance());
    }

    @Test(expected = NotEnaughBalanceException.class)
    public void shouldNotWithdrawWhenNotEnoughBalance() {
        // given
        CreditCard creditCard = new CreditCard();

        // when
        creditCard.withdraw(toBigDecimal(500));
    }

    @Test(expected = LimitExceededException.class)
    public void shouldNotWithdrawWhenMoneyExceedLimit() {
        // given
        CreditCard creditCard = new CreditCard(toBigDecimal(2000));
        creditCard.assignLimit(toBigDecimal(1000));

        // when
        creditCard.withdraw(toBigDecimal(2000));
    }

    @Test(expected = CardBlockedException.class)
    public void shouldNotWithdrawWhenCardIsBlocked() {
        // given
        CreditCard creditCard = new CreditCard(toBigDecimal(1000));
        creditCard.assignLimit(toBigDecimal(1000));
        creditCard.block();

        // when
        creditCard.withdraw(toBigDecimal(500));
    }
}
