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
        int timeout = firstTimeout;
        long finishTime = 0L;

        noStopRequested = true;

        while (noStopRequested) {
            long startTime = System.currentTimeMillis();
            try {
                Thread.sleep(timeout);
                elapse();
                finishTime = System.currentTimeMillis();
            } catch (InterruptedException x) {
                x.printStackTrace();
            }

            int adjust = (int) (finishTime - startTime);
            System.out.println(adjust);

            timeout = adjust > 1000 ? firstTimeout - (adjust - 1000) : firstTimeout + (1000 - adjust);


        }
    }

    private void elapse() {
        if (seconds == 59) {
            seconds = 0;
            minutes++;
            if (minutes == 60) {
                minutes = 0;
                hours++;
                if (hours == 24) {
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
