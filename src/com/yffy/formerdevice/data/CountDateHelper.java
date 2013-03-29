package com.yffy.formerdevice.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class CountDateHelper extends SQLiteOpenHelper implements BaseColumns {
	// 锟斤拷菘锟斤拷锟斤拷锟斤拷
	public static final String DB_NAME = "count.db";
	// 锟斤拷菘锟侥版本
	public static final int DB_VERSION = 1;
	// 锟斤拷锟斤拷
	public static final String TABLE_NAME = "Count";
	// 锟斤拷锟斤拷
	public static final String TYPECOLUMN = "TYPE";
	public static final String DETAILSCOLUMN = "DETAILS";
	public static final String COUNTCOLUMN = "COUNT";
	public static final String DATECOLUMN = "DATE";
	public static final String PSCOLUMN = "PS";
	public static final String MONTHCOLUMN = "MONTH";
	public static final String DAYCOLUMN = "DAY";
	// 默锟斤拷锟斤拷锟斤拷拇锟斤拷锟�
	public static final String ORDERBY = "DAY DESC , _ID DESC";

	public CountDateHelper(Context context) {
		super(context, Params.Sqlist.TABLE_NAME, null, Params.Sqlist.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE IF NOT EXISTS " + Params.Sqlist.TABLE_NAME
				+ " (" + Params.Sqlist.CUSTOMER_ID + " INTEGER PRIMARY KEY, "
				
				+ Params.Sqlist.CUSTOMER_PHONE + " TEXT)";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("DROP TABLE IF EXISTS " + Params.Sqlist.TABLE_NAME);
		onCreate(db);
	}

}
