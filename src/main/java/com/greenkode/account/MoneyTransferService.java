package com.greenkode.account;

import java.time.Instant;

public class MoneyTransferService {

    private static final String KYC1 = "KYC1";
    private static final String KYC2 = "KYC2";
    private static final String KYC3 = "KYC3";

    private DatabaseRepository repository;

    public SendMoneyTransaction transferMoney(String fromNumber, String toNumber) {

        BankAccount from = repository.getAccountByName(fromNumber);
        if(from == null || from.getAccountBlacklisted().equals("true")) {
            return null;
        }

        BankAccount to = repository.getAccountByName(fromNumber);
        if(to == null || to.getAccountBlacklisted().equals("true")) {
            return null;
        }

        SendMoneyTransaction transaction = new SendMoneyTransaction();
        transaction.setFrom(from);
        transaction.setTo(to);
        transaction.setFee(calculateFee(transaction));
        transaction.setCreated(Instant.now());

        double total = transaction.getAmount() + transaction.getFee();
        repository.save(from);

        if(from.getKycLevel() == KYC1) {
            if(total > 30_000) {
                return null;
            }
        } else if (from.getKycLevel() == KYC2) {
            if(total > 100_000) {
                return null;
            }
        }

        if(to.getKycLevel() == KYC1) {
            if(transaction.getAmount() > 30_000) {
                return null;
            }
        } else if (to.getKycLevel() == KYC2) {
            if(transaction.getAmount() > 100_000) {
                return null;
            }
        }

        from.setBalance(from.getBalance() - total);
        to.setBalance(to.getBalance() + transaction.getAmount());
        repository.save(to);

        return transaction;
    }

    private Double calculateFee(SendMoneyTransaction transaction) {
        Double fee = null;
        if(transaction.getAmount() == null) {
            return fee;
        } else if (transaction.getAmount() > 100_000) {
            fee = 50.0;
        } else if (transaction.getAmount() > 500_000) {
            fee = 100.0;
        }
        return fee;
    }
}
