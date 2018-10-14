package project;

/**
 * The class is counting time
 */

public class Processor implements Runnable{
    private Time time = new Time();
    private int hours = time.getHours();
    private int minutes = time.getMinutes();
    private int seconds = time.getSeconds();

    private boolean noStopRequested;

    public void run() {
        doWork();
    }

    private void doWork() {
        int firstTimeout = 1000;
        int timeout;
        long startTime = System.currentTimeMillis();

        try {
            Thread.sleep(firstTimeout);
            elapse();
        } catch (InterruptedException x) {
            x.printStackTrace();
        }

        long finishTime = System.currentTimeMillis();

        int adjust = (int) (finishTime - startTime);

        timeout = adjust > 1000 ? firstTimeout - (adjust - 1000) : firstTimeout + (1000 - adjust);

        noStopRequested = true;

        while (noStopRequested) {
            try {
                Thread.sleep(timeout);
                elapse();
            } catch (InterruptedException x) {
                x.printStackTrace();
            }
        }
    }

    private void elapse() {
        if (seconds == 59) {
            seconds = 0;
            minutes++;
            if (minutes == 59) {
                minutes = 0;
                hours++;
                if (hours == 23) {
                    hours = 0;
                }
            }
        } else {
            seconds ++;
        }
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
}
