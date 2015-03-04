package kokosoft.com.alarmclock.data.sql;

import android.content.Context;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import kokosoft.com.alarmclock.data.AlarmClock;
import kokosoft.com.alarmclock.data.AlarmClockStorage;
import kokosoft.com.alarmclock.utils.Logger;

/**
 * Created by piotr on 3/2/15.
 */
public class SQLAlarmClockStorage implements AlarmClockStorage {

    protected OrmLiteSqliteOpenHelper helper;

    public SQLAlarmClockStorage(Context context, OrmLiteSqliteOpenHelper helper) {
        super();
        this.helper = helper;
    }

    @Override
    public AlarmClock alarmWithId(long id) {
        try {
            Dao<AlarmClock, Long> dao = helper.getDao(AlarmClock.class);
            return  dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.error("db", e.getMessage());
        }
        return null;
    }

    @Override
    public List<AlarmClock> findAllAlarms() {
        try {
            return getDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean removeAlarmWithId(long id) {
        try {
            return getDao().deleteById(id) == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public long addAlarm(AlarmClock alarm) {
        try {
            Dao<AlarmClock, Long> dao = getDao();
            dao.create(alarm);
            return alarm.getId();
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.error("db", e.getMessage());
        }
        return 0;
    }

    @Override
    public boolean updateAlarm(AlarmClock alarm) {
        try {
            return getDao().update(alarm) == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Dao<AlarmClock, Long> getDao()
    {
        try {
            Dao<AlarmClock, Long> dao = helper.getDao(AlarmClock.class);
            return dao;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
