package windydang.com.demo.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by windydang on 12/20/17.
 */

public class DBManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "";
    private static final String TABLE_NAME = "";
    private static final String ID = "";
    private static final String NAME = "";
    private static final String ADDRESS = "";
    private static final String PHONE_NUMBER = "";
    private static final String EMAIL = "";
    private static final int VERSION = 1;

    private String SQLQuery = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " integer primary key, " +
            NAME + " TEXT, " +
            EMAIL + " TEXT, " +
            PHONE_NUMBER + " TEXT, " +
            ADDRESS + " TEXT)";
    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
