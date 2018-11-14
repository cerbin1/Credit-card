package com.bartek.creditcard;

import com.bartek.creditcard.exception.CardLimitAlreadyAssignedException;

import java.math.BigDecimal;

public class CreditCard {
    private BigDecimal limit;

    public void assignLimit(BigDecimal newLimit) {
        if (limit != null) {
            throw new CardLimitAlreadyAssignedException();
        }
        this.limit = newLimit;
    }

    public BigDecimal getLimit() {
        return limit;
    }
}
