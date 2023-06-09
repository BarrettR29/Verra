package com.daclink.Verra.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.daclink.Verra.Cart;
import com.daclink.Verra.Smoothies;
import com.daclink.Verra.Users;

import java.util.List;

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

    @Query("SELECT * FROM " + AppDatabase.CART_TABLE)
    List<Cart> getAllCarts();

    @Query("SELECT * FROM " + AppDatabase.CART_TABLE + " WHERE cartID = :cartId")
    List<Cart> getCartById(int cartId);

    @Query("SELECT * FROM " + AppDatabase.CART_TABLE + " WHERE userID = :userId")
    List<Cart> getCartByUserID(int userId);
}
