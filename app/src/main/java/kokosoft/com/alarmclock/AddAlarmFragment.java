package kokosoft.com.alarmclock;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import kokosoft.com.alarmclock.data.AlarmClock;
import kokosoft.com.alarmclock.data.AlarmClockStorage;
import kokosoft.com.alarmclock.ui.AlarmFragment;
import kokosoft.com.alarmclock.utils.DateUtils;

/**
 * Created by piotr on 3/2/15.
 */
@EFragment(R.layout.fragment_add_alarm)
public class AddAlarmFragment extends AlarmFragment{

    @ViewById(R.id.timePicker)
    protected TimePicker timePicker;

    protected Long editedAlarmClockID;

    @ViewById(R.id.buttonRepeat)
    protected Button buttonRepeat;

    @ViewById(R.id.buttonAdd)
    protected Button buttonAdd;

    @ViewById(R.id.editTextDescription)
    protected EditText editTextDescription;

    protected AlarmClock alarmClock = new AlarmClock();

    @AfterInject
    protected void init()
    {

    }

    @AfterViews
    protected void initViews()
    {

    }

    @Click(R.id.buttonRepeat)
    protected void onRepeatButtonClicked()
    {
        String[] dayNames = DateUtils.getNamesForAllDays();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final boolean[] checkedItems = new boolean[dayNames.length];
        if (isInEditMode())
        {
           for (int i = 1; i < alarmClock.getDaysSet().length() ; i++)
           {
               checkedItems[i-1] = alarmClock.getDaysSet().get(i);
           }
        }

        builder.setMultiChoiceItems(dayNames, checkedItems,new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                checkedItems[which] = isChecked;
            }
        });

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int i = 0 ; i < checkedItems.length ; i++)
                {
                    alarmClock.setDayEnabled(i+1, checkedItems[i]);
                }
                bindAlarmClock(alarmClock);
                //dialog.dismiss();
            }
        });

        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    @Click(R.id.buttonAdd)
    protected void onAddButtonClicked()
    {
        AlarmClockStorage clockStorage = getAlarmClockStorage();
        alarmClock.setTime(AlarmClock.dateTimeFromTimePicker(timePicker));
        alarmClock.setDescription(editTextDescription.getText().toString());

        if (isInEditMode()) {
            clockStorage.updateAlarm(alarmClock);
        } else{
            clockStorage.addAlarm(alarmClock);
        }

        getActivity().finish();
    }

    private boolean isInEditMode()
    {
        return editedAlarmClockID != null;
    }

    public void setEditModeForAlarm(Long editedAlarmClockID) {
        this.editedAlarmClockID = editedAlarmClockID;
        AlarmClockStorage clockStorage = getAlarmClockStorage();
        AlarmClock alarmClock = clockStorage.alarmWithId(editedAlarmClockID);
        this.alarmClock = alarmClock;
        bindAlarmClock(alarmClock);
        buttonAdd.setText(isInEditMode()? R.string.edit_alarm : R.string.add_alarm);
    }

    protected void bindAlarmClock(AlarmClock alarmClock)
    {
        timePicker.setCurrentHour(alarmClock.getTime().getHourOfDay());
        timePicker.setCurrentMinute(alarmClock.getTime().getMinuteOfDay());
        String daysTitle = DateUtils.getFrequencyDescriptionForDays(getActivity(), alarmClock.getDaysSet(), 3);
        Resources res = getResources();
        String buttonTitle = String.format(res.getString(R.string.repeat), daysTitle);
        buttonRepeat.setText(buttonTitle);
    }
}
