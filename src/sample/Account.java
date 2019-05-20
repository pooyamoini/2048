package sample;

import java.util.ArrayList;

public class Account {
    private int highScore;
    private String userName;
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static Account currentAccount;

    private Account(String userName) {
        this.highScore = 0;
        this.userName = userName;
    }

    static boolean addAccount(String userName) {
        for (Account account1 : accounts) {
            if (account1.userName.equals(userName))
                return false;
        }
        Account account = new Account(userName);
        accounts.add(account);
        return true;
    }

    int getHighScore() {
        return highScore;
    }

    void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    String getUserName() {
        return userName;
    }

    void setUserName(String userName) {
        this.userName = userName;
    }

    static boolean login(String userName) {
        for (Account account : accounts) {
            if (account.getUserName().equals(userName)) {
                currentAccount = account;
                return true;
            }
        }
        return false;
    }

    static Account getCurrentAccount() {
        return currentAccount;
    }

    static int accountsSize(){
        return accounts.size();
    }

    static ArrayList<Account> getAccounts() {
        return accounts;
    }
}
