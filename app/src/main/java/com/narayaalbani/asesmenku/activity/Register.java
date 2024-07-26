package com.narayaalbani.asesmenku.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.narayaalbani.asesmenku.R;
import com.narayaalbani.asesmenku.object.Account;

import java.util.Calendar;
import java.util.Objects;

public class Register extends AppCompatActivity {
    private final Account account = new Account(this);

    private TextInputEditText usernameText, passwordText, namaText, tglLahirText;

    private boolean isRegistered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView titleText = findViewById(R.id.title_text_view);
        LinearLayout textToLogin = findViewById(R.id.to_login);
        if (getIntent().getBooleanExtra("FIRST", false)) {
            titleText.setText("Buat Akun Barumu");
            isRegistered = true;
        } else {
            titleText.setText("Buat Akun Pertamamu");
            textToLogin.setVisibility(View.GONE);
            isRegistered = false;
        }

        usernameText = findViewById(R.id.username_field);

        passwordText = findViewById(R.id.password_field);

        namaText = findViewById(R.id.nama_field);

        tglLahirText = findViewById(R.id.tanggal_lahir_field);
        tglLahirText.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(Register.this,
                    (view, year, monthOfYear, dayOfMonth) -> tglLahirText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year), mYear, mMonth, mDay);
            datePickerDialog.show();
        });

        Button buttonRegister = findViewById(R.id.register_button);
        buttonRegister.setOnClickListener(view -> {
            String username = Objects.requireNonNull(usernameText.getText()).toString();
            String password = Objects.requireNonNull(passwordText.getText()).toString();
            String nama = Objects.requireNonNull(namaText.getText()).toString();
            String tglLahir = Objects.requireNonNull(tglLahirText.getText()).toString();

            if (!nama.isEmpty() && !tglLahir.isEmpty() && !username.isEmpty() && !password.isEmpty()) {
                account.setUsername(username);
                account.setPassword(password);
                account.setNama(nama);
                account.setTglLahir(tglLahir);

                if (account.createAccount()) {
                    Toast.makeText(Register.this, "Register berhasil.", Toast.LENGTH_SHORT).show();
                    if (!isRegistered) {
                        startActivity(new Intent(this, Login.class));
                    }
                    finish();
                } else {
                    Toast.makeText(Register.this, "Akun telah terdaftar.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(Register.this, "Tidak boleh ada yang kosong.", Toast.LENGTH_SHORT).show();
            }
        });

        TextView toLogin = findViewById(R.id.login_hyperlink);
        toLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, Login.class));
            finish();
        });
    }
}