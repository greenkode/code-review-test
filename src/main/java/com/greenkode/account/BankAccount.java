package com.greenkode.account;

public class BankAccount {

    private String accountNumber;
    private String name;
    private Double balance;
    private String accountBlacklisted;
    private String kycLevel;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getAccountBlacklisted() {
        return accountBlacklisted;
    }

    public void setAccountBlacklisted(String accountBlacklisted) {
        this.accountBlacklisted = accountBlacklisted;
    }

    public String getKycLevel() {
        return kycLevel;
    }

    public void setKycLevel(String kycLevel) {
        this.kycLevel = kycLevel;
    }
}
