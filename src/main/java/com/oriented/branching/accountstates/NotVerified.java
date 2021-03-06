package com.oriented.branching.accountstates;

import com.oriented.branching.AccountState;
import com.oriented.branching.AccountUnfrozen;

import java.math.BigDecimal;
import java.util.function.Consumer;

public class NotVerified implements AccountState {
    private AccountUnfrozen onUnfrozen;

    public NotVerified(AccountUnfrozen onUnfrozen) {
        this.onUnfrozen = onUnfrozen;
    }

    @Override
    public AccountState deposit(BigDecimal amount, Consumer<BigDecimal> addToBalance) {
        addToBalance.accept(amount);
        return null;
    }

    @Override
    public AccountState withdraw(BigDecimal balance, BigDecimal amount, Consumer<BigDecimal> subtractFromBalance) {
        return null;
    }

    @Override
    public AccountState freezeAccount() {
        return null;
    }

    @Override
    public AccountState holderVerified() {
        return new Active(this.onUnfrozen);
    }

    @Override
    public AccountState closeAccount() {
        return new Closed();
    }
}
