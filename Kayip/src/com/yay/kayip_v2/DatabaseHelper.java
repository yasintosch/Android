package com.yay.kayip_v2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.sax.StartElementListener;

public class DatabaseHelper extends SQLiteOpenHelper {

	
    // Logcat tag
    private static final String LOG = "DatabaseHelper";
	
    // Database Version
    private static final int DATABASE_VERSION = 1;
	
    // Database Name
    private static final String DATABASE_AD = "Kayip";
	
    // Table Names
    private static final String TABLE_KULLANICILAR = "kullanicilar";
    private static final String TABLE_ILANLAR = "ilanlar";
    private static final String TABLE_KULLANICILAR_ILANLAR= "kullanici_ilanlar";
    
    //  Kullanicilar Table - column names
	public static final String KEY_KULID = "kulid";
	public static final String KEY_EMAIL = "email";
	public static final String KEY_SIFRE = "sifre";
	
    //  Ýlanlar Table - column names
	public static String KEY_ILANID = "ilanid";
	public static String KEY_ILANTURU = "ilanturu";
	public static String KEY_KAYIPTURU = "kayipturu";
	public static String KEY_OZELLIKLER = "ozellikler";
	public static String KEY_TARIH = "tarih";
	public static String KEY_YER = "yer";
	public static String KEY_ILETISIM = "iletisim";
	
	//Kullanicilar-Ýlanlar Table  - column names
	
	public static String KEY_ID = "id";
	
	// Table Create Statements
	
	//Kullanicilar Create Statement
	private static final String CREATE_TABLE_KULLANICILAR="CREATE TABLE " + TABLE_KULLANICILAR + " (" + KEY_KULID
    + " INTEGER PRIMARY KEY AUTOINCREMENT , " + KEY_EMAIL
    + " TEXT NOT NULL, " + KEY_SIFRE + " TEXT NOT NULL);";
	
	//Ilanlar Create Statement
	
	
	private static final String CREATE_TABLE_ILANLAR="CREATE TABLE "+ TABLE_ILANLAR+ " (" +
			  KEY_ILANID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
			  KEY_ILANTURU + " TEXT, "+
			  KEY_KAYIPTURU + " TEXT, "+
			  KEY_OZELLIKLER + " TEXT, "+
			  KEY_TARIH + " TEXT, "+
			  KEY_YER + " TEXT, "+
			  KEY_ILETISIM + " TEXT); ";
	//ILanlar_Kullanýcýlar create statment
	
	private static final String CREATE_TABLE_KULLANICILAR_ILANLAR="CREATE TABLE "+ TABLE_KULLANICILAR_ILANLAR+ " (" +
	          KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
              KEY_ILANID + " INTEGER, " +
	          KEY_KULID + " INTEGER);";
             
			
    
	 public DatabaseHelper(Context context) {
	        super(context, DATABASE_AD, null, DATABASE_VERSION);
	    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
        db.execSQL(CREATE_TABLE_KULLANICILAR);
        db.execSQL(CREATE_TABLE_ILANLAR);
        db.execSQL(CREATE_TABLE_KULLANICILAR_ILANLAR);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KULLANICILAR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ILANLAR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KULLANICILAR_ILANLAR);	
        onCreate(db);
	}

	public void kullaniciKaydet(String email, String sifre) {
		// TODO Auto-generated method stub
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues cv = new ContentValues();
		cv.put(KEY_EMAIL, email);
		cv.put(KEY_SIFRE, sifre);
		
		db.insert(TABLE_KULLANICILAR, null, cv);
	}
	
	public void ilanKaydet(String ilan_turu, String kayip_turu, String ozellikler,String tarih, String yer,  String iletisim) {
		// TODO Auto-generated method stub
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues cv = new ContentValues();
		cv.put(KEY_ILANTURU, ilan_turu);
		cv.put(KEY_KAYIPTURU, kayip_turu);
		cv.put(KEY_TARIH, tarih);
		cv.put(KEY_YER, yer);
		cv.put(KEY_OZELLIKLER, ozellikler);
		cv.put(KEY_ILETISIM, iletisim);
		
		
		db.insert(TABLE_ILANLAR, null, cv);
		
		
	}
	
	
	
    public void baglantiyiKapa() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
    
	String[] kolon={"kulid","email","sifre"};
	 
	public Cursor kayitGetir() {
	  SQLiteDatabase db=this.getReadableDatabase();
	  Cursor c=db.query("kullanicilar", kolon, null, null, null, null, null);
	  //startManagingCursor(c);
	  return c;
	 }
	
		public StringBuilder kayitYazdýr(){


			
			Cursor kayitlar= kayitGetir();
			
			StringBuilder builder= new StringBuilder();
			while(kayitlar.moveToNext()){
				long id= kayitlar.getLong(kayitlar.getColumnIndex("kulid"));
				String mail= kayitlar.getString(kayitlar.getColumnIndex("email"));
				String pass= kayitlar.getString(kayitlar.getColumnIndex("sifre"));
				
				builder.append(id);
				builder.append(" ");
				builder.append(mail);
				builder.append("~");
				builder.append(pass);
				builder.append("\n");
			}
			
			
			return builder;
			
		}
	 
	 
	
}
