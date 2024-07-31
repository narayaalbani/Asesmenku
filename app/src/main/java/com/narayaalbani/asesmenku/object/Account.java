package com.narayaalbani.asesmenku.object;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.narayaalbani.asesmenku.database.DatabaseHelper;

public class Account extends DatabaseHelper {
    private SQLiteDatabase db;
    private Cursor cursor;

    private static final String COL_USER = "username";
    private static final String COL_PASS = "password";
    private static final String COL_NAMA_ACCOUNT = "nama";
    private static final String COL_TGL_LAHIR_ACCOUNT = "tgl_lahir";

    private String username, password, nama, tglLahir;

    public Account(Context context) {
        super(context);
    }

    public boolean createAccount() {
        if (checkAccount()) {
            db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COL_USER, getUsername());
            cv.put(COL_PASS, getPassword());
            cv.put(COL_NAMA_ACCOUNT, getNama());
            cv.put(COL_TGL_LAHIR_ACCOUNT, getTglLahir());
            long result = db.insert(getTableAccount(), null, cv);
            return result > 0;
        } else {
            return false;
        }
    }

    public Cursor readAccount(String username) {
        db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + getTableAccount() + " WHERE " + COL_USER + " = ?", new String[]{username});
    }

    public boolean updateAccount() {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_USER, getUsername());
        cv.put(COL_PASS, getPassword());
        cv.put(COL_NAMA_ACCOUNT, getNama());
        cv.put(COL_TGL_LAHIR_ACCOUNT, getTglLahir());
        int result = db.update(getTableAccount(), cv, COL_USER + "=?", new String[]{getUsername()});
        db.close();
        return result >= 0;
    }

    public void deleteAccount(String username) {
        db = this.getWritableDatabase();
        db.delete(getTableAccount(),COL_USER + " = ?", new String[]{username});
        db.close();
    }

    public boolean checkLogin() {
        db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM " + getTableAccount() + " WHERE " +
                        COL_USER + " = ? AND " +
                        COL_PASS + " = ?",
                new String[]{getUsername(), getPassword()});
        return cursor.getCount() > 0;
    }

    public boolean checkAccount() {
        db = this.getReadableDatabase();
        String query = "SELECT " + COL_USER + " FROM " + getTableAccount() + " WHERE " + COL_USER + " = ?";
        cursor = db.rawQuery(query, new String[]{getUsername()});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return !exists;
    }

    public boolean isUserRegistered() {
        db = this.getReadableDatabase();
        cursor = db.query(getTableAccount(), null, null, null, null, null, null);
        boolean result = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return result;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
