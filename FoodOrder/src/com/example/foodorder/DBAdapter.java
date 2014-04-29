package com.example.foodorder;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
	
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;
	private final Context context;
	
	public DBAdapter(Context ctx)
	{
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}
	
	public static class DatabaseHelper extends SQLiteOpenHelper
	{
		DatabaseHelper(Context context)
		{
			super(context, "OrderHistory", null, 1);
		}
		@Override
		public void onCreate(SQLiteDatabase db)
		{
			db.execSQL("create table orders (id integer primary key autoincrement, "
					+ "items text not null, order_date text not null);");
		}
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion,
				int newVersion)
		{
			Log.w("DBAdapter", "Upgrading database from version " + oldVersion
					+ " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS orders");
			onCreate(db);
		}
	}

	//---open database---

	public DBAdapter open() throws SQLException
	{
		db = DBHelper.getWritableDatabase();
		return this;
	}
	//---close database---

	public void close()
	{
		DBHelper.close();
	}
	//---insert an order to database---

	public void insertOrder(String items, String date)
	{
		SQLiteDatabase db = DBHelper.getWritableDatabase();
        db.execSQL("insert into orders (items,order_date) values(?,?)", new Object[] { items, date });  
        db.close();
	}
	
	//---delete all orders from database---
	
	public void deleteOrders() {
		SQLiteDatabase db = DBHelper.getWritableDatabase();
		db.delete("orders", "id"+">"+0 , null);
		db.close();
	}
	//---get all orders history---
	
	public List<Orders> findAll() {
        List<Orders> lists = new ArrayList<Orders>();  
        Orders _order = null;  
        SQLiteDatabase db = DBHelper.getReadableDatabase();  
        Cursor cursor = db.rawQuery("select * from orders ", null);  
        while (cursor.moveToNext()) {  
            _order = new Orders();  
            _order.setId(cursor.getInt(cursor.getColumnIndex("id")));  
            _order.setItems(cursor.getString(cursor.getColumnIndex("items")));  
            _order.setOrderDate(cursor.getString(cursor.getColumnIndex("order_date")));  
            lists.add(_order);  
        }  
        db.close();  
        return lists;  
    }    

	
}
