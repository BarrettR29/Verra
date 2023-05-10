package com.daclink.Verra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.SharedPreferences;

import com.daclink.Verra.DB.AppDatabase;
import com.daclink.Verra.DB.CartDAO;
import com.daclink.Verra.DB.UsersDAO;
import com.daclink.Verra.databinding.ActivityMainBinding;

import java.util.List;

import io.github.muddz.styleabletoast.StyleableToast;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    TextView mainDisplay;
    Button adminButton;
    Button logOutButton;
    ImageView cartImg;
    ImageView mangoImg;
    ImageView strawberryImg;
    ImageView blueberryImg;
    ImageView kaleImg;

    UsersDAO usersDAO;
    CartDAO cartDAO;

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
        cartImg = binding.mainActivityCartImg;
        mangoImg = binding.mainActivityMangoImg;
        strawberryImg = binding.mainActivityStrBanImg;
        blueberryImg = binding.mainActivityBlueberryImg;
        kaleImg = binding.mainActivityKaleImg;

        usersDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build().UsersDAO();

        cartDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build().CartDAO();


        SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        if (prefs.getBoolean("is_logged_in", false)) {
            Intent intent = LogIn.intentFactory(getApplicationContext());
            startActivity(intent);
        }


        String loggedInUsername = prefs.getString("username", "_no_user");
        List<Users> user = usersDAO.getUserByName(loggedInUsername);

        mainDisplay.setText("Hello, " + loggedInUsername);

        if (user.get(0).isAdmin()) {
            adminButton.setVisibility(View.VISIBLE);
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

        cartImg.setOnClickListener(view -> {
            Intent intent = CartActivity.intentFactory(getApplicationContext());
            startActivity(intent);
        });

        mangoImg.setOnClickListener(view -> {
            addToCart(user.get(0));
        });
        strawberryImg.setOnClickListener(view -> {
            addToCart(user.get(0));
        });
        kaleImg.setOnClickListener(view -> {
            addToCart(user.get(0));
        });
        blueberryImg.setOnClickListener(view -> {
            addToCart(user.get(0));
        });
    }

    private void addToCart(Users user) {

        List<Cart> newCartList = cartDAO.getCartByUserID(user.getUserID());

        Cart newCart = new Cart(-1, -1);


        if (newCartList.size() > 0) {
            newCart = newCartList.get(0);
            if (newCart.getProductCount() < 5) {
                newCart.setProductCount(newCart.getProductCount() + 1);
                cartDAO.update(newCart);
            }
            else {
                new StyleableToast
                        .Builder(getApplicationContext())
                        .text("Order limit reached!")
                        .textColor(Color.WHITE)
                        .backgroundColor(Color.RED)
                        .show();
                return;
            }
        }
        else {
            newCart.setProductCount(1);
            newCart.setUserID(user.getUserID());
            cartDAO.insert(newCart);
        }

        new StyleableToast
                .Builder(getApplicationContext())
                .text("Item added to cart")
                .textColor(Color.WHITE)
                .backgroundColor(Color.GREEN)
                .show();

    }

}