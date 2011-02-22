package name.pehl.piriti.client.gwttest.escape;

import name.pehl.piriti.client.converter.Converter;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class DataConverter implements Converter<String>
{
    @Override
    public String convert(String value, String format)
    {
        return value;
    }


    @Override
    public String serialize(String value, String format)
    {
        return value;
    }
}
