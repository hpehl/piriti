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
public class MsDateTimeFormat extends DateTimeFormat
{
    // Me and myself
    public static final MsDateTimeFormat me = new MsDateTimeFormat();

    // Use the one you want
    public static final String PATTERN = "MsDateTimeFormat()";

    // Pre and post string where formatting dates, pretty simple
    public static final String PREFIX = "/Date(";
    public static final String POSTFIX = ")/";

    // Private JS object to store regexok
    private static JavaScriptObject regexp;


    protected MsDateTimeFormat()
    {
        super(PATTERN);
        createJoRegExp();
    }


    @Override
    public String format(Date date)
    {
        return PREFIX + date.getTime() + POSTFIX;
    }


    @Override
    public String format(Date date, TimeZone timeZone)
    {
        throw new UnsupportedOperationException("Method not implemented");
    }


    @Override
    public String getPattern()
    {
        return PATTERN;
    }


    @Override
    public Date parse(String text) throws IllegalArgumentException
    {
        // Test if match expressiom, convert !
        return matches(text) ? new Date(Long.parseLong(text.substring(PREFIX.length(), text.lastIndexOf(')')))) : null;
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
        if (regexp == null)
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
		if (@name.pehl.piriti.converter.client.MsDateTimeFormat::regexp == null) {
			@name.pehl.piriti.converter.client.MsDateTimeFormat::regexp = /\/Date\((\d+)\)\//i;
		}
    }-*/;


    /**
     * PRIVATE NATIVE METHOD Test a string against regular expression (make sure
     * it is created before)
     * 
     * @param text
     *            Text to test
     * @return True if text matches regular expression
     */
    private static native boolean testJoRegExp(String text) /*-{
		return @name.pehl.piriti.converter.client.MsDateTimeFormat::regexp
				.test(text);
    }-*/;
}
