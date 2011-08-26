package name.pehl.piriti.converter.client;

import static com.google.gwt.i18n.shared.DateTimeFormat.PredefinedFormat.ISO_8601;

import java.util.Date;

import com.google.gwt.i18n.shared.DateTimeFormat;

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
     * Converts the specified value to date.
     * 
     * @param value
     *            The string to be converted. May be <code>null</code>.
     * @param format
     *            Must be a valid date format or {@code null}
     * @return {@code null} if the value is {@code null}, empty or in the wrong
     *         format, otherwise the converted date
     * @see name.pehl.piriti.converter.client.Converter#convert(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public Date convert(String value, String format)
    {
        if (isValid(value))
        {
            return convertDate(value, format);
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
    protected Date convertDate(String value, String format)
    {
        DateTimeFormat dtFormat = dateTimeFormatFor(format);
        try
        {
            return dtFormat.parse(value);
        }
        catch (IllegalArgumentException e)
        {
            return null;
        }
    }


    @Override
    public String serialize(Date value, String format)
    {
        if (value != null)
        {
            return serializeDate(value, format);
        }
        return null;
    }


    /**
     * Serialization happens in an extra method so it can be overwritten in unit
     * tests.
     * 
     * @param value
     * @param format
     */
    protected String serializeDate(Date value, String format)
    {
        DateTimeFormat dtFormat = dateTimeFormatFor(format);
        return dtFormat.format(value);
    }


    private DateTimeFormat dateTimeFormatFor(String format)
    {
        DateTimeFormat dtFormat = null;
        if (format == null)
        {
            dtFormat = DateTimeFormat.getFormat(ISO_8601);
        }
        else if (DateMS_DateTimeFormat.DateMS.equalsIgnoreCase(format))
        {
            return DateMS_DateTimeFormat.me;
        }
        else
        {
            dtFormat = DateTimeFormat.getFormat(format);
        }
        return dtFormat;
    }
}
