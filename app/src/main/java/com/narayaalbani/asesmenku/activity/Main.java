package com.narayaalbani.asesmenku.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.narayaalbani.asesmenku.R;
import com.narayaalbani.asesmenku.object.Account;

public class Main extends AppCompatActivity {
    private final Account account = new Account(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (account.isUserRegistered()) {
            if (isLoggedIn()) {
                startActivity(new Intent(this, Dashboard.class));
            } else {
                startActivity(new Intent(this, Login.class));
            }
        } else {
            startActivity(new Intent(this, Register.class)
                    .putExtra("FIRST", false));
        }
        finish();
    }

    private boolean isLoggedIn() {
        return getSharedPreferences("loginPrefs", MODE_PRIVATE)
                .getBoolean("isLoggedIn", false);
    }
}