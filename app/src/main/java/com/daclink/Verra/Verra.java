package com.daclink.Verra;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.daclink.Verra.DB.AppDatabase;

import java.util.Date;

@Entity(tableName =  AppDatabase.USER_TABLE)
public class Verra {

    @PrimaryKey(autoGenerate = true)
    private int userID;

    private String name;
    private String password;
    private boolean isAdmin;

    private static final String USER_TABLE = "verra";


    public Verra(String name, String password, boolean isAdmin) {
        this.name = name;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "User # " + userID + "\n" +
                "Name: " + name + "\n" +
                "Password: " + password + "\n" +
                "----------------" + "\n";
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
