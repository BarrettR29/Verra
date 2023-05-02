package com.daclink.Verra.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.daclink.Verra.Cart;
import com.daclink.Verra.Smoothies;

@Dao
public interface CartDAO {


    @Insert
    void insert(Cart... cart);


    @Update
    void update(Cart... cart);


    @Delete
    void delete(Cart cart);

    @Query("SELECT COUNT(*) FROM " + AppDatabase.CART_TABLE)
    int count();
}
