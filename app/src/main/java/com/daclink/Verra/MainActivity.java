package com.daclink.Verra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences;

import com.daclink.Verra.DB.AppDatabase;
import com.daclink.Verra.DB.UsersDAO;
import com.daclink.Verra.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    TextView mainDisplay;
    Button adminButton;
    Button logOutButton;



    UsersDAO usersDAO;

    List<Users> usersList;

    public static Intent intentFactory(Context packageContext) {
        Intent intent = new Intent(packageContext, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainDisplay = binding.mainActivityDisplay;
        adminButton = binding.mainActivityAdminButton;
        logOutButton = binding.mainActivityLogOutButton;

        SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        if (prefs.getBoolean("is_logged_in", false)) {
            Intent intent = LogIn.intentFactory(getApplicationContext());
            startActivity(intent);
        }

        adminButton.setOnClickListener(view -> {
            Intent intent = Admin.intentFactory(getApplicationContext());
            startActivity(intent);
        });

        logOutButton.setOnClickListener(view -> {
            editor.putBoolean("is_logged_in", false);

            Intent intent = LogIn.intentFactory(getApplicationContext());
            startActivity(intent);
        });


        usersDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build().UsersDAO();
    }

}