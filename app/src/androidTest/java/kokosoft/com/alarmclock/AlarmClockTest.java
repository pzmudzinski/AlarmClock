package kokosoft.com.alarmclock;

import android.test.AndroidTestCase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;

import kokosoft.com.alarmclock.data.AlarmClockStorage;
import kokosoft.com.alarmclock.data.sql.AlarmClockDBHelper;
import kokosoft.com.alarmclock.data.sql.SQLAlarmClockStorage;

/**
 * Created by piotr on 3/2/15.
 */
public class AlarmClockTest extends AndroidTestCase{

    protected AlarmClockStorage dbStore;
    protected OrmLiteSqliteOpenHelper dbHelper;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.dbHelper = new AlarmClockDBHelper(getContext(), null);
        this.dbStore = new SQLAlarmClockStorage(getContext(), dbHelper);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        dbHelper.close();
    }
}
