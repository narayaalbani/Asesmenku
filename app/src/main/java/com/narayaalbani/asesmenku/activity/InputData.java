package com.narayaalbani.asesmenku.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.narayaalbani.asesmenku.R;
import com.narayaalbani.asesmenku.object.Mahasiswa;

import java.util.Calendar;
import java.util.Objects;

public class InputData extends AppCompatActivity {
    private TextInputEditText nimField, namaField, tglLahirField, alamatField;
    private AutoCompleteTextView kelaminField;
    private final Mahasiswa mahasiswa = new Mahasiswa(this);
    private final String[] kelamin = {"Laki-laki", "Perempuan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        nimField = findViewById(R.id.nim_field);

        namaField = findViewById(R.id.nama_field);

        tglLahirField = findViewById(R.id.tanggal_lahir_field);
        tglLahirField.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (view, year, monthOfYear, dayOfMonth) -> tglLahirField.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year), mYear, mMonth, mDay);
            datePickerDialog.show();
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_gender, kelamin);
        kelaminField = findViewById(R.id.kelamin_field);
        kelaminField.setInputType(InputType.TYPE_NULL);
        kelaminField.setAdapter(adapter);

        alamatField = findViewById(R.id.alamat_field);

        Button buttonInput = findViewById(R.id.input_buttom);
        buttonInput.setOnClickListener(v -> {
            String nim = Objects.requireNonNull(nimField.getText()).toString();
            String nama = Objects.requireNonNull(namaField.getText()).toString();
            String tglLahir = Objects.requireNonNull(tglLahirField.getText()).toString();
            String kelamin = kelaminField.getText().toString();
            String alamat = Objects.requireNonNull(alamatField.getText()).toString();

            if (!nim.isEmpty() && !nama.isEmpty() && !tglLahir.isEmpty() && !kelamin.isEmpty() && !alamat.isEmpty()) {
                mahasiswa.setNim(nim);
                mahasiswa.setNama(nama);
                mahasiswa.setTglLahir(tglLahir);
                mahasiswa.setKelamin(kelamin);
                mahasiswa.setAlamat(alamat);

                if (mahasiswa.createMhs()) {
                    Toast.makeText(InputData.this, "Input data berhasil.", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(InputData.this, "NIM telah terdaftar.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(InputData.this, "Tidak boleh ada yang kosong.", Toast.LENGTH_SHORT).show();
            }

        });
    }
}