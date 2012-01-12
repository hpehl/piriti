package name.pehl.piriti.converter.client;

import com.google.gwt.i18n.client.NumberFormat;

/**
 * Converter for integer objects. Uses {@code Integer.valueOf(value)} if no
 * format is specified and {@link NumberFormat#parse(String)} otherwise.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 7 $
 */
public class IntegerConverter extends NumberConverter<Integer>
{
    @Override
    protected Integer convertWithoutFormat(String value)
    {
        return Integer.valueOf(value);
    }


    @Override
    protected Integer newInstance(double parsed)
    {
        return new Integer((int) parsed);
    }


    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return new IntegerConverter();
    }
}
