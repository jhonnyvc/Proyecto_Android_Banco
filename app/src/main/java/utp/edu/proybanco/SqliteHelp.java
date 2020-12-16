package utp.edu.proybanco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class SqliteHelp extends SQLiteOpenHelper {
    static int version=1;
    static final String base="bdbanco.db";
    static final String tabla1="create table banco(id integer primary key autoincrement,cliente text,saldo real)";

    public SqliteHelp(@Nullable Context context) {
        super(context, base, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(tabla1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists banco");
        Log.w("men","actualizando"+oldVersion+"con"+newVersion);
        db.execSQL(tabla1);
    }
}
