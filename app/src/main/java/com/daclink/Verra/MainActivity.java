package com.daclink.Verra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.SharedPreferences;

import com.daclink.Verra.DB.AppDatabase;
import com.daclink.Verra.DB.VerraDAO;
import com.daclink.Verra.R;
import com.daclink.Verra.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    TextView mainDisplay;
    Button adminButton;
    Button logOutButton;

    VerraDAO verraDAO;

    List<Verra> verraList;

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

//        mainDisplay.setText("Hello, " + );

        verraDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build().VerraDAO();

//        Intent intent = LogIn.intentFactory(getApplicationContext());
//        startActivity(intent);

//        refreshDisplay();

//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                submitGymLog();
//                refreshDisplay();
//            }
//        });
    }


//    private void submitGymLog() {
//        String myExercise = exercise.getText().toString();
//        double myWeight = Double.parseDouble(weight.getText().toString());
//        int myReps = Integer.parseInt(reps.getText().toString());
//
//        Verra log = new Verra(myExercise, myWeight, myReps);
//        verraDAO.insert(log);
//    }

//    private void refreshDisplay() {
//        verraList = verraDAO.getVerras();
//        if (!verraList.isEmpty()) {
//            StringBuilder sb = new StringBuilder();
//            for (Verra log : verraList) {
//                sb.append(log.toString());
//            }
//            mainDisplay.setText(sb.toString());
//        }
//        else {
//            mainDisplay.setText(R.string.no_logs_message);
//        }
//    }


    public static Intent intentFactory(Context packageContext) {
        Intent intent = new Intent(packageContext, MainActivity.class);
        return intent;
    }
}