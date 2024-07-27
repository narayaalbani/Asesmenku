package com.narayaalbani.asesmenku.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.narayaalbani.asesmenku.R;
import com.narayaalbani.asesmenku.object.Account;

import java.util.Calendar;
import java.util.Objects;

public class EditAccount extends AppCompatActivity {
    private final Account account = new Account(this);

    private TextInputEditText namaEditText, tglLahirEditText, usernameEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        Toolbar actionBar = findViewById(R.id.actionbar);
        setSupportActionBar(actionBar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Edit Profil");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        usernameEditText = findViewById(R.id.username_field);
        passwordEditText = findViewById(R.id.password_field);
        namaEditText = findViewById(R.id.nama_field);
        tglLahirEditText = findViewById(R.id.tanggal_lahir_field);
        tglLahirEditText.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(EditAccount.this,
                    (view, year, monthOfYear, dayOfMonth) -> tglLahirEditText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year), mYear, mMonth, mDay);
            datePickerDialog.show();
        });

        Intent intent = getIntent();
        Cursor cursor = account.readAccount(intent.getStringExtra("username"));
        if (cursor != null && cursor.moveToFirst()) {
            usernameEditText.setText(cursor.getString(1));
            passwordEditText.setText(cursor.getString(2));
            namaEditText.setText(cursor.getString(3));
            tglLahirEditText.setText(cursor.getString(4));
            cursor.close();
        }

        Button editButton = findViewById(R.id.submit_edit);
        editButton.setOnClickListener(v -> {
            String username = Objects.requireNonNull(usernameEditText.getText()).toString();
            String password = Objects.requireNonNull(passwordEditText.getText()).toString();
            String nama = Objects.requireNonNull(namaEditText.getText()).toString();
            String tglLahir = Objects.requireNonNull(tglLahirEditText.getText()).toString();

            if (!nama.isEmpty() && !tglLahir.isEmpty() && !username.isEmpty() && !password.isEmpty()) {
                account.setUsername(username);
                account.setPassword(password);
                account.setNama(nama);
                account.setTglLahir(tglLahir);
                if (account.updateAccount()) {
                    Toast.makeText(EditAccount.this, "Edit data berhasil.", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditAccount.this, "Username telah terdaftar.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(EditAccount.this, "Tidak boleh ada yang kosong.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return true;
    }
}