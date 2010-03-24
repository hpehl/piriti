package name.pehl.piriti.sample.client.util;

/**
 * Represents a span of time.
 */
public class TimeInterval
{
    private long interval;


    TimeInterval(long interval)
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