package kokosoft.com.alarmclock.utils;

import android.util.Log;

/**
 * Created by piotr on 3/2/15.
 */
public class Logger {
    public static void log(String tag, String msg)
    {
        Log.d(tag, msg);
    }

    public static void error(String tag, String msg)
    {
        Log.e(tag, msg);
    }

    public static void error(String tag, String msg, Throwable throwable)
    {
        Log.e(tag,msg, throwable);
    }

}
