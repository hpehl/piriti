package name.pehl.piriti.converter.client;

import com.google.gwt.i18n.client.NumberFormat;

/**
 * Converter for float objects. Uses {@code Float.valueOf(value)} if no format
 * is specified and {@link NumberFormat#parse(String)} otherwise.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 7 $
 */
public class FloatConverter extends NumberConverter<Float>
{
    @Override
    protected Float convertWithoutFormat(String value)
    {
        return Float.valueOf(value);
    }


    @Override
    protected Float newInstance(double parsed)
    {
        return new Float(parsed);
    }


    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return new FloatConverter();
    }
}
