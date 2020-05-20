package com.example.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="databarang.db";
    public static final String TABLE_NAME ="barang";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="nama_barang";
    public static final String COL_3 ="merk_barang";
    public static final String COL_4 ="harga_barang";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase dbsql) {
        dbsql.execSQL("CREATE TABLE "+TABLE_NAME+" (ID INTEGER PRIMARY  KEY AUTOINCREMENT, nama_barang TEXT, merk_barang TEXT, harga_barang TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String nama_barang, String merk_barang, String harga_barang){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,nama_barang);
        contentValues.put(COL_3,merk_barang);
        contentValues.put(COL_4,harga_barang);

        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id, String nama, String merk,String harga) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,nama);
        contentValues.put(COL_3,merk);
        contentValues.put(COL_4,harga);

        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }



    ///////////
}
