package com.narayaalbani.asesmenku.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.narayaalbani.asesmenku.R;
import com.narayaalbani.asesmenku.adapter.Adapter;
import com.narayaalbani.asesmenku.object.Mahasiswa;

import java.util.List;

public class LihatData extends AppCompatActivity {
    private final Mahasiswa mahasiswa = new Mahasiswa(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);

        RecyclerView recyclerView = findViewById(R.id.list_data);
        List<Mahasiswa> mahasiswaList = mahasiswa.readMhs();
        Adapter adapter = new Adapter(this, mahasiswaList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mahasiswa.readMhs();
    }
}