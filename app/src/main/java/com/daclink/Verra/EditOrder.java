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
import com.daclink.Verra.DB.CartDAO;
import com.daclink.Verra.DB.UsersDAO;
import com.daclink.Verra.databinding.EditOrderBinding;
import com.daclink.Verra.databinding.EditUserBinding;

import io.github.muddz.styleabletoast.StyleableToast;

public class EditOrder extends AppCompatActivity {


    EditOrderBinding binding;
    TextView textView;
    EditText cartID;
    EditText productCount;
    EditText userID;
    Button submit;

    CartDAO cartDAO;

    public static Intent intentFactory(Context packageContext) {
        Intent intent = new Intent(packageContext, EditOrder.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user);

        binding = EditOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        textView = binding.editOrderTextView;
        cartID = binding.editOrderCartIdEditText;
        productCount = binding.editOrderCountEditText;
        userID = binding.editOrderUserIdEditText;
        submit = binding.editOrderSubmitButton;

        cartDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build().CartDAO();

        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setText(cartDAO.getAllCarts().toString());


        submit.setOnClickListener(view -> {
            updateCart();
        });
    }

    private void updateCart() {
        String tempIDString = cartID.getText().toString();
        int parsedCartID = Integer.parseInt(tempIDString);

        String tempCountString = productCount.getText().toString();
        int parsedCount = Integer.parseInt(tempCountString);

        String tempUserIDString = userID.getText().toString();
        int parsedUserID = Integer.parseInt(tempUserIDString);

        if (cartDAO.getCartById(parsedCartID) == null) {
            new StyleableToast
                    .Builder(getApplicationContext())
                    .text("Cart doesn't exist!")
                    .textColor(Color.WHITE)
                    .backgroundColor(Color.RED)
                    .show();
        }
        else {
            Cart updatedCart = new Cart(parsedCount, parsedUserID);
            updatedCart.setCartID(parsedCartID);

            cartDAO.update(updatedCart);
            textView.setText(cartDAO.getAllCarts().toString());

            new StyleableToast
                    .Builder(getApplicationContext())
                    .text("Cart" + cartID.getText().toString() + " updated!")
                    .textColor(Color.WHITE)
                    .backgroundColor(Color.GREEN)
                    .show();
        }
    }
}
