package kokosoft.com.alarmclock;

import android.test.ServiceTestCase;

import kokosoft.com.alarmclock.clock.ClockService;

/**
 * Created by piotr on 3/4/15.
 */
public class AlarmClockServiceTest extends ServiceTestCase<ClockService> {
    /**
     * Constructor
     *
     * @param serviceClass The type of the service under test.
     */
    public AlarmClockServiceTest(Class<ClockService> serviceClass) {
        super(serviceClass);
    }


}
