package kokosoft.com.alarmclock.ui;

import android.support.v4.app.FragmentActivity;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import kokosoft.com.alarmclock.data.AlarmClockStorage;
import kokosoft.com.alarmclock.data.StorageProvider;
import kokosoft.com.alarmclock.data.sql.AlarmClockDBHelper;
import kokosoft.com.alarmclock.data.sql.SQLAlarmClockStorage;

/**
 * Created by piotr on 3/3/15.
 */
public class AlarmFragmentActivity extends FragmentActivity implements StorageProvider{


    private AlarmClockStorage alarmClockStorage = null;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (alarmClockStorage != null) {
            OpenHelperManager.releaseHelper();
            alarmClockStorage = null;
        }
    }

    @Override
    public AlarmClockStorage getAlarmClockStorage() {
        if (alarmClockStorage == null) {

            alarmClockStorage = new SQLAlarmClockStorage(this,  OpenHelperManager.getHelper(this, AlarmClockDBHelper.class));
        }
        return alarmClockStorage;
    }
}
