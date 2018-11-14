package com.bartek.creditcard;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreditCardTest {
    @Test
    public void shouldAllowAssignLimitToCard() {
        // given
        CreditCard creditCard = new CreditCard();

        // when
        creditCard.setLimit(2000);

        // then
        assertEquals(2000, creditCard.getLimit());
    }
}
