package com.lib.sqlite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.lib.Debug;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite extends SQLiteOpenHelper {

	private String filename = null;
	private String pathfilename = null;
	private SQLiteDatabase database = null;
	private Context context = null;

	public SQLite(Context context, String filename, CursorFactory factory, int version) {
		super(context, filename, factory, version);
		this.context = context;
		this.filename = filename;
		this.pathfilename = context.getDatabasePath(filename).getPath();
		try {
			isCreatedDatabase();
		} catch (IOException e) {
			Debug.e("Lỗi " + e.getMessage());
		}
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Debug.e("onCreate");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Debug.e("oncreate update");
	}

	public synchronized void close() {
		if (database != null && database.isOpen())
			database.close();
		super.close();
	}

	public long writeDatabase(String table, ContentValues values) throws SQLiteConstraintException{
		if(database == null)
			database = getReadableDatabase();
		return database.insertOrThrow(table, null, values);
	}
	public Cursor selectTable(String querry, String selectionArgs[]){
		return database.rawQuery(querry, selectionArgs);
	}
	public int upDateRowDatabase(String table, ContentValues values, String whereClause, String[] whereArgs) throws SQLiteConstraintException{
		if(database == null)
			database = getReadableDatabase();
		return database.update(table, values, whereClause, whereArgs);
	}
	
	public Cursor readDatabase(String table, String[] colums, String whereClause, String[] whereArgs,  String groupBy, String having, String orderBy) {
		if(database == null)
			database = getReadableDatabase();
		Cursor cursor = null;
		try {
			cursor = database.query(table, colums, whereClause , whereArgs, groupBy, having, orderBy);
		} catch (SQLiteConstraintException e) {
			Debug.e(e.toString());
		}
		return cursor;
	}

	public void isCreatedDatabase() throws IOException {
		if (!checkExistDataBase()) {
			try {
				getReadableDatabase();
				copyDataBase();
			} catch (Exception e) {
				throw new Error("Error copying database " + e.getMessage());
			}
		}
	}

	/**
	 * check whether database exist on the device?
	 * 
	 * @return true if existed
	 */
	private boolean checkExistDataBase() {

		try {
			String myPath = pathfilename;
			File fileDB = new File(myPath);

			if (fileDB.exists()) {
				return true;
			} else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * copy database from assets folder to the device
	 * 
	 * @throws IOException
	 */
	private void copyDataBase() throws IOException {
		InputStream myInput = context.getAssets().open(filename);
		OutputStream myOutput = new FileOutputStream(pathfilename);
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		myOutput.flush();
		myOutput.close();
		myInput.close();
	}

	/**
	 * delete database file
	 * 
	 * @return
	 */
	public boolean deleteDatabase() {
		File file = new File(pathfilename);
		return file.delete();
	}

	/**
	 * open database
	 * 
	 * @throws SQLException
	 */
	public void openDataBase() throws SQLException {
		database = SQLiteDatabase.openDatabase(pathfilename, null, SQLiteDatabase.OPEN_READWRITE);
	}

}
