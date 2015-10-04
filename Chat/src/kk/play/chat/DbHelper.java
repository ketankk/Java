package kk.play.chat;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "instachat.db";
	private static final int DATABASE_VERSION = 1;

	private String CREATE_PROFILE_TABLE = "CREATE TABLE profile(_id integer primary key autoincrement,"
			+ "												email text unique,count integer 0)";
	private String CREATE_MESSAGE_TABLE = "CREATE TABLE message(_id integer primary key autoincrement,"
			+ "		msg text,email text,email2 text,at datetime default current_timestamp)";

	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_MESSAGE_TABLE);
		db.execSQL(CREATE_PROFILE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
