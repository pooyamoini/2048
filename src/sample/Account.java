package sample;

import java.util.ArrayList;

public class Account {
    private int highScore;
    private String userName;
    private static ArrayList<Account> Accounts = new ArrayList<>();

    private Account(String userName) {
        this.highScore = 0;
        this.userName = userName;
    }

    static boolean addAccount(String userName){
        for (Account account1 : Accounts) {
            if (account1.userName.equals(userName))
                return false;
        }
        Account account = new Account(userName);
        Accounts.add(account);
        return true;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
