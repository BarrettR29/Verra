package com.daclink.Verra.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.daclink.Verra.Users;

import java.util.List;

@Dao
public interface UsersDAO {
    @Insert
    void insert(Users... users);


    @Update
    void update(Users... users);


    @Delete
    void delete(Users users);


    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE userID = :userId")
    List<Users> getUserById(int userId);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE name = :name")
    List<Users> getUserByName(String name);


    @Query("SELECT COUNT(*) FROM " + AppDatabase.USER_TABLE)
    int count();

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE)
    List<Users> getAllUsers();


}
