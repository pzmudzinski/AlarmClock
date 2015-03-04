package kokosoft.com.alarmclock;

import android.app.Application;

import org.androidannotations.annotations.EApplication;

/**
 * Created by piotr on 3/3/15.
 */
@EApplication
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //getApplicationContext().deleteDatabase(DBTables.DATABASE_NAME);

    }
}
