package kokosoft.com.alarmclock.clock;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by piotr on 3/2/15.
 */
public class ClockService extends Service {

    private final IBinder mBinder = new LocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class LocalBinder extends Binder {
        ClockService getService() {
            // Return this instance of LocalService so clients can call public methods
            return ClockService.this;
        }
    }

}
