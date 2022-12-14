package com.example.demo;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;

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
