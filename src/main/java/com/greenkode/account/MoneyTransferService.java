package com.greenkode.account;

public class MoneyTransferService {

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

        double total = transaction.getAmount() + transaction.getFee();

        from.setBalance(from.getBalance() - total);
        to.setBalance(to.getBalance() + transaction.getAmount());

        return transaction;
    }

    private Double calculateFee(SendMoneyTransaction transaction) {
        double fee = 0.0;
        if(transaction.getAmount() == null) {
            return fee;
        } else if (transaction.getAmount() > 100_000) {
            fee = 50;
        } else if (transaction.getAmount() > 500_000) {
            fee = 100;
        }
        return fee;
    }
}
