package com.narayaalbani.asesmenku.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.narayaalbani.asesmenku.R;
import com.narayaalbani.asesmenku.object.Account;

import java.util.Objects;

public class Login extends AppCompatActivity {
    private TextInputEditText usernameText, passwordText;
    private final Account account = new Account(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameText = findViewById(R.id.username_field);
        passwordText = findViewById(R.id.password_field);

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(view -> {
            account.setUsername(Objects.requireNonNull(usernameText.getText()).toString());
            account.setPassword(Objects.requireNonNull(passwordText.getText()).toString());
            if(account.checkLogin()) {
                Intent intent = new Intent(this, Dashboard.class);
                intent.putExtra("username", usernameText.getText().toString());
                startActivity(intent);
            } else {
                Toast.makeText(Login.this, "Username atau password salah.", Toast.LENGTH_SHORT).show();
            }
        });

        TextView toRegister = findViewById(R.id.register_hyperlink);
        toRegister.setOnClickListener(v -> {
            Intent intent = new Intent(this, Register.class);
            intent.putExtra("FIRST", true);
            startActivity(intent);
        });
    }
}