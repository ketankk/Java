package kk.play.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "abcd";
	private static final int DATABASE_VERSION = 1;

	private static final String TABLE_NAME = "items";
	private static final String itemId = "_id";
	private static final String NAME = "itemName";
	private static final String IMAGE = "img";
	private static final String DESCRIPTION = "desc";
	private static final int QUANTITY = 0;
	//private static final int CAT_ID = 0;
	private static final int SUB_CAT_ID = 0;


	private static final String CREATE_ITEM_TABLE = "create table "
			+ TABLE_NAME + "(" + itemId + " integer primary key autoincrement,"
			+ NAME + " varchar(50)," + IMAGE + " varchar(20)," + DESCRIPTION
			+ " varchar(100));";

	private static final String CATEGORY = "itemCat";
	private static final String SUB_CATEGORY = "itemSubCat";

	SQLiteDatabase db;

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

		db = getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("created", "sss");
		System.out.println("oncreate");
		db.execSQL(CREATE_ITEM_TABLE);
		db.close();

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d("up", "grade");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}

	public String get() {
		SQLiteDatabase db;
		String name = null;
		db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, new String[] { NAME }, null, null,
				null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		name = cursor.getString(0);
		return name;

	}

}
