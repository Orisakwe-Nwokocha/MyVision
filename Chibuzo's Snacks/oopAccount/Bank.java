package oopAccount;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String name;
    private List<Account> accounts;
    private int numberOfAccounts;

    public Bank(String name) {
        this.name = name;
        accounts = new ArrayList<>();
    }

    public Account registerCustomer(String firstName, String lastName, String pin) {
        String name = firstName + " " + lastName;
        int number = generateAccountNumber();
        Account account = new Account(name, number, pin);

        accounts.add(account);

        return account;
    }

    private int generateAccountNumber() {
         return ++numberOfAccounts;
    }

    public Account findAccount(int accountNumber) {
        for (Account account : accounts) if (isEqual(accountNumber, account)) return account;

        return null;
    }

    private boolean isEqual(int accountNumber, Account account) {
        return getAccountNumber(account) == accountNumber;
    }

    public int getAccountNumber(Account account) {
        return account.getAccountNumber();
    }

    public int checkBalance(int accountNumber, String pin) {
        ensureAccountExists(accountNumber);

        Account account = findAccount(accountNumber);

        return account.checkBalance(pin);
    }

    public void deposit(int accountNumber, int amount) {
        ensureAccountExists(accountNumber);

        Account account = findAccount(accountNumber);

        account.deposit(amount);
    }

    private void ensureAccountExists(int accountNumber) {
        Account account = findAccount(accountNumber);
        boolean accountIsNull = account == null;

        if (accountIsNull) throw new NullPointerException("Account number provided does not exist: " + accountNumber);
    }


    public void withdraw(int accountNumber, int amount, String pin) {
        ensureAccountExists(accountNumber);

        Account account = findAccount(accountNumber);

        account.withdraw(amount, pin);
    }

    public void transfer(int sourceAccountNumber, int destinationAccountNumber, int amount, String pin) {
        ensureAccountExists(sourceAccountNumber);
        ensureAccountExists(destinationAccountNumber);

        Account sourceAccount = findAccount(sourceAccountNumber);
        Account destinationAccount = findAccount(destinationAccountNumber);

        sourceAccount.withdraw(amount, pin);
        destinationAccount.deposit(amount);
    }

    public void removeAccount(int accountNumber, String pin) {
        ensureAccountExists(accountNumber);

        Account account = findAccount(accountNumber);

        if (account.isCorrect(pin)) accounts.remove(account);
    }
}
