package com.greenkode.account;

public class MoneyTransferService {

    private DatabaseRepository repository;

    public void transferMoney(String fromNumber, String toNumber) {

        BankAccount from = repository.getAccountByName(fromNumber);
        BankAccount to = repository.getAccountByName(fromNumber);

        SendMoneyTransaction transaction = new SendMoneyTransaction();
        transaction.setFrom(from);
        transaction.setTo(to);
        transaction.setFee(calculateFee(transaction));

        double total = transaction.getAmount() + transaction.getFee();

        from.setBalance(from.getBalance() - total);
        to.setBalance(to.getBalance() + transaction.getAmount());
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
