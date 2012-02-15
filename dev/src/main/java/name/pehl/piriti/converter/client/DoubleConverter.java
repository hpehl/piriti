package name.pehl.piriti.converter.client;

import com.google.gwt.i18n.client.NumberFormat;

/**
 * Converter for double objects. Uses {@code Double.valueOf(value)} if no format
 * is specified and {@link NumberFormat#parse(String)} otherwise.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 7 $
 */
public class DoubleConverter extends NumberConverter<Double>
{
    @Override
    protected Double convertWithoutFormat(String value)
    {
        return Double.valueOf(value);
    }


    @Override
    protected Double newInstance(double parsed)
    {
        return new Double(parsed);
    }
}
