package com.oriented.branching.accountstates;

import com.oriented.branching.AccountState;
import com.oriented.branching.AccountUnfrozen;
import com.oriented.branching.accountstates.Active;

import java.math.BigDecimal;
import java.util.function.Consumer;

public class Frozen implements AccountState {
    private AccountUnfrozen onUnfrozen;

    public Frozen(AccountUnfrozen onUnfrozen) {
        this.onUnfrozen = onUnfrozen;
    }

    @Override
    public AccountState deposit(BigDecimal amount, Consumer<BigDecimal> addToBalance) {
        addToBalance.accept(amount);
        return unfreeze();
    }

    private AccountState unfreeze() {
        this.onUnfrozen.handle();
        return new Active(this.onUnfrozen);
    }

    @Override
    public AccountState withdraw(BigDecimal balance, BigDecimal amount, Consumer<BigDecimal> subtractFromBalance) {
        if(balance.compareTo(amount) >= 0) {
            subtractFromBalance.accept(amount);
        }
        return unfreeze();
    }

    @Override
    public AccountState freezeAccount() {
        return this;
    }

    @Override
    public AccountState holderVerified() {
        return this;
    }

    @Override
    public AccountState closeAccount() {
        return new Closed();
    }
}
