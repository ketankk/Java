package kk.play.database;

import java.util.ArrayList;
import java.util.List;

import kk.play.entity.Cycles;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CyclesDBHandler extends SQLiteOpenHelper{
	
	
	private static final String DATABASE_NAME = "KeshriSpares";
	private static final int DATABASE_VERSION = 1;

	private static final String TABLE_NAME = "cycles";
	private static final String TYPE = "type";

	private static final String itemId = "_id";
	private static final String NAME = "compName";
	private static final String IMAGE = "img";
	private static final String DESCRIPTION = "desc";
	private static final String COLOR="color";
	private static final String SIZE="size";
	private static final String QUANTITY = "quantity";
	

	private static final String CREATE_ITEM_TABLE = "create table "
			+ TABLE_NAME + "(" + itemId + " integer primary key autoincrement,"
			+ NAME + " varchar(50)," + IMAGE + " varchar(20)," + DESCRIPTION
			+ " varchar(100),"+COLOR+" varchar(10),"+SIZE+" int(5),"+QUANTITY+" int(5),"+TYPE+" varchar(20)"+ ");";

		SQLiteDatabase db;


	public CyclesDBHandler(Context context) {
		super(context, NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(CREATE_ITEM_TABLE);
		Log.d("Query", CREATE_ITEM_TABLE);
		//db.close();
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		 
        // Create tables again
        onCreate(db);
	}
	
	public List<Cycles> getCyclesByType(String type){
		db=this.getReadableDatabase();
		Cursor cursor=db.query(TABLE_NAME, new String[]{itemId,NAME,IMAGE,DESCRIPTION,QUANTITY,SIZE,COLOR}, TYPE+"=?", new String[]{type}, null, null, null);
		
		
		List<Cycles> cycleList=new ArrayList<Cycles>();
		//if(cursor!=null)//Empty DataBase Table
			//cursor.moveToFirst();
		while(cursor.moveToNext()){
			
			Cycles cycle=new Cycles();
			
			cycle.setId(Integer.parseInt(cursor.getString(0)));
			cycle.setNAME(cursor.getString(1));
			cycle.setIMAGE(cursor.getString(2));
			cycle.setDESCRIPTION(cursor.getString(3));
			cycle.setQUANTITY(Integer.parseInt(cursor.getString(4)));
			cycle.setSIZE(Integer.parseInt(cursor.getString(5)));
			cycle.setCOLOR(cursor.getString(6));
			
			cycleList.add(cycle);
			
		}
		db.close();
			return cycleList;
		
	}
	

}
