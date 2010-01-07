package name.pehl.gwt.piriti.client.converter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author $Author:$
 * @version $Revision:$
 */
public class ConverterFactoryImpl implements ConverterFactory
{
    private Map<Class<?>, Converter<?>> registry;


    public ConverterFactoryImpl()
    {
        registry = new HashMap<Class<?>, Converter<?>>();
        initDefaultConverters();
    }


    protected void initDefaultConverters()
    {
        // Primitive Objects
        registry.put(Boolean.class, new BooleanConverter());
        registry.put(Byte.class, new ByteConverter());
        registry.put(Character.class, new CharacterConverter());
        registry.put(Double.class, new DoubleConverter());
        registry.put(Float.class, new FloatConverter());
        registry.put(Integer.class, new IntegerConverter());
        registry.put(Long.class, new LongConverter());
        registry.put(Short.class, new ShortConverter());

        // Date
        registry.put(Date.class, new DateConverter());
    }


    @Override
    @SuppressWarnings("unchecked")
    public <T> Converter<T> get(Class<T> clazz)
    {
        if (clazz != null)
        {
            return (Converter<T>) registry.get(clazz);
        }
        return null;
    }
}
