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
import com.daclink.Verra.DB.VerraDAO;
import com.daclink.Verra.databinding.ActivityMainBinding;
import com.daclink.Verra.databinding.LogInBinding;

import java.util.List;

public class LogIn extends AppCompatActivity {
    private static final String INTENT_KEY_STRING = "com.daclink.Verra_INTENT_KEY_STRING";

    LogInBinding binding;

    EditText username;
    EditText password;
    TextView error;
    Button signIn;

    VerraDAO verraDAO;

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

        verraDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build().VerraDAO();

        if (verraDAO.count() < 1) {
            Log.d(INTENT_KEY_STRING, "No entries in db - creating defaults");
            String testuser1 = "testuser1";
            String admin2 = "admin2";
            Verra user1 = new Verra(testuser1, testuser1, false);
            Verra user2 = new Verra(admin2,admin2, true);

            verraDAO.insert(user1);
            verraDAO.insert(user2);
        }

        signIn.setOnClickListener(view -> {
            trySignIn(prefs);
//            refreshDisplay();
        });

    }

    private void trySignIn(SharedPreferences prefs) {
        String name = username.getText().toString();
        String pass = password.getText().toString();
        List<Verra> userList;

        userList = verraDAO.getUserByName(name);

        SharedPreferences.Editor editor = prefs.edit();

        if ((userList != null) && userList.size() > 0) {

            if (userList.get(0).getPassword().equals(pass)) {
                editor.putString("username", name);
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


