package com.bartek.creditcard;

import com.bartek.creditcard.exception.*;

import java.math.BigDecimal;

public class CreditCard {
    private BigDecimal limit;
    private boolean blocked;
    private BigDecimal moneyAmount;
    private BigDecimal balance;

    public CreditCard() {
        this(BigDecimal.ZERO);
    }

    public CreditCard(BigDecimal balance) {
        this.balance = balance;
    }

    public void assignLimit(BigDecimal newLimit) {
        if (limit != null) {
            throw new CardLimitAlreadyAssignedException();
        }
        if (newLimit.compareTo(BigDecimal.ZERO) <= 0) {
            throw new NegativeLimitAssignException();
        }
        this.limit = newLimit;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void block() {
        blocked = true;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void withdraw(BigDecimal moneyAmount) {
        if (isBalanceTooLow(moneyAmount)) {
            throw new NotEnaughBalanceException();
        }
        if (moneyAmount.compareTo(limit) > 0) {
            throw new LimitExceededException();
        }
        if (blocked) {
            throw new CardBlockedException();
        }
        balance = balance.subtract(moneyAmount);
    }

    private boolean isBalanceTooLow(BigDecimal moneyAmount) {
        return moneyAmount.compareTo(balance) > 0;
    }

    public void repay(BigDecimal moneyAmount) {
        if (moneyAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativeMoneyToRepayException();
        }
        balance = balance.add(moneyAmount);
    }
}
