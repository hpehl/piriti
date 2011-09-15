package name.pehl.piriti.converter.client;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.i18n.shared.DateTimeFormat.PredefinedFormat;

/**
 * Abstract base converter for all kind of dates.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 7 $
 */
public abstract class AbstractDateConverter<T extends Date> extends AbstractConverter<T>
{
    public static final String ISO_8601 = "ISO_8601";
    public static final PredefinedFormat DEFAULT_FORMAT = PredefinedFormat.ISO_8601;

    protected final Logger logger;
    protected DateTimeFormat dateTimeFormat;


    public AbstractDateConverter()
    {
        this.dateTimeFormat = dateTimeFormat();
        this.logger = Logger.getLogger(getClass().getName());
    }


    @Override
    public void setFormat(String format)
    {
        super.setFormat(format);
        this.dateTimeFormat = dateTimeFormat();
    }


    /**
     * Instantiation happens in an extra method so it can be overwritten in unit
     * tests.
     * 
     * @return
     */
    protected DateTimeFormat dateTimeFormat()
    {
        DateTimeFormat dtFormat = null;
        if (format == null)
        {
            dtFormat = DateTimeFormat.getFormat(DEFAULT_FORMAT);
        }
        else if (ISO_8601.equals(format))
        {
            dtFormat = DateTimeFormat.getFormat(PredefinedFormat.ISO_8601);
        }
        else if (MsDateTimeFormat.PATTERN.equalsIgnoreCase(format))
        {
            return MsDateTimeFormat.me;
        }
        else
        {
            try
            {
                dtFormat = DateTimeFormat.getFormat(format);
            }
            catch (IllegalArgumentException e)
            {
                logger.log(Level.SEVERE, "Unknown DateTimeFormat '" + format + "': " + e.getMessage(), e);
            }
        }
        return dtFormat;
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
    public T convert(String value)
    {
        T result = null;
        if (isValid(value))
        {
            if (format == null)
            {
                result = convertWithoutFormat(value);
            }
            else
            {
                result = convertWithFormat(value);
            }
        }
        return result;
    }


    protected abstract T convertWithoutFormat(String value);


    protected T convertWithFormat(String value)
    {
        Date parsed = parseInternal(value);
        return newInstance(parsed);
    }


    protected abstract T newInstance(Date parsed);


    protected Date parseInternal(String value)
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
    public String serialize(T value)
    {
        String result = null;
        if (value != null)
        {
            if (format == null)
            {
                result = super.serialize(value);
            }
            else
            {
                result = serializeWithFormat(value);
            }
        }
        return result;
    }


    protected String serializeWithFormat(T value)
    {
        return dateTimeFormat.format(value);
    }
}
