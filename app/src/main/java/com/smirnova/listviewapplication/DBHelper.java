package com.smirnova.listviewapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "flagstore.db"; // название бд
    private static final int DB_VERSION = 1; // версия базы данных
    static final String TABLE = "stats"; // название таблицы в бд
    // названия столбцов
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAMESTAT = "name";
    public static final String COLUMN_CAPITAL = "capital";
    public static final String FLAGRESURCE = "flagResource";


    public DBHelper(@Nullable Context context) {
        super(context,DATABASE_NAME , null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE +" ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAMESTAT + " TEXT, "
                + COLUMN_CAPITAL + " TEXT, "
                + FLAGRESURCE +" INTEGER);");
        // добавление начальных данных
        initialData(db);
    }

    void initialData(SQLiteDatabase db)
    {
        db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_NAMESTAT
                + ", " + COLUMN_CAPITAL + ", "+ FLAGRESURCE  + ") VALUES ('Россия', 'Москва', R.drawable.flag_of_russia);");
        db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_NAMESTAT
                + ", " + COLUMN_CAPITAL + ", "+ FLAGRESURCE  + ") VALUES ('Франция', 'Париж', R.drawable.france);");

        // добавьте сюда остальные данные
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);

    }
}
