package com.daclink.Verra.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.daclink.Verra.Verra;

import java.util.List;

@Dao
public interface VerraDAO {
    @Insert
    void insert(Verra... verras);


    @Update
    void update(Verra... verras);


    @Delete
    void delete(Verra verra);


    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE userID = :userId")
    List<Verra> getUserById(int userId);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE name = :name")
    List<Verra> getUserByName(String name);

    @Query("SELECT COUNT(*) FROM " + AppDatabase.USER_TABLE)
    int count();

}
