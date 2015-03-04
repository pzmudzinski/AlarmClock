package kokosoft.com.alarmclock.data;

import android.widget.TimePicker;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.joda.time.DateTime;

import java.util.BitSet;

import kokosoft.com.alarmclock.data.sql.DBColumns;
import kokosoft.com.alarmclock.data.sql.DBTables;

/**
 * Created by piotr on 3/2/15.
 */
@DatabaseTable(tableName = DBTables.TABLE_CLOCKS)
public class AlarmClock {

    @DatabaseField(generatedId = true, unique = true, columnName = DBColumns._ID)
    protected long id;

    @DatabaseField(columnName = DBColumns.ENABLED)
    protected boolean isEnabled = true;

    @DatabaseField(columnName = DBColumns.TIME)
    protected DateTime time;

    @DatabaseField(columnName = DBColumns.DESCRIPTION)
    protected String description;

    /*
         0 - not used
         1 - monday
         2 - tuesday
         ...
         7 - sunday
     */
    @DatabaseField(columnName = DBColumns.DAYS, dataType = DataType.SERIALIZABLE)
    protected BitSet days = new BitSet(8);

    public AlarmClock()
    {
        super();
        this.time = new DateTime();
    }

    public static AlarmClock alarmFromTimePicker(TimePicker picker, String description)
    {
        AlarmClock alarmClock = new AlarmClock();
        alarmClock.description = description;
        DateTime time = dateTimeFromTimePicker(picker);
        alarmClock.time = time;
        return alarmClock;
    }

    public boolean isEnabledOnDay(int day)
    {
        return days.get(day);
    }

    public void setDayEnabled(int day, boolean enabled)
    {
        days.set(day, enabled);
    }
    
    public static DateTime dateTimeFromTimePicker(TimePicker picker)
    {
        DateTime time = new DateTime().withHourOfDay(picker.getCurrentHour()).withMinuteOfHour(picker.getCurrentMinute());
        return time;
    }
    
    public void setDescription(String newDesc)
    {
        this.description = newDesc;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public DateTime getTime() {
        return time;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public BitSet getDaysSet() {
        return days;
    }

    public void setTime(DateTime time) {
        this.time = time;
    }
}
