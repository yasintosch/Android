package com.example.databaseapp;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;





import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper{

	private static final int DATABASE_VERSION = 1;
	
	private static final String DATABASE_AD ="OgrencilerDB";
	
	private static final String TABLE_NAME = "Ogreciler";
	
	private static final String KEY_OGRENCI_ADI ="ogrenci_adi";
	private static final String KEY_OGRENCI_ID = "id";
	private static final String KEY_OGRENCI_OKUL_NO= "okul_no";
	private static final String KEY_OGRENCÝ_BOLUM = "bolum";
	private static final String KEY_OGRENCI_SINIF = "sinif";
	
	private static final String CREATE_TABLE_OGRENCILER ="CREATE TABLE " + TABLE_NAME 
			+ " (" 
			+ KEY_OGRENCI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " 
			+ KEY_OGRENCI_OKUL_NO + " TEXT NOT NULL, " 
		    + KEY_OGRENCI_ADI + " TEXT NOT NULL, " 
		    + KEY_OGRENCÝ_BOLUM + " TEXT NOT NULL, "
		    + KEY_OGRENCI_SINIF + " TEXT NOT NULL "
		    +");";
	private static final String[] COLUMNS = {KEY_OGRENCI_ID,KEY_OGRENCI_OKUL_NO,KEY_OGRENCI_ADI,KEY_OGRENCÝ_BOLUM,KEY_OGRENCI_SINIF};
	
	public Database (Context contex)
	{
		super(contex,TABLE_NAME,null,DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TABLE_OGRENCILER);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
	}
	
	public void OgrenciEkle(Ogrenci ogrenci )
	{
		
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_OGRENCI_OKUL_NO, ogrenci.getOkul_no());
		values.put(KEY_OGRENCI_ADI, ogrenci.getAd());
		values.put(KEY_OGRENCÝ_BOLUM, ogrenci.getBolum());
		values.put(KEY_OGRENCI_SINIF, ogrenci.getSinif());
		
		db.insert(TABLE_NAME, null, values);
		db.close();
		

	}
	
	
	public Ogrenci ogrenciGetir(int id){
		 
	    // 1. get reference to readable DB
	    SQLiteDatabase db = this.getReadableDatabase();
	 
	    // 2. build query
	    Cursor cursor = 
	            db.query(TABLE_NAME, // a. table
	            COLUMNS, // b. column names
	            " id = ?", // c. selections 
	            new String[] { String.valueOf(id) }, // d. selections args
	            null, // e. group by
	            null, // f. having
	            null, // g. order by
	            null); // h. limit
	 
	    // 3. if we got results get the first one
	    if (cursor != null)
	        cursor.moveToFirst();
	 
	    // 4. build book object
	    Ogrenci ogrenci = new Ogrenci();
        ogrenci.setId(Integer.parseInt(cursor.getString(0)));
        ogrenci.setOkul_no(cursor.getString(1));
        ogrenci.setAd(cursor.getString(2));
        ogrenci.setBolum(cursor.getString(3));
        ogrenci.setSinif(cursor.getString(4));
	 
	    //log 

	 
	    // 5. return book
	    return ogrenci;
	}
	
	
	public ArrayList<Ogrenci> TumOgrencileriGetir()
	{
		
		ArrayList<Ogrenci> ogrenciler = new ArrayList<Ogrenci>();
		String query = "SELECT  * FROM " + TABLE_NAME;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		
		Ogrenci ogrenci = null;
	       if (cursor.moveToFirst()) {
	           do {
	               ogrenci = new Ogrenci();
	               ogrenci.setId(Integer.parseInt(cursor.getString(0)));
	               ogrenci.setOkul_no(cursor.getString(1));
	               ogrenci.setAd(cursor.getString(2));
	               ogrenci.setBolum(cursor.getString(3));
	               ogrenci.setSinif(cursor.getString(4));
	               
	 
	             
	               ogrenciler.add(ogrenci);
	           } while (cursor.moveToNext());
	       }
	       
	      
	       
	       
	       return ogrenciler;
	       
	} 
	
	public void OgrenciDuzenle(Ogrenci ogrenci )
	{
		
		 
	    SQLiteDatabase db = this.getWritableDatabase();
	 
	   
	    ContentValues values = new ContentValues();
		values.put(KEY_OGRENCI_OKUL_NO, ogrenci.getOkul_no());
		values.put(KEY_OGRENCI_ADI, ogrenci.getAd());
		values.put(KEY_OGRENCÝ_BOLUM, ogrenci.getBolum());
		values.put(KEY_OGRENCI_SINIF, ogrenci.getSinif());
	 
	   
	    db.update(TABLE_NAME, //table
	            values, // column/value
	            KEY_OGRENCI_ID+" = ?", // selections
	            new String[] { String.valueOf(ogrenci.getId()) }); 
	 
	    // 4. close
	    db.close();
	 
	    

		
	}
	
	public void OgrenciSil(Ogrenci ogrenci)
	{
		   SQLiteDatabase db = this.getWritableDatabase();
		   
	       
	        db.delete(TABLE_NAME,
	        		KEY_OGRENCI_ID+" = ?",
	                new String[] { String.valueOf(ogrenci.getId()) });
	 
	       
	        db.close();
	 
	       
		
		
	}

	
}
