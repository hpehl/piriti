package name.pehl.piriti.converter.client;

import com.google.gwt.i18n.client.NumberFormat;

/**
 * Converter for long objects. Uses {@code Long.valueOf(value)} if no format is
 * specified and {@link NumberFormat#parse(String)} otherwise.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 7 $
 */
public class LongConverter extends NumberConverter<Long>
{
    @Override
    protected Long convertWithoutFormat(String value)
    {
        return Long.valueOf(value);
    }


    @Override
    protected Long newInstance(double parsed)
    {
        return new Long((long) parsed);
    }


    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return new LongConverter();
    }
}
