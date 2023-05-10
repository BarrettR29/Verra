package com.daclink.Verra;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.daclink.Verra.DB.AppDatabase;
import com.daclink.Verra.DB.CartDAO;
import com.daclink.Verra.DB.UsersDAO;
import com.daclink.Verra.databinding.ActivityMainBinding;
import com.daclink.Verra.databinding.CartBinding;

import java.util.List;

import io.github.muddz.styleabletoast.StyleableToast;

public class CartActivity extends AppCompatActivity {

    CartBinding binding;

    TextView cartDisplay;
    Button cancelButton;
    Button checkOutButton;

    UsersDAO usersDAO;
    CartDAO cartDAO;

    List<Users> usersList;

    public static Intent intentFactory(Context packageContext) {
        Intent intent = new Intent(packageContext, CartActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        binding = CartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        cartDisplay = binding.cartActivityTextView;
        cancelButton = binding.cartActivityCancelButton;
        checkOutButton = binding.cartActivitySubmitButton;

        usersDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build().UsersDAO();

        cartDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build().CartDAO();

        SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);

        String loggedInUsername = prefs.getString("username", "_no_user");
        List<Users> user = usersDAO.getUserByName(loggedInUsername);
        List<Cart> userCart = cartDAO.getCartByUserID(user.get(0).getUserID());

        cartDisplay.setMovementMethod(new ScrollingMovementMethod());
        cartDisplay.setText(userCart.get(0).toString());
        cartDisplay.append("\nOrder total: $" + userCart.get(0).getProductCount() * 5);

        cancelButton.setOnClickListener(view -> {
            cancelOrder(userCart.get(0));
        });

        checkOutButton.setOnClickListener(view -> {
            completeOrder(userCart.get(0));
        });




    }

    private void cancelOrder(Cart cart) {
        cartDAO.delete(cart);
        cartDisplay.setText("");

        new StyleableToast
                .Builder(getApplicationContext())
                .text("Order canceled.")
                .textColor(Color.WHITE)
                .backgroundColor(Color.GREEN)
                .show();
    }

    private void completeOrder(Cart cart) {
        cartDAO.delete(cart);
        cartDisplay.setText("");

        new StyleableToast
                .Builder(getApplicationContext())
                .text("Order completed!")
                .textColor(Color.WHITE)
                .backgroundColor(Color.GREEN)
                .show();
    }
}
