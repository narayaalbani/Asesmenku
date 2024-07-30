package com.narayaalbani.asesmenku.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.narayaalbani.asesmenku.R;
import com.narayaalbani.asesmenku.activity.EditAccount;
import com.narayaalbani.asesmenku.object.Account;

public class ProfilFragment extends Fragment {

    public ProfilFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        TextView username = view.findViewById(R.id.username_view);
        TextView password = view.findViewById(R.id.password_view);
        TextView nama = view.findViewById(R.id.nama_view);
        TextView tglLahir = view.findViewById(R.id.tgl_lahir_view);

        Account account = new Account(getActivity());
        Cursor cursor = account.readAccount(requireArguments().getString("ARGS_USERNAME"));
        if (cursor != null && cursor.moveToFirst()) {
            username.setText(cursor.getString(1));
            password.setText(cursor.getString(2));
            nama.setText(cursor.getString(3));
            tglLahir.setText(cursor.getString(4));
            cursor.close();
        }

        Button editButton = view.findViewById(R.id.edit_profil);
        editButton.setOnClickListener(v -> requireActivity()
                .startActivity(new Intent(getActivity(), EditAccount.class)
                .putExtra("username", requireArguments().getString("ARGS_USERNAME")))
        );

        return view;
    }

    public static ProfilFragment newInstance(String username) {
        ProfilFragment fragment = new ProfilFragment();
        Bundle args = new Bundle();
        args.putString("ARGS_USERNAME", username);
        fragment.setArguments(args);
        return fragment;
    }
}