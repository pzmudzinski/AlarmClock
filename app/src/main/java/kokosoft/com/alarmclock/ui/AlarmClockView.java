package kokosoft.com.alarmclock.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import kokosoft.com.alarmclock.R;
import kokosoft.com.alarmclock.data.AlarmClock;
import kokosoft.com.alarmclock.utils.DateUtils;

/**
 * Created by piotr on 3/3/15.
 */
@EViewGroup(R.layout.row_alarm_clock)
public class AlarmClockView extends RelativeLayout {

    @ViewById(R.id.textViewDescription)
    protected TextView textViewDescription;
    @ViewById(R.id.textViewTime)
    protected TextView textViewTime;
    @ViewById(R.id.textViewDays)
    protected TextView textViewDays;
    @ViewById(R.id.checkBox)
    protected CheckBox checkBox;

    public AlarmClockView(Context context) {
        super(context);
    }

    public AlarmClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AlarmClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void showAlarmClock(AlarmClock alarmClock)
    {
        textViewDescription.setText(alarmClock.getDescription());

        DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:mm");
        textViewTime.setText(fmt.print(alarmClock.getTime()));
        checkBox.setEnabled(alarmClock.isEnabled());
        String[] daysText = DateUtils.getNamesForAllDays(3);
        SpannableString spanString = new SpannableString(TextUtils.join(" ", daysText).toUpperCase());

        int startIndex = 0;
        for (int i = 0; i < daysText.length; i++)
        {
            int nameLength = daysText[i].length();
            if (alarmClock.isEnabledOnDay(i + 1))
            {
                spanString.setSpan(new StyleSpan(Typeface.BOLD), startIndex, startIndex + nameLength, 0);
                spanString.setSpan(new StyleSpan(Typeface.NORMAL), startIndex, startIndex + nameLength, 0);
            } else {
            }

            startIndex += nameLength + 1;
        }

        textViewDays.setText(spanString);

        textViewDescription.setText(alarmClock.getDescription());
    }
}
