package com.daclink.Verra;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.daclink.Verra.DB.AppDatabase;

@Entity(tableName =  AppDatabase.CART_TABLE)
public class Cart {

    @PrimaryKey(autoGenerate = true)
    private int cartID;

    private Integer productCount;
    private Integer userID;

    public Cart(Integer productCount, Integer userID) {
        this.productCount = productCount;
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "cartID=" + cartID + "\n" +
                "productCount=" + productCount + "\n" +
                "userID='" + userID + "\n" +
                "----------------------------" + "\n";
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}
