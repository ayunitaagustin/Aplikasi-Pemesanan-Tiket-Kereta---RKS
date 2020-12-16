package com.example.pemesanantiket.database;


import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db_travel";
    private static final String TABLE_USER = "tb_user";
    private static final String COL_USERNAME = "username";
    private static final String COL_PASSWORD = "password";
    private static final String COL_NAME = "name";
    private static final String TABLE_BOOK = "tb_book";
    private static final String COL_ID_BOOK = "id_book";
    private static final String COL_ASAL = "asal";
    private static final String COL_TUJUAN = "tujuan";
    private static final String COL_TANGGAL = "tanggal";
    private static final String COL_DEWASA = "dewasa";
    private static final String COL_ANAK = "anak";
    private static final String TABLE_HARGA = "tb_harga";
    private static final String COL_HARGA_DEWASA = "harga_dewasa";
    private static final String COL_HARGA_ANAK = "harga_anak";
    private static final String COL_HARGA_TOTAL = "harga_total";

    private SQLiteDatabase db;
    private String username;
    private String password;
    private String name;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("PRAGMA foreign_keys=ON");
        db.execSQL("create table " + TABLE_USER + " (" + COL_USERNAME + " TEXT PRIMARY KEY, " + COL_PASSWORD +
                " TEXT, " + COL_NAME + " TEXT)");
        db.execSQL("create table " + TABLE_BOOK + " (" + COL_ID_BOOK + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_ASAL + " TEXT, " + COL_TUJUAN + " TEXT" + ", " + COL_TANGGAL + " TEXT, " + COL_DEWASA + " TEXT, "
                + COL_ANAK + " TEXT)");
        db.execSQL("create table " + TABLE_HARGA + " (" + COL_USERNAME + " TEXT, " + COL_ID_BOOK + " INTEGER, " +
                COL_HARGA_DEWASA + " TEXT, " + COL_HARGA_ANAK + " TEXT, " + COL_HARGA_TOTAL +
                " TEXT, FOREIGN KEY(" + COL_USERNAME + ") REFERENCES " + TABLE_USER
                + ", FOREIGN KEY(" + COL_ID_BOOK + ") REFERENCES " + TABLE_BOOK + ")");
        db.execSQL("insert into " + TABLE_USER + " values ('ayunita.agustin.aa@gmail.com','ayunita','Ayunita Agustin');");
        db.execSQL("insert into " + TABLE_USER + " values ('tiaiklima@gmail.com','tiaiklima','Tia Iklima');");
        db.execSQL("insert into " + TABLE_USER + " values ('raihanirambe@gmail.com','hani','Raihani Rambe');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();

    }
    public boolean Register(String username, String password, String name) throws SQLException {

        @SuppressLint("Recycle") Cursor mCursor = db.rawQuery("INSERT INTO " + TABLE_USER + "(" + COL_USERNAME + ", " + COL_PASSWORD + ", " + COL_NAME + ") VALUES (?,?,?)", new String[]{username, password, name});
        if (mCursor != null) {
            return ((Cursor) mCursor).getCount() > 0;
        }
        return false;
    }

    public boolean Login(String username, String password) throws SQLException {
        @SuppressLint("Recycle") Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + COL_USERNAME + "=? AND " + COL_PASSWORD + "=?", new String[]{username, password});
        if (mCursor != null) {
            return ((Cursor) mCursor).getCount() > 0;
        }
        return false;
    }

}
