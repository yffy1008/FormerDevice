package com.yffy.formerdevice.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteCunchu extends SQLiteOpenHelper {

	public  SQLiteCunchu(Context context){
		this(context,Params.Sqlist.DATABASE_NAME,null,Params.Sqlist.DATABASE_VERSION);
		
	}
	public  SQLiteCunchu(Context context,int version){
		this(context,Params.Sqlist.DATABASE_NAME,null,version);
		System.out.println("hjcbdbfie   "+"创建表成功?");
	}
	
	public SQLiteCunchu(Context context, String name, CursorFactory factory,int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//创建一个表,只有一列,存放电话号码
//		String createTable="create table "+Params.Sqlist.TABLE_NAME+"("+Params.Sqlist.PHONE+" nvarchar(50)" 
//				+")";
//		String createTable="create table "+ Params.Sqlist.TABLE_NAME +" ("
//				+Params.Sqlist.CUSTOMER_ID 		+ "  integer  primary key autoincrement,"
//				+ Params.Sqlist.PHONE  +"  TEXT " 
//				+" )";
		String createTableSQL = "create table " + Params.Sqlist.TABLE_NAME + "("
				+Params.Sqlist.CUSTOMER_ID 		+ " integer  primary key autoincrement,"
				+Params.Sqlist.PHONE 	+ " nvarchar(50)"
				+")";
		//System.out.println("create`````````````  +"+createTable);
	
		db.execSQL(createTableSQL);
	
	} 

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	//插入方法
	public boolean insertData( SQLiteDatabase db,ContentValues values){
		try {
			return db.insert(Params.Sqlist.TABLE_NAME, null, values)>0? true :false;
		} catch (Exception e) {
			System.out.println("插入数据错误:"+e.getMessage());
			e.printStackTrace();
			return false;
		}
	
	}

}
