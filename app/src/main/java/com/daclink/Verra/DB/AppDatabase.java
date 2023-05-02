package com.daclink.Verra.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.daclink.Verra.Cart;
import com.daclink.Verra.Smoothies;
import com.daclink.Verra.Users;

@Database(entities = {Users.class, Cart.class, Smoothies.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "verra.db";
    public static final String USER_TABLE = "user_table";
    public static final String CART_TABLE = "cart_table";
    public static final String SMOOTHIES_TABLE = "smoothies_table";


    private static volatile AppDatabase instance;
    private static final Object LOCK = new Object();

    public abstract UsersDAO UsersDAO();
    public abstract CartDAO CartDAO();
    public abstract SmoothiesDAO SmoothiesDAO();

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,
                            DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }

}
