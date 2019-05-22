package sample;

import java.util.ArrayList;
import java.util.Collections;

class Account implements Comparable {
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

    boolean setUserName(String userName) {
        for (Account account : accounts) {
            if (account.userName.equals(userName))
                return false;
        }
        this.userName = userName;
        return true;
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

    static int accountsSize() {
        return accounts.size();
    }

    static ArrayList<Account> getAccounts() {
        Collections.sort(accounts);
        return accounts;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Account)
            return ((Account) o).highScore - this.highScore;
        return 0;
    }
}
