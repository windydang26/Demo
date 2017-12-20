package windydang.com.demo.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import windydang.com.demo.Model.Message;

/**
 * Created by windydang on 12/20/17.
 */

public class DBManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "message_manager";
    private static final String TABLE_NAME = "messages";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String CONTENT = "content";
    private static final String TIME = "time";
    private static final int VERSION = 1;

    private String SQLQuery = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " integer primary key, " +
            NAME + " TEXT, " +
            TIME + " TEXT, " +
            CONTENT + " TEXT)";
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

    public void addMessage(Message message) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, message.getName());
        values.put(CONTENT, message.getContent());
        values.put(TIME, message.getTime());
        values.put(ID, message.getId());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Message> getAllMessage() {
        List<Message> listMessage = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Message message = new Message();
                message.setId(cursor.getInt(0));
                message.setName(cursor.getString(1));
                message.setTime(cursor.getString(2));
                message.setContent(cursor.getString(3));
                listMessage.add(message);
            } while(cursor.moveToNext());
        }
        db.close();
        return listMessage;
    }
}
