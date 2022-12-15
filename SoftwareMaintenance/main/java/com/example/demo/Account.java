package com.example.demo;

import java.util.ArrayList;

public class Account implements Comparable<Account> {
    private long score = 0;
    private String userName ;
    private static ArrayList<Account> accounts = new ArrayList<>();

    public Account(String userName){
        this.userName=userName;
    }

    @Override
    public int compareTo(Account o) {
        return Long.compare(o.getScore(), score);
    }

    // Removed addToScore method

    public long getScore() { // Changed method from private to public
        return score;
    }

    public String getUserName() { // Changed method from private to public
        return userName;
    }

    // Removed accountExists method

    static Account makeNewAccount(String userName){
        Account account = new Account(userName);
        accounts.add(account);
        return account;
    }
}
