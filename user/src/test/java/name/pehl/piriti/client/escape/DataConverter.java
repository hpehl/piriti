package name.pehl.piriti.client.escape;

import name.pehl.piriti.converter.client.AbstractConverter;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class DataConverter extends AbstractConverter<String>
{
    @Override
    public String convert(String value)
    {
        return value;
    }


    @Override
    public String serialize(String value)
    {
        return value;
    }
}
