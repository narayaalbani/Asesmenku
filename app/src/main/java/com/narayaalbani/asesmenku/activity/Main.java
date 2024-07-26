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
            startActivity(new Intent(this, Login.class));
        } else {
            Intent intent = new Intent(this, Register.class);
            intent.putExtra("FIRST", false);
            startActivity(intent);
        }
        finish();
    }
}