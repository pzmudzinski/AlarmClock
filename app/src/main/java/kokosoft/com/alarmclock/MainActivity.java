package kokosoft.com.alarmclock;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import kokosoft.com.alarmclock.data.StorageProvider;
import kokosoft.com.alarmclock.ui.AlarmActionBarActivity;
import kokosoft.com.alarmclock.ui.AlarmClockListFragment_;


public class MainActivity extends AlarmActionBarActivity implements StorageProvider{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new AlarmClockListFragment_())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            AddAlarmActivity_.intent(this).start();
        }

        return super.onOptionsItemSelected(item);
    }



}
