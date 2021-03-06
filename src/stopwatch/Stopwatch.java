package stopwatch;

public class Stopwatch {
    private int startTime, stopTime, elapsedTime;

    public Stopwatch() {
        reset();
    }

    public void reset() {
        startTime = 0;
        stopTime = 0;
        elapsedTime = 0;
    }

    public void start() {
        startTime = nanoToCenti(System.nanoTime());
    }

    public void stop() {
        stopTime = nanoToCenti(System.nanoTime());
        elapsedTime += stopTime - startTime;
    }

    private int getCurrentTimeElapsed() {
        return nanoToCenti(System.nanoTime()) - startTime + elapsedTime;
    }

    private static int nanoToCenti(long nano) {
        return (int) (nano / Math.pow(10,7));
    }

    public String getStoppedTime() {
        return formatTime(elapsedTime);
    }

    public String getCurrentTime() {
        return formatTime(getCurrentTimeElapsed());
    }

    private String formatTime(int time) {
        StringBuilder timeString = new StringBuilder();
        int centiSeconds = getCentiSeconds(time);
        int seconds = getSeconds(time);
        int minutes = getMinutes(time);
        int hours = getHours(time);

        timeString.append(hours < 10 ? "0" + hours : hours);
        timeString.append(":");
        timeString.append(minutes < 10 ? "0" + minutes : minutes);
        timeString.append(":");
        timeString.append(seconds < 10 ? "0" + seconds : seconds);
        timeString.append(".");
        timeString.append(centiSeconds < 10 ? "0" + centiSeconds : centiSeconds);

        return timeString.toString();
    }

    private static int getCentiSeconds(int centiSeconds) {
        return (centiSeconds -
                (getSeconds(centiSeconds) * Constants.CENTISECONDS_PER_SECOND) -
                (getMinutes(centiSeconds) * Constants.CENTISECONDS_PER_MINUTE) -
                (getHours(centiSeconds) * Constants.CENTISECONDS_PER_HOUR));
    }

    private static int getSeconds(int centiSeconds) {
        return ((centiSeconds / Constants.CENTISECONDS_PER_SECOND) -
                (getMinutes(centiSeconds) * Constants.SECONDS_PER_MINUTE) -
                (getHours(centiSeconds) * Constants.SECONDS_PER_HOUR));
    }

    private static int getMinutes(int centiSeconds) {
        return ((centiSeconds / Constants.CENTISECONDS_PER_MINUTE) -
                getHours(centiSeconds) * Constants.MINUTES_PER_HOUR);
    }

    private static int getHours(int centiSeconds) {
        return (centiSeconds / Constants.CENTISECONDS_PER_HOUR);
    }
}
