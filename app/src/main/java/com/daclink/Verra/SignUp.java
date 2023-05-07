package com.daclink.Verra;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.daclink.Verra.DB.AppDatabase;
import com.daclink.Verra.DB.UsersDAO;
import com.daclink.Verra.databinding.AdminBinding;
import com.daclink.Verra.databinding.SignUpBinding;

import io.github.muddz.styleabletoast.StyleableToast;

public class SignUp extends AppCompatActivity {

    SignUpBinding binding;

    EditText username;
    EditText firstPassword;
    EditText secondPassword;
    Button signUp;

    UsersDAO usersDAO;


    public static Intent intentFactory(Context packageContext) {
        Intent intent = new Intent(packageContext, SignUp.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        binding = SignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        username = binding.signUpEditTextUsername;
        firstPassword = binding.signUpEditTextPassword;
        secondPassword = binding.signUpEditTextConfirmPassword;
        signUp = binding.signUpButton;

        usersDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build().UsersDAO();



        signUp.setOnClickListener(view -> {
            trySignUp();
        });
    }


    private void trySignUp() {
        if (!firstPassword.getText().toString().equals(secondPassword.getText().toString())) {
            new StyleableToast
                    .Builder(getApplicationContext())
                    .text("Passwords don't match!")
                    .textColor(Color.WHITE)
                    .backgroundColor(Color.RED)
                    .show();
        }
        else {
            Users newUser = new Users(username.getText().toString(), firstPassword.getText().toString(), false);
            usersDAO.insert(newUser);
        }
    }

}
