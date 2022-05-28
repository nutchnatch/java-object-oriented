package com.oriented.branching;

import com.oriented.branching.accountstates.Active;

import java.math.BigDecimal;

public class Account {

//    private boolean isVerified;
//    private boolean isClosed;
//    private boolean isFrozen;
    private BigDecimal balance;
    private AccountState accountState;

    public Account(AccountUnfrozen onUnfrozen) {
        this.balance = BigDecimal.ZERO;
//        this.ensureUnfrozen = this.stayUnfrozen();
//        this.onUnfrozen = onUnfrozen;
        this.accountState = new Active(onUnfrozen);
    }

    public void holderVerified() {
        this.accountState.holderVerified();
//        this.isVerified = true;
    }

    public void closeAccount() {
        this.accountState.closeAccount();
//        this.isClosed = true;
    }

    public void freezeAccount() {
//        if(!this.isVerified) {
//            return;
//        }
//        if(this.isClosed) {
//            return;
//        }
//        this.isFrozen = true;
//        this.ensureUnfrozen.execute();
        this.accountState.freezeAccount();
    }

    public void deposit(BigDecimal amount) {
//        if(this.isClosed) {
//            return;
//        }
//        this.ensureUnfrozen.execute();
//        this.balance = this.balance.add(amount);
        this.accountState.deposit(amount, this::addToBalance);
    }

    private void addToBalance(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    private void subtractFromBalance(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }

    public void withdraw(BigDecimal amount) {
//        if(!this.isVerified) {
//            return;
//        }
//        if(this.isClosed) {
//            return;
//        }
//        this.ensureUnfrozen.execute();
//        this.balance.subtract(amount);
        this.accountState.withdraw(this.balance, amount, this::subtractFromBalance);
    }

//    private void unsureUnfrozen() {
//        if(isFrozen) { // guard condition
//            this.unfreeze();
//        } else {
//            stayUnfrozen();
//        }
////        if (this.isFrozen) {
////            this.isFrozen = false;
////        } else {
////            // do nothing
////        }
//        this.isFrozen = false;
//        this.onUnfrozen.handle();
//    }

//    private void stayUnfrozen() {
//
//    }

//    private void unfreeze() {
////        this.isFrozen = false;
//        this.onUnfrozen.handle();
//        this.ensureUnfrozen = this.stayUnfrozen();
//    }
}
