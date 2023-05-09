package com.daclink.Verra;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.daclink.Verra.DB.AppDatabase;
import com.daclink.Verra.DB.UsersDAO;
import com.daclink.Verra.databinding.EditUserBinding;
import com.daclink.Verra.databinding.SignUpBinding;

import io.github.muddz.styleabletoast.StyleableToast;

public class EditUser extends AppCompatActivity {


    EditUserBinding binding;
    TextView textView;
    EditText userID;
    EditText username;
    EditText password;
    EditText isAdmin;
    Button submit;

    UsersDAO usersDAO;

    public static Intent intentFactory(Context packageContext) {
        Intent intent = new Intent(packageContext, EditUser.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user);

        binding = EditUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        textView = binding.editUsertextView;
        userID = binding.editUserIDEditText;
        username = binding.editUserUsernameEditText;
        password = binding.editUserPasswordEditText;
        isAdmin = binding.editUserIsAdminEditText;
        submit = binding.editUserSubmitButton;
        usersDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build().UsersDAO();


        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setText(usersDAO.getAllUsers().toString());


        submit.setOnClickListener(view -> {
            updateUser();
        });
    }

    private void updateUser() {
        String tempIDString = userID.getText().toString();
        int parsedInt = Integer.parseInt(tempIDString);

        String tempBoolString = isAdmin.getText().toString();
        boolean parsedBool = Boolean.parseBoolean(tempBoolString);

        if (usersDAO.getUserById(parsedInt) == null) {
            new StyleableToast
                    .Builder(getApplicationContext())
                    .text("User doesn't exist!")
                    .textColor(Color.WHITE)
                    .backgroundColor(Color.RED)
                    .show();
        }
        else {
            Users updatedUser = new Users(username.getText().toString(), password.getText().toString(), parsedBool);
            updatedUser.setUserID(parsedInt);

            usersDAO.update(updatedUser);
            textView.setText(usersDAO.getAllUsers().toString());

            new StyleableToast
                    .Builder(getApplicationContext())
                    .text("User " + userID.getText().toString() + " updated!")
                    .textColor(Color.WHITE)
                    .backgroundColor(Color.GREEN)
                    .show();
        }
    }
}

