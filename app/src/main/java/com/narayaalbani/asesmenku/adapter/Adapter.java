package com.narayaalbani.asesmenku.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.narayaalbani.asesmenku.activity.EditMahasiswa;
import com.narayaalbani.asesmenku.R;
import com.narayaalbani.asesmenku.database.DatabaseHelper;
import com.narayaalbani.asesmenku.object.Mahasiswa;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    private final Context context;
    private final List<Mahasiswa> mhsList;
    private Mahasiswa mahasiswa;
    private DatabaseHelper databaseHelper;

    public Adapter(Context context, List<Mahasiswa> mhsList) {
        this.context = context;
        this.mhsList = mhsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.activity_item_recycle, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        databaseHelper = new DatabaseHelper(context);
        mahasiswa = mhsList.get(position);
        holder.getTextViewName().setText(mahasiswa.getNama());

        holder.getEdit().setOnClickListener(v -> {
            Intent intent = new Intent(context, EditMahasiswa.class);
            mahasiswa = mhsList.get(position);
            intent.putExtra("nim", mahasiswa.getNim());
            intent.putExtra("nama", mahasiswa.getNama());
            intent.putExtra("tgl_lahir", mahasiswa.getTglLahir());
            intent.putExtra("kelamin", mahasiswa.getKelamin());
            intent.putExtra("alamat", mahasiswa.getAlamat());
            context.startActivity(intent);
        });

        holder.getHapus().setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Konfirmasi Hapus")
                    .setMessage("Apakah Anda yakin ingin menghapus data ini?")
                    .setPositiveButton("Ya", (dialog, which) -> {
                        mahasiswa = mhsList.get(position);
                        if (!mahasiswa.getNim().isEmpty()) {
                            databaseHelper.deleteMhs(mahasiswa.getNim());
                            mhsList.remove(position);
                            notifyItemRemoved(position);
                            Toast.makeText(context, "Data berhasil dihapus.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Gagal menghapus data.", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Tidak", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return mhsList.size();
    }
}