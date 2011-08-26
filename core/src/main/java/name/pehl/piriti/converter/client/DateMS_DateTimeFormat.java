package name.pehl.piriti.converter.client;

import java.util.Date;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.i18n.shared.TimeZone;

/**
 * Custom date format to support dates in the following format:
 * 
 * <pre>
 * /Date(1224043200000)/
 * </pre>
 * <p>
 * Thanks to obesga@gmail.com who contributed this class to Piriti.
 * 
 * @see https://groups.google.com/d/topic/piriti/1AWGJmECMak/discussion
 * @see https://groups.google.com/d/topic/piriti/Is3Z3tBDgVM/discussion
 * @author obesga@gmail.com
 */
public class DateMS_DateTimeFormat extends DateTimeFormat
{
    // Me and myself
    public static final DateMS_DateTimeFormat me = new DateMS_DateTimeFormat();

    // Use the one you want
    public static final String DateMS_DateTimeFormatPattern = "DateMS()";
    public static final String DateMS = DateMS_DateTimeFormatPattern;

    // Pre and post string where formatting dates, pretty simple
    public static final String prefix = "/Date(";
    public static final String postfix = ")/";

    // Private JS object to store regexok
    private static JavaScriptObject joRegexp;


    protected DateMS_DateTimeFormat()
    {
        super(DateMS);
        createJoRegExp();
    }


    @Override
    public String format(Date date)
    {
        return prefix + date.getTime() + postfix;
    }


    @Override
    public String format(Date date, TimeZone timeZone)
    {
        throw new UnsupportedOperationException("Method not implemented");
    }


    @Override
    public String getPattern()
    {
        // Return 'pattern'
        return DateMS;
    }


    @Override
    public Date parse(String text) throws IllegalArgumentException
    {
        // Test if match expressiom, convert !
        return matches(text) ? new Date(Long.parseLong(text.substring(prefix.length(), text.lastIndexOf(')')))) : null;
    }


    @Override
    public int parse(String text, int start, Date date)
    {
        throw new UnsupportedOperationException("Method not implemented");
    }


    @Override
    public Date parseStrict(String text) throws IllegalArgumentException
    {
        throw new UnsupportedOperationException("Method not implemented");
    }


    @Override
    public int parseStrict(String text, int start, Date date)
    {
        throw new UnsupportedOperationException("Method not implemented");
    }


    /**
     * Test a string against regular expression (make sure it is created before)
     * 
     * @param text
     *            Text to test
     * @return True if text matches regular expression
     */
    public static boolean matches(String text)
    {
        // Create if first time
        if (joRegexp == null)
        {
            createJoRegExp();
        }
        return testJoRegExp(text);
    }


    /**
     * PRIVATE NATIVE METHOD Create a regular expression, a RegExp Javascript
     * object and store to use later if not created previously
     */
    private static native void createJoRegExp() /*-{
		if (@name.pehl.piriti.converter.client.DateMS_DateTimeFormat::joRegexp == null) {
			@name.pehl.piriti.converter.client.DateMS_DateTimeFormat::joRegexp = /\/Date\((\d+)\)\//i;
		}
    }-*/;


    /**
     * PRIVATE NATIVE METHOD Test a string against regular expression ( make
     * sure it is created before )
     * 
     * @param text
     *            Text to test
     * @return True if text matches regular expression
     */
    private static native boolean testJoRegExp(String text) /*-{
		return @name.pehl.piriti.converter.client.DateMS_DateTimeFormat::joRegexp
				.test(text);
    }-*/;
}
