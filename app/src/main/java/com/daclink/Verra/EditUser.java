package com.daclink.Verra;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.daclink.Verra.DB.UsersDAO;
import com.daclink.Verra.databinding.EditUserBinding;
import com.daclink.Verra.databinding.SignUpBinding;

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

        textView.setText(usersDAO.getAllUsers().toString());

    }
}
