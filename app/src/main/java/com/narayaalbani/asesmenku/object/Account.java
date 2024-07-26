package com.narayaalbani.asesmenku.object;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.narayaalbani.asesmenku.database.DatabaseHelper;

public class Account extends DatabaseHelper {
    private SQLiteDatabase db;
    private Cursor cursor;

    private static final String COL_ID_ACCOUNT = "id_account";
    private static final String COL_USER = "username";
    private static final String COL_PASS = "password";
    private static final String COL_NAMA_ACCOUNT = "nama";
    private static final String COL_TGL_LAHIR_ACCOUNT = "tgl_lahir";

    private String id_account, username, password, nama, tglLahir;

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
        int result = db.update(getTableAccount(), cv, COL_ID_ACCOUNT + "=?", new String[]{getId_account()});
        db.close();
        return result > 0;

//        db = this.getWritableDatabase();
//        String sql = "UPDATE " + getTableAccount() + " SET " +
//                COL_USER + " = ?, " +
//                COL_PASS + " = ?, " +
//                COL_NAMA_ACCOUNT + " = ?, " +
//                COL_TGL_LAHIR_ACCOUNT + " = ? WHERE " +
//                COL_USER + " = ? AND NOT EXISTS " +
//                "(SELECT 1 FROM " + getTableAccount() + " WHERE " + COL_USER + " = ? AND " + COL_USER + " != ?)";
//        cursor = db.rawQuery(sql, new String[]{getUsername(), getPassword(), getNama(), getTglLahir(), username, getUsername(), username});
//        return cursor.getCount() > 0;
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

    public String getId_account() {
        return id_account;
    }

    public void setId_account(String id_account) {
        this.id_account = id_account;
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
