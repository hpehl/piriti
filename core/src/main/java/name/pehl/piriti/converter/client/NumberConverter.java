package name.pehl.piriti.converter.client;

import java.util.logging.Level;

import com.google.gwt.i18n.client.NumberFormat;

public abstract class NumberConverter<T extends Number> extends FormatableConverter<T>
{
    protected final NumberFormat numberFormat;


    public NumberConverter()
    {
        this((String) null);
    }


    /**
     * @param format
     * @throws IllegalArgumentException
     *             If the format is an illegal number format
     */
    public NumberConverter(String format)
    {
        super(format);
        if (format == null)
        {
            numberFormat = null;
        }
        else
        {
            numberFormat = NumberFormat.getFormat(format);
        }
    }


    /**
     * @param numberFormat
     *            Must not be null! Otherwise a {@link NullPointerException}
     *            will be thrown.
     * @throws NullPointerException
     *             if {@code numberFormat is null}
     */
    public NumberConverter(NumberFormat numberFormat)
    {
        super(numberFormat.getPattern());
        this.numberFormat = numberFormat;
    }


    @Override
    public T convert(String value)
    {
        T result = null;
        if (isValid(value))
        {
            if (numberFormat == null)
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
            if (numberFormat == null)
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
