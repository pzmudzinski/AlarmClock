package kokosoft.com.alarmclock.data.sql;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import kokosoft.com.alarmclock.data.AlarmClock;
import kokosoft.com.alarmclock.utils.Logger;

/**
 * Created by piotr on 3/2/15.
 */
public class AlarmClockDBHelper extends OrmLiteSqliteOpenHelper implements DBTables {

    public AlarmClockDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public AlarmClockDBHelper(Context context, String dbName) {
        super(context, dbName, new SQLiteDatabase.CursorFactory() {
            @Override
            public Cursor newCursor(SQLiteDatabase sqLiteDatabase, SQLiteCursorDriver sqLiteCursorDriver, String s, SQLiteQuery sqLiteQuery) {
                Logger.log("db", "query: " + sqLiteQuery.toString());
                return new SQLiteCursor(sqLiteDatabase, sqLiteCursorDriver, s, sqLiteQuery);
            }
        }, DATABASE_VERSION);

    }

    public AlarmClockDBHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, AlarmClock.class);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            Logger.error("db", e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        Log.d("db", String.format("onUpgrade: %d => %d", oldVersion, newVersion));
        if (REVISIONS.length < 1) {
            return;
        }
        for (int i = oldVersion; i < newVersion; i++) {
            try {
                Revision revision = REVISIONS[i - 1];
                revision.execute(this, connectionSource);
            } catch (Exception e) {
                Log.d("db", "Version up error", e);
                throw new RuntimeException(e);
            }
        }
        onCreate(database, connectionSource);
    }

    private static interface Revision {
        public void execute(OrmLiteSqliteOpenHelper openHelper, ConnectionSource connectionSource)
                throws SQLException;
    }

    /**
     * Don't include "CREATE TABLE" here
     */
    private static final Revision[] REVISIONS = new Revision[]{
// Example
//            new Revision() {
//                @Override
//                public void execute(DatabaseOpenHelper openHelper, ConnectionSource connectionSource)
//                        throws SQLException {
//                    openHelper.getDao(Card.class).executeRaw(String.format(
//                            "ALTER TABLE card ADD COLUMN %s VARCHAR",
//                            Card.Column.DICTIONARY));
//                }
//            }
    };

}
