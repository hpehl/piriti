package name.pehl.piriti.client.converter;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

/**
 * Converter for dates. Uses {@code DateTimeFormat#parse(String)} for the
 * conversion.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 7 $
 */
public class DateConverter extends AbstractConverter<Date>
{
    /**
     * The default format which is used if no format is specified in
     * {@link #convert(String, String)}
     */
    public static final String DEFAULT_FORMAT = "dd.MM.yyyy HH:mm:ss.SSS";


    /**
     * Converts the specified value to date.
     * 
     * @param value
     *            The string to be converted
     * @param format
     *            Must be a valid date format or {@code null}
     * @return {@code null} if the value is {@code null}, empty or in the wrong
     *         format, otherwise the converted date
     * @see name.pehl.piriti.client.converter.Converter#convert(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public Date convert(String value, String format)
    {
        if (isValid(value))
        {
            return parseDate(value, format == null ? DEFAULT_FORMAT : format);
        }
        return null;
    }


    /**
     * Parsing happens in an extra method so it can be overwritten in unit
     * tests.
     * 
     * @param value
     * @param format
     * @return
     */
    protected Date parseDate(String value, String format)
    {
        DateTimeFormat dtFormat = DateTimeFormat.getFormat(format);
        try
        {
            return dtFormat.parse(value);
        }
        catch (IllegalArgumentException e)
        {
            return null;
        }
    }
}
