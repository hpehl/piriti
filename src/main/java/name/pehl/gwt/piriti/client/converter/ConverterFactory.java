package name.pehl.gwt.piriti.client.converter;

/**
 * @author $Author:$
 * @version $Revision:$
 */
public interface ConverterFactory
{
    <T> Converter<T> get(Class<T> clazz);
}
