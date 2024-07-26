package com.narayaalbani.asesmenku.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.narayaalbani.asesmenku.R;
import com.narayaalbani.asesmenku.adapter.Adapter;
import com.narayaalbani.asesmenku.object.Mahasiswa;

import java.util.List;

public class BerandaFragment extends Fragment {
    private final Mahasiswa mahasiswa;
    private final Context context;

    public BerandaFragment(Context context) {
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
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.list_mahasiswa);
        List<Mahasiswa> mahasiswaList = mahasiswa.readMhs();
        Adapter adapter = new Adapter(context, mahasiswaList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        mahasiswa.readMhs();
        return view;
    }
}