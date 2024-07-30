package com.narayaalbani.asesmenku.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.narayaalbani.asesmenku.R;
import com.narayaalbani.asesmenku.fragment.BerandaFragment;
import com.narayaalbani.asesmenku.fragment.InputFragment;
import com.narayaalbani.asesmenku.fragment.ProfilFragment;

import java.util.Objects;

public class Dashboard extends AppCompatActivity {
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        replaceFragment(new BerandaFragment(this));

        Toolbar actionBar = findViewById(R.id.actionbar);
        setSupportActionBar(actionBar);

        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnItemSelectedListener(item -> {
            id = item.getItemId();
            if (id == R.id.beranda_navbar) {
                replaceFragment(new BerandaFragment(this));
                Objects.requireNonNull(getSupportActionBar()).setTitle("Asesmenku");
                return true;
            } else if (id == R.id.input_navbar) {
                replaceFragment(new InputFragment(this));
                Objects.requireNonNull(getSupportActionBar()).setTitle("Input");
                return true;
            } else if (id == R.id.profil_navbar) {
                ProfilFragment profilFragment = ProfilFragment.newInstance(
                        getSharedPreferences("loginPrefs", MODE_PRIVATE)
                        .getString("usernameLoggedIn", null));
                replaceFragment(profilFragment);
                Objects.requireNonNull(getSupportActionBar()).setTitle("Profil");
                return true;
            }
            return false;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        id = item.getItemId();
        if (id == R.id.info) {
            startActivity(new Intent(this, Informasi.class));
        } else if (id == R.id.logout) {
            new AlertDialog.Builder(this)
                    .setTitle("Konfirmasi Logout")
                    .setMessage("Apakah Anda yakin ingin logout akun ini?")
                    .setPositiveButton("Ya", (dialog, which) -> {
                        getSharedPreferences("loginPrefs", MODE_PRIVATE)
                                .edit()
                                .putBoolean("isLoggedIn", false)
                                .apply();
                        startActivity(new Intent(this, Login.class));
                        finish();
                    })
                    .setNegativeButton("Tidak", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        return true;
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit();
    }
}