package name.pehl.piriti.sample.client.util;

/**
 * Utility for measuring and outputting elapsed times.
 * <p>
 * Typical usage:
 * 
 * <pre>
 * StopWatch timer = new StopWatch().start();
 * // do something...
 * System.out.println(&quot;elapsed time: &quot; + timer.stop());
 * </pre>
 */
public final class StopWatch
{
    private long startTime;
    private boolean isRunning;


    /**
     * Constructs a new StopWatch in the 'stopped' state.
     */
    public StopWatch()
    {
        this.startTime = System.currentTimeMillis();
        this.isRunning = false;
    }


    /**
     * Starts the stopwatch
     */
    public StopWatch start()
    {
        this.isRunning = true;
        startTime = System.currentTimeMillis();
        return this;
    }


    /**
     * Stops the stopwatch and returns the elapsed time.
     * 
     * @throws IllegalStateException
     *             if the stopwatch was not running at the time this method was
     *             invoked.
     */
    public TimeInterval stop()
    {
        if (!this.isRunning)
        {
            throw new IllegalStateException("StopWatch is already stopped");
        }

        this.isRunning = false;
        return calcTimeInterval();
    }


    public String toString()
    {
        if (this.isRunning)
        {
            return calcTimeInterval().toString();
        }
        else
        {
            return "<StopWatch not running>";
        }
    }


    private TimeInterval calcTimeInterval()
    {
        return new TimeInterval(System.currentTimeMillis() - startTime);
    }

    /**
     * Represents a span of time.
     */
    public static class TimeInterval
    {
        private long interval;


        private TimeInterval(long interval)
        {
            this.interval = interval;
        }


        public long ms()
        {
            return interval;
        }


        public double seconds()
        {
            return (double) interval / (double) 1000;
        }


        public String toString()
        {
            return interval + " ms";
        }
    }
}
