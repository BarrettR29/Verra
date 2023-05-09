package com.daclink.Verra;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.daclink.Verra.DB.AppDatabase;
import com.daclink.Verra.DB.CartDAO;
import com.daclink.Verra.DB.UsersDAO;
import com.daclink.Verra.databinding.LogInBinding;

import java.util.List;

public class LogIn extends AppCompatActivity {
    private static final String INTENT_KEY_STRING = "com.daclink.Verra_INTENT_KEY_STRING";

    LogInBinding binding;

    EditText username;
    EditText password;
    TextView error;
    Button signIn;
    Button signUp;

    UsersDAO usersDAO;
    CartDAO cartDAO;

    public static Intent intentFactory(Context packageContext) {
        Intent intent = new Intent(packageContext, LogIn.class);
//        intent.putExtra()
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        binding = LogInBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        // Shared Preferences login
        SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("is_logged_in", false);
        editor.commit();

        if (prefs.getBoolean("is_logged_in", true)) {
            Intent intent = MainActivity.intentFactory(getApplicationContext());
            startActivity(intent);
        }

        username = binding.loginEditTextUsername;
        password = binding.loginEditTextPassword;
        signIn = binding.loginSignInButton;
        error = binding.logInErrorText;
        signUp = binding.loginSignUpButton;

        usersDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build().UsersDAO();

        cartDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build().CartDAO();

        if (usersDAO.count() < 1) {
            Log.d(INTENT_KEY_STRING, "No entries in users db - creating defaults");
            String testuser1 = "testuser1";
            String admin2 = "admin2";
            Users user1 = new Users(testuser1, testuser1, false);
            Users user2 = new Users(admin2,admin2, true);

            usersDAO.insert(user1);
            usersDAO.insert(user2);
        }

        if (cartDAO.count() < 1) {
            Log.d(INTENT_KEY_STRING, "No entries in cart db - creating defaults");
            Cart cart1 = new Cart(1, "testuser1");

            cartDAO.insert(cart1);
        }


        signUp.setOnClickListener(view -> {
            Intent intent = SignUp.intentFactory(getApplicationContext());
            startActivity(intent);
        });


        signIn.setOnClickListener(view -> {
            trySignIn(prefs);
//            refreshDisplay();
        });

    }

    private void trySignIn(SharedPreferences prefs) {
        String name = username.getText().toString();
        String pass = password.getText().toString();
        List<Users> userList;

        userList = usersDAO.getUserByName(name);

        SharedPreferences.Editor editor = prefs.edit();

        if ((userList != null) && userList.size() > 0) {

            if (userList.get(0).getPassword().equals(pass)) {
                editor.putString("username", name);
                editor.commit();
//                Log.d("aasdsadas1", prefs.getString("username", "error"));
                Intent intent = MainActivity.intentFactory(getApplicationContext());
                startActivity(intent);
            }
            else {

            }
        }
        else {
            Log.d(INTENT_KEY_STRING, "test");
            error.setVisibility(View.VISIBLE);
        }
    }



}


