package name.pehl.piriti.converter.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.i18n.client.NumberFormat;

public abstract class NumberConverter<T extends Number> extends AbstractConverter<T>
{
    protected final Logger logger;
    protected NumberFormat numberFormat;


    public NumberConverter()
    {
        this.numberFormat = numberFormat();
        this.logger = Logger.getLogger(getClass().getName());
    }


    @Override
    public void setFormat(String format)
    {
        super.setFormat(format);
        this.numberFormat = numberFormat();
    }


    /**
     * Instantiation happens in an extra method so it can be overwritten in unit
     * tests.
     * 
     * @return
     */
    protected NumberFormat numberFormat()
    {
        NumberFormat numberFormat = null;
        if (format != null)
        {
            try
            {
                numberFormat = NumberFormat.getFormat(format);
            }
            catch (IllegalArgumentException e)
            {
                logger.log(Level.SEVERE, "Unknown NumberFormat '" + format + "': " + e.getMessage(), e);
            }
        }
        return numberFormat;
    }


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
        double parsed = parseInternal(value);
        return newInstance(parsed);
    }


    protected abstract T newInstance(double parsed);


    protected double parseInternal(String value)
    {
        double result = 0;
        try
        {
            result = numberFormat.parse(value);
        }
        catch (IllegalArgumentException e)
        {
            logger.log(Level.SEVERE, "Cannot parse number '" + value + "': " + e.getMessage(), e);
        }
        return result;
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
        return numberFormat.format(value);
    }
}
