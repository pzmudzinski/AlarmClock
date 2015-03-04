package kokosoft.com.alarmclock.utils;

import android.content.Context;
import android.text.TextUtils;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import kokosoft.com.alarmclock.R;

/**
 * Created by piotr on 3/3/15.
 */
public class DateUtils {

    public static final int[] DAYS = {
            DateTimeConstants.MONDAY,
            DateTimeConstants.TUESDAY,
            DateTimeConstants.WEDNESDAY,
            DateTimeConstants.THURSDAY,
            DateTimeConstants.FRIDAY,
            DateTimeConstants.SATURDAY,
            DateTimeConstants.SUNDAY
    };

    private static DateTimeFormatter dayNameFormatter = DateTimeFormat.forPattern("EEEE");

    public static String getDayName(int day, int charLimit)
    {
        LocalDate date = new LocalDate();
        date = date.withDayOfWeek(day);
        return charLimit == - 1? dayNameFormatter.print(date) : dayNameFormatter.print(date).substring(0, charLimit);
    }

    public static String getDayName(int day)
    {
        return getDayName(day, -1);
    }

    public static String[] getNamesForAllDays()
    {
        return new String[]
                {
                        getDayName(DateTimeConstants.MONDAY),
                        getDayName(DateTimeConstants.TUESDAY),
                        getDayName(DateTimeConstants.WEDNESDAY),
                        getDayName(DateTimeConstants.THURSDAY),
                        getDayName(DateTimeConstants.FRIDAY),
                        getDayName(DateTimeConstants.SATURDAY),
                        getDayName(DateTimeConstants.SUNDAY)
                };
    }

    public static String[] getNamesForAllDays(int charLimit)
    {
        return new String[]
                {
                        getDayName(DateTimeConstants.MONDAY, charLimit),
                        getDayName(DateTimeConstants.TUESDAY, charLimit),
                        getDayName(DateTimeConstants.WEDNESDAY, charLimit),
                        getDayName(DateTimeConstants.THURSDAY, charLimit),
                        getDayName(DateTimeConstants.FRIDAY, charLimit),
                        getDayName(DateTimeConstants.SATURDAY, charLimit),
                        getDayName(DateTimeConstants.SUNDAY, charLimit)
                };
    }

    public static String getDaysFirstLetters(int charLimit)
    {
        StringBuffer result = new StringBuffer();
        String name;
        for (int day : DAYS)
        {
            name = getDayName(day);
            result.append(name.substring(0,charLimit == -1? name.length() : charLimit).toUpperCase() + " ");
        }
        return result.toString();
    }

    public static String getFrequencyDescriptionForDays(Context context, BitSet daysSet, int charLimit)
    {
        BitSet firstBitSet = (BitSet) daysSet.clone();
        firstBitSet.set(0, true);
        boolean hasAllDaysIn = firstBitSet.cardinality() == firstBitSet.size();
        boolean hasZeroDaysIn = firstBitSet.cardinality() == 1;
        if (hasAllDaysIn)
            return context.getString(R.string.every_day);
        else if (hasZeroDaysIn)
            return context.getString(R.string.never);
        else {
            List<String> days = new ArrayList<String>();
            for (int day : DAYS)
            {
                if (daysSet.get(day))
                    days.add(getDayName(day).substring(0,charLimit));
            }

            return TextUtils.join(",", days.toArray());
        }
    }
}
