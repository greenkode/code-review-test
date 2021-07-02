package com.greenkode.account;

public interface DatabaseRepository {

    BankAccount getAccountByName(String accountNumber);
    void save(BankAccount from);
}
