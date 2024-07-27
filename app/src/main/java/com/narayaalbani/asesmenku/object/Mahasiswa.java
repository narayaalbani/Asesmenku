package com.narayaalbani.asesmenku.object;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.narayaalbani.asesmenku.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class Mahasiswa extends DatabaseHelper {
    private SQLiteDatabase db;

    private static final String COL_NIM = "nim";
    private static final String COL_NAMA_MHS = "nama";
    private static final String COL_TGL_LAHIR_MHS = "tgl_lahir";
    private static final String COL_JNS_KELAMIN = "jns_kelamin";
    private static final String COL_ALAMAT = "alamat";

    private String nim, nama, tglLahir, kelamin, alamat;

    public Mahasiswa(Context context) {
        super(context);
    }

    public boolean createMhs() {
        if (checkMhs()) {
            db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COL_NIM, getNim());
            cv.put(COL_NAMA_MHS, getNama());
            cv.put(COL_TGL_LAHIR_MHS, getTglLahir());
            cv.put(COL_JNS_KELAMIN, getKelamin());
            cv.put(COL_ALAMAT, getAlamat());
            long result = db.insert(getTableMhs(), null, cv);
            return result != -1;
        } else {
            return false;
        }
    }

    public List<Mahasiswa> readMhs() {
        String query = "SELECT * FROM " + getTableMhs();
        db = this.getReadableDatabase();
        Cursor data = db.rawQuery(query, null);
        List<Mahasiswa> mahasiswaList = new ArrayList<>();
        if (data.moveToFirst()) {
            do {
                Mahasiswa mahasiswa = new Mahasiswa(null);
                mahasiswa.setNim(data.getString(1));
                mahasiswa.setNama(data.getString(2));
                mahasiswa.setTglLahir(data.getString(3));
                mahasiswa.setKelamin(data.getString(4));
                mahasiswa.setAlamat(data.getString(5));
                mahasiswaList.add(mahasiswa);
            } while (data.moveToNext());
        }
        data.close();
        db.close();
        return mahasiswaList;
    }

    public boolean updateMhs() {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NIM, getNim());
        cv.put(COL_NAMA_MHS, getNama());
        cv.put(COL_TGL_LAHIR_MHS, getTglLahir());
        cv.put(COL_JNS_KELAMIN, getKelamin());
        cv.put(COL_ALAMAT, getAlamat());
        int result = db.update(getTableMhs(), cv, COL_NIM + " = ?", new String[]{getNim()});
        db.close();
        return result >= 0;
    }

    private boolean checkMhs() {
        db = this.getReadableDatabase();
        String query = "SELECT " + COL_NIM + " FROM " + getTableMhs() + " WHERE " + COL_NIM + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{getNim()});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return !exists;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getKelamin() {
        return kelamin;
    }

    public void setKelamin(String kelamin) {
        this.kelamin = kelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
