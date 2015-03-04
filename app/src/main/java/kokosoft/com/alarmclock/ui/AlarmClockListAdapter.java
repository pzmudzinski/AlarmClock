package kokosoft.com.alarmclock.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import kokosoft.com.alarmclock.data.AlarmClock;

/**
 * Created by piotr on 3/3/15.
 */
public class AlarmClockListAdapter extends BaseAdapter {

    private List<AlarmClock> alarmClocks;
    private Context context;

    public AlarmClockListAdapter(Context context, List<AlarmClock> alarmClockList)
    {
        super();
        this.alarmClocks = alarmClockList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return alarmClocks.size();
    }

    public void refill(List<AlarmClock> alarmClocks)
    {
        if (this.alarmClocks != null)
        {
            this.alarmClocks.clear();
            this.alarmClocks.addAll(alarmClocks);
        }
        notifyDataSetChanged();
    }

    @Override
    public Object getItem(int position) {
        return alarmClocks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alarmClocks.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AlarmClockView alarmClockView;
        if (convertView == null)
        {
            alarmClockView = AlarmClockView_.build(context);
        } else {
            alarmClockView = (AlarmClockView) convertView;
        }

        alarmClockView.showAlarmClock((AlarmClock) getItem(position));
        return alarmClockView;
    }
}
