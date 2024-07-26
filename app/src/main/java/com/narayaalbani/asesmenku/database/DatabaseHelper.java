package com.narayaalbani.asesmenku.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "asesmen.db";
    private static final String TABLE_ACCOUNT = "account";
    private static final String TABLE_MHS = "mahasiswa";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_ACCOUNT +
                " (id_account INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT, " +
                "password TEXT, " +
                "nama TEXT, " +
                "tgl_lahir TEXT)");

        db.execSQL("CREATE TABLE " + TABLE_MHS +
                " (id_mhs INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nim TEXT, " +
                "nama TEXT, " +
                "tgl_lahir TEXT, " +
                "jns_kelamin TEXT, " +
                "alamat TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MHS);
        onCreate(db);
    }

    public void deleteMhs(String nim) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MHS, "nim = ?", new String[]{nim});
        db.close();
    }

    protected String getTableAccount() {
        return TABLE_ACCOUNT;
    }

    protected String getTableMhs() {
        return TABLE_MHS;
    }
}
