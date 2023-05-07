package com.daclink.Verra;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
//import com.daclink.Verra.databinding.ActivityMainBinding;
import com.daclink.Verra.databinding.AdminBinding;


public class Admin extends AppCompatActivity {

    AdminBinding binding;

    Button editUsers;
    Button editOrders;


    public static Intent intentFactory(Context packageContext) {
        Intent intent = new Intent(packageContext, Admin.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);

        binding = AdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        editUsers = binding.adminActivityEditUsersBtn;
        editOrders = binding.adminActivityEditOrdersBtn;

        editUsers.setOnClickListener(view -> {
            Intent intent = EditUser.intentFactory(getApplicationContext());
            startActivity(intent);
        });


    }

}
