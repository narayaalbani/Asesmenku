package com.narayaalbani.asesmenku.activity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.narayaalbani.asesmenku.R;
import com.narayaalbani.asesmenku.object.Mahasiswa;

import java.util.Objects;

public class EditMahasiswa extends AppCompatActivity {
    private final Mahasiswa mahasiswa = new Mahasiswa(this);

    private TextInputEditText nimEditText, namaEditText, tglLahirEditText, alamatEditText;
    private TextView kelaminEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_mahasiswa);

        Toolbar actionBar = findViewById(R.id.actionbar);
        setSupportActionBar(actionBar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Edit Data");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nimEditText = findViewById(R.id.nim_field);
        namaEditText = findViewById(R.id.nama_field);
        tglLahirEditText = findViewById(R.id.tanggal_lahir_field);
        kelaminEditText = findViewById(R.id.kelamin_field);
        alamatEditText = findViewById(R.id.alamat_field);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nimEditText.setText(extras.getString("nim"));
            namaEditText.setText(extras.getString("nama"));
            tglLahirEditText.setText(extras.getString("tgl_lahir"));
            kelaminEditText.setText(extras.getString("kelamin"));
            alamatEditText.setText(extras.getString("alamat"));
        }

        Button editButton = findViewById(R.id.submit_edit);
        editButton.setOnClickListener(v -> {
            String inputNim = Objects.requireNonNull(nimEditText.getText()).toString();
            String inputNama = Objects.requireNonNull(namaEditText.getText()).toString();
            String inputTgllahir = Objects.requireNonNull(tglLahirEditText.getText()).toString();
            String inputKelamin = Objects.requireNonNull(kelaminEditText.getText()).toString();
            String inputAlamat = Objects.requireNonNull(alamatEditText.getText()).toString();

            if (!inputNim.isEmpty() && !inputNama.isEmpty() && !inputTgllahir.isEmpty() && !inputKelamin.isEmpty() && !inputAlamat.isEmpty()) {
                mahasiswa.setNim(inputNim);
                mahasiswa.setNama(inputNama);
                mahasiswa.setTglLahir(inputTgllahir);
                mahasiswa.setKelamin(inputKelamin);
                mahasiswa.setAlamat(inputAlamat);
                if (mahasiswa.updateMhs()) {
                    Toast.makeText(EditMahasiswa.this, "Edit data berhasil.", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditMahasiswa.this, "NIM tidak boleh sama dengan yang lain.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(EditMahasiswa.this, "Tidak boleh ada yang kosong.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return true;
    }
}