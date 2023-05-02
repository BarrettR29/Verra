package com.daclink.Verra.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.daclink.Verra.Smoothies;
import com.daclink.Verra.Users;

@Dao
public interface SmoothiesDAO {

    @Insert
    void insert(Smoothies... smoothies);


    @Update
    void update(Smoothies... smoothies);


    @Delete
    void delete(Smoothies smoothies);



}
