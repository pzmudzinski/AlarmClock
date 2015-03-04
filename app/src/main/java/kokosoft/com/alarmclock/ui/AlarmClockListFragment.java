package kokosoft.com.alarmclock.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import kokosoft.com.alarmclock.AddAlarmActivity_;
import kokosoft.com.alarmclock.R;
import kokosoft.com.alarmclock.data.AlarmClock;

/**
 * Created by piotr on 3/3/15.
 */
@EFragment(R.layout.fragment_alarms)
public class AlarmClockListFragment extends AlarmFragment {

    @ViewById(R.id.listView)
    protected ListView listView;

    @AfterViews
    protected void init()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AddAlarmActivity_.intent(getActivity()).editedAlarmClockID(id).start();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        List<AlarmClock> clockList = getAlarmClockStorage().findAllAlarms();
        if (listView.getAdapter() == null)
            listView.setAdapter(new AlarmClockListAdapter(getActivity(), clockList));
        else {
            AlarmClockListAdapter adapter = (AlarmClockListAdapter) listView.getAdapter();
            adapter.refill(clockList);
        }
    }


}
