package com.narayaalbani.asesmenku.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.narayaalbani.asesmenku.R;
import com.narayaalbani.asesmenku.object.Mahasiswa;

import java.util.Calendar;
import java.util.Objects;

public class InputFragment extends Fragment {
    private final Context context;
    private final Mahasiswa mahasiswa;

    private TextInputEditText nimField, namaField, tglLahirField, alamatField;
    private AutoCompleteTextView kelaminField;
    private final String[] kelamin = {"Laki-laki", "Perempuan"};

    public InputFragment(Context context) {
        this.context = context;
        mahasiswa = new Mahasiswa(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);

        nimField = view.findViewById(R.id.nim_field);

        namaField = view.findViewById(R.id.nama_field);

        tglLahirField = view.findViewById(R.id.tanggal_lahir_field);
        tglLahirField.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                    (viewDate, year, monthOfYear, dayOfMonth) -> tglLahirField.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year), mYear, mMonth, mDay);
            datePickerDialog.show();
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.list_gender, kelamin);
        kelaminField = view.findViewById(R.id.kelamin_field);
        kelaminField.setInputType(InputType.TYPE_NULL);
        kelaminField.setAdapter(adapter);

        alamatField = view.findViewById(R.id.alamat_field);

        Button buttonInput = view.findViewById(R.id.input_buttom);
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
                    Toast.makeText(context, "Input data berhasil.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "NIM telah terdaftar.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Tidak boleh ada yang kosong.", Toast.LENGTH_SHORT).show();
            }

        });
        return view;
    }
}