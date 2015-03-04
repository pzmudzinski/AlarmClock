package kokosoft.com.alarmclock.data;

import java.util.List;

/**
 * Created by piotr on 3/2/15.
 */
public interface AlarmClockStorage {

    public AlarmClock alarmWithId(long id);
    public List<AlarmClock> findAllAlarms();
    public boolean removeAlarmWithId(long id);
    public long addAlarm(AlarmClock alarm);
    public boolean updateAlarm(AlarmClock alarm);
}
