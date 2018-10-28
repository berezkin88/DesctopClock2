package project;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * This class purpose is to get the local time for next usage in <code>Processor</code>
 */

public class Time {
    private Calendar calendar;
    private static int hours;
    private static int minutes;
    private static int seconds;

    public Time() {
        calendar = new GregorianCalendar();
        hours = calendar.get(Calendar.HOUR);
        minutes = calendar.get(Calendar.MINUTE);
        seconds = calendar.get(Calendar.SECOND);
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    @Override
    public String toString() {
        return "Time{" + '\n' +
                "hours=" + hours + '\n' +
                "minutes" + minutes + '\n' +
                "seconds" + seconds + '\n' +
                '}';
    }
}
