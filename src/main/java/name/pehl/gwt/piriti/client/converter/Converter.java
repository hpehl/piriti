package name.pehl.gwt.piriti.client.converter;

/**
 * @author $Author:$
 * @version $Revision:$
 */
public interface Converter<T>
{
    T convert(String value, String format);
}
