package kokosoft.com.alarmclock;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.FragmentById;

import kokosoft.com.alarmclock.ui.AlarmActionBarActivity;

/**
 * Created by piotr on 3/2/15.
 */
@EActivity(R.layout.activity_add_alarm)
public class AddAlarmActivity extends AlarmActionBarActivity {

    @Extra
    protected Long editedAlarmClockID;

    @FragmentById(R.id.fragment)
    protected AddAlarmFragment addAlarmFragment;

    @AfterViews
    protected void init()
    {
        if (editedAlarmClockID != null)
        {
            addAlarmFragment.setEditModeForAlarm(editedAlarmClockID);
            getSupportActionBar().setTitle(R.string.edit_alarm);
        } else {
            getSupportActionBar().setTitle(R.string.add_alarm);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
