package com.example.dizitakip;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBase{
	
	public static final String KEY_ROWID = "_id";
	public static final String KEY_ISIM = "isim";
	public static final String KEY_TARIH = "tarih";
	public static final String KEY_SAAT = "saat";
	public static final String KEY_BILDIRIM = "bildirim";
	
	public static final String[] ALL_KEYS = new String[] {KEY_ROWID, KEY_ISIM, KEY_TARIH, KEY_SAAT, KEY_BILDIRIM};
	
	private static final String DATABASE_NAME="DiziBilgileri";
	private static final String DATABASE_TABLE="diziler";
	private static final int DATABASE_VERSION=1;
	private static final String TAG = "DBAdapter";
	
	private static final String DATABASE_CREATE_SQL = 
			"create table " + DATABASE_TABLE 
			+ " (" + KEY_ROWID + " integer primary key autoincrement, "
			+ KEY_ISIM + " text not null, "
			+ KEY_TARIH + " text not null, "
			+ KEY_SAAT + " text not null, "
			+ KEY_BILDIRIM + " text not null "
			+ ");";
	
	
	private final Context context;
	private DatabaseHelper myDBHelper;
	private SQLiteDatabase db;
	
	
	public DataBase(Context ctx) {
		this.context = ctx;
		myDBHelper = new DatabaseHelper(context);
	}
	

	public DataBase open() {
		db = myDBHelper.getWritableDatabase();
		return this;
	}
	

	public void close() {
		myDBHelper.close();
	}
	
	
	public long insertRow(String isim, String tarih, String saat,String bildirim) {

		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_ISIM, isim);
		initialValues.put(KEY_TARIH, tarih);
		initialValues.put(KEY_SAAT, saat);
		initialValues.put(KEY_BILDIRIM, bildirim);		
		return db.insert(DATABASE_TABLE, null, initialValues);
		
	}
	
	
	public boolean deleteRow(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		return db.delete(DATABASE_TABLE, where, null) != 0;
	}
	
	public void deleteAll() {
		Cursor c = getAllRows();
		long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
		if (c.moveToFirst()) {
			do {
				deleteRow(c.getLong((int) rowId));				
			} while (c.moveToNext());
		}
		c.close();
	}
	
	
	public Cursor getAllRows() {
		String where = null;
		Cursor cursor = 	db.query(true, DATABASE_TABLE, ALL_KEYS, where, null, null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			}
		     return cursor;
		}
	
	public Cursor getIsim(){
	
	    String where = KEY_ISIM;
	    Cursor cursor = db.query(true, DATABASE_TABLE, ALL_KEYS, where, null, null, null, null, null);
	    if (cursor != null) {
			cursor.moveToFirst();
		}
	    return cursor;
	}
	
	public Cursor getIsimler() { 

		String countQuery = "SELECT isim FROM diziler";
		Cursor cursor = db.rawQuery(countQuery, null);
		return cursor;
	}
	
	public Cursor getAra(String s){
		    String where = KEY_ISIM + "=" + s;
		    Cursor cursor = db.query(true, DATABASE_TABLE, ALL_KEYS, where, null, null, null, null, null);
		    if (cursor != null) {
				cursor.moveToFirst();
			}
		    	 return cursor;
		}
	
	public Cursor getRow(long rowId) {
			String where = KEY_ROWID + "=" + rowId;
			Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS, 
							where, null, null, null, null, null);
			if (c != null) {
				c.moveToFirst();
			}
			return c;
		} 
	
	public boolean updateRow(long rowId, String isim, String tarih, String saat, String bildirim){
		
		String where = KEY_ROWID + "=" + rowId;		
		ContentValues newValues = new ContentValues();
		
		newValues.put(KEY_ISIM, isim);
		newValues.put(KEY_TARIH, tarih);
		newValues.put(KEY_SAAT, saat);
		newValues.put(KEY_BILDIRIM, bildirim);
		
		return db.update(DATABASE_TABLE, newValues, where, null) != 0;
	}
	
	
	
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase _db) {
			_db.execSQL(DATABASE_CREATE_SQL);			
		}

		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading application's database from version " + oldVersion
					+ " to " + newVersion + ", which will destroy all old data!");

			_db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

			onCreate(_db);
		}
	}	
	
}
