package kokosoft.com.alarmclock.data.sql;

import android.provider.BaseColumns;

/**
 * Created by piotr on 3/2/15.
 */
public interface DBColumns extends BaseColumns {

    public static final String ENABLED = "is_enabled";
    public static final String TIME = "time";
    public static final String DESCRIPTION = "description";
    public static final String DAYS = "days";
}
