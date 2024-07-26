package com.narayaalbani.asesmenku.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.narayaalbani.asesmenku.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    private final TextView textViewName;
    private final Button edit, hapus;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewName = itemView.findViewById(R.id.textViewName);
        edit = itemView.findViewById(R.id.edit);
        hapus = itemView.findViewById(R.id.delete);
    }

    public TextView getTextViewName() {
        return textViewName;
    }

    public Button getEdit() {
        return edit;
    }

    public Button getHapus() {
        return hapus;
    }
}
