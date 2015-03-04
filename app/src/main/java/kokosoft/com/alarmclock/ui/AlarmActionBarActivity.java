package kokosoft.com.alarmclock.ui;

import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import kokosoft.com.alarmclock.data.AlarmClockStorage;
import kokosoft.com.alarmclock.data.StorageProvider;
import kokosoft.com.alarmclock.data.sql.AlarmClockDBHelper;
import kokosoft.com.alarmclock.data.sql.SQLAlarmClockStorage;

/**
 * Created by piotr on 3/3/15.
 */
public class AlarmActionBarActivity extends ActionBarActivity implements StorageProvider {


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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}