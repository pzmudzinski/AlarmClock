package kokosoft.com.alarmclock;

import android.widget.TimePicker;

import kokosoft.com.alarmclock.data.AlarmClock;
import kokosoft.com.alarmclock.utils.DateUtils;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by piotr on 3/2/15.
 */
public class AlarmClockStorageTest extends AlarmClockTest{


    public void testAddingAlarmClock()
    {
        long insertedAlarmClock = addSampleAlarmClock();
        assertThat(insertedAlarmClock).isNotEqualTo((long)0);
    }

    public void testFindingAlarmClock()
    {
        long insertedAlarmClock = addSampleAlarmClock();
        assertThat(dbStore.alarmWithId(insertedAlarmClock)).isNotNull();
    }

    public void testUpdatingAlarmClock()
    {
        long insertedAlarmClock = addSampleAlarmClock();
        AlarmClock alarmClock = dbStore.alarmWithId(insertedAlarmClock);
        alarmClock.setDescription("LOL");
        dbStore.updateAlarm(alarmClock);
        assertThat(dbStore.alarmWithId(insertedAlarmClock).getDescription()).isEqualTo("LOL");
    }

    public void testDeletingAlarmClock()
    {
        long insertedAlarmClock = addSampleAlarmClock();
        dbStore.removeAlarmWithId(insertedAlarmClock);
        assertThat(dbStore.alarmWithId(insertedAlarmClock)).isNull();
    }

    public void testEnablingDaysOfWeek()
    {
        long insertedAlarmClock = addSampleAlarmClock();
        AlarmClock alarmClock = dbStore.alarmWithId(insertedAlarmClock);
        for (int day : DateUtils.DAYS)
        {
            alarmClock.setDayEnabled(day, day%2==0);
        }

        dbStore.updateAlarm(alarmClock);

        AlarmClock updatedAlarm = dbStore.alarmWithId(insertedAlarmClock);

        for (int day : DateUtils.DAYS)
        {
            assertThat(updatedAlarm.isEnabledOnDay(day)).isEqualTo(day%2==0);
        }
    }

    public void testGettingAllAlarms()
    {
        addSampleAlarmClock();
        addSampleAlarmClock();
        assertThat(dbStore.findAllAlarms().size()).isEqualTo(2);
    }

    private long addSampleAlarmClock()
    {
        TimePicker timePicker = new TimePicker(getContext());
        timePicker.setCurrentHour(10);
        timePicker.setCurrentMinute(5);

        AlarmClock alarmClock = AlarmClock.alarmFromTimePicker(timePicker,"desc");
        return dbStore.addAlarm(alarmClock);
    }
}
