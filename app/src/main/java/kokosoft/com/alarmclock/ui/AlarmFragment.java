package kokosoft.com.alarmclock.ui;

import android.support.v4.app.Fragment;

import kokosoft.com.alarmclock.data.AlarmClockStorage;
import kokosoft.com.alarmclock.data.StorageProvider;

/**
 * Created by piotr on 3/3/15.
 */
public class AlarmFragment extends Fragment implements StorageProvider {
    @Override
    public AlarmClockStorage getAlarmClockStorage() {
        if (getActivity() instanceof StorageProvider)
            return ((StorageProvider) getActivity()).getAlarmClockStorage();
        else
            return null;
    }
}
