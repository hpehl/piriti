package name.pehl.piriti.converter.client;

import static com.google.gwt.i18n.shared.DateTimeFormat.PredefinedFormat.ISO_8601;

import java.util.Date;
import java.util.logging.Level;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.i18n.shared.DateTimeFormat.PredefinedFormat;

/**
 * Converter for dates. Uses {@code DateTimeFormat#parse(String)} for the
 * conversion. If no format is given {@linkplain PredefinedFormat#ISO_8601
 * ISO_8601} is used.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 7 $
 */
public class DateConverter extends FormatableConverter<Date>
{
    public static final PredefinedFormat DEFAULT_FORMAT = ISO_8601;
    protected final DateTimeFormat dateTimeFormat;


    public DateConverter()
    {
        this(DateTimeFormat.getFormat(ISO_8601));
    }


    /**
     * @param format
     * @throws IllegalArgumentException
     *             If the format is an illegal date time format
     */
    public DateConverter(String format)
    {
        super(format);
        this.dateTimeFormat = dateTimeFormat(format);
    }


    /**
     * @param dateTimeFormat
     *            Must not be null! Otherwise a {@link NullPointerException}
     *            will be thrown.
     * @throws NullPointerException
     *             if {@code dateTimeFormat is null}
     */
    public DateConverter(DateTimeFormat dateTimeFormat)
    {
        super(dateTimeFormat.getPattern());
        this.dateTimeFormat = dateTimeFormat;
    }


    /**
     * Initialization happens in an extra method to enable GWT free unit tests
     * 
     * @param format
     * @return
     */
    protected DateTimeFormat dateTimeFormat(String format)
    {
        DateTimeFormat dateTimeFormat;
        if (format == null)
        {
            dateTimeFormat = DateTimeFormat.getFormat(DEFAULT_FORMAT);
        }
        else if (ISO_8601.equals(format))
        {
            dateTimeFormat = DateTimeFormat.getFormat(PredefinedFormat.ISO_8601);
        }
        else if (MsDateTimeFormat.PATTERN.equalsIgnoreCase(format))
        {
            dateTimeFormat = MsDateTimeFormat.me;
        }
        else
        {
            dateTimeFormat = DateTimeFormat.getFormat(format);
        }
        return dateTimeFormat;
    }


    /**
     * Converts the specified value to date.
     * 
     * @param value
     *            The string to be converted. May be <code>null</code>.
     * @return {@code null} if the value is {@code null}, empty or in the wrong
     *         format, otherwise the converted date
     * @see name.pehl.piriti.converter.client.Converter#convert(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public Date convert(String value)
    {
        Date result = null;
        if (isValid(value))
        {
            result = convertInternal(value);
        }
        return result;
    }


    protected Date convertInternal(String value)
    {
        Date date = null;
        try
        {
            date = dateTimeFormat.parse(value);
        }
        catch (IllegalArgumentException e)
        {
            logger.log(Level.SEVERE, "Cannot parse date '" + value + "': " + e.getMessage(), e);
        }
        return date;
    }


    @Override
    public String serialize(Date value)
    {
        String result = null;
        if (value != null)
        {
            result = dateTimeFormat.format(value);
        }
        return result;
    }
}
