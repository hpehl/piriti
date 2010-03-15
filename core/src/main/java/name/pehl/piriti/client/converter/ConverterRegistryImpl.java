package name.pehl.piriti.client.converter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation with default converter. Currently these converters are
 * registered:
 * <ul>
 * <li>Boolean.class --> {@link BooleanConverter}
 * <li>Byte.class --> {@link ByteConverter}
 * <li>Character.class --> {@link CharacterConverter}
 * <li>Date.class --> {@link DateConverter}
 * <li>Double.class --> {@link DoubleConverter}
 * <li>Float.class --> {@link FloatConverter}
 * <li>Integer.class --> {@link IntegerConverter}
 * <li>Long.class --> {@link LongConverter}
 * <li>Short.class --> {@link ShortConverter}
 * </ul>
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 45 $
 */
public class ConverterRegistryImpl implements ConverterRegistry
{
    private Map<Class<?>, Converter<?>> registry;


    public ConverterRegistryImpl()
    {
        registry = new HashMap<Class<?>, Converter<?>>();
        registerDefaultConverters();
    }


    /**
     * Registers the following converters:
     * <ul>
     * <li>Boolean.class --> {@link BooleanConverter}
     * <li>Byte.class --> {@link ByteConverter}
     * <li>Character.class --> {@link CharacterConverter}
     * <li>Date.class --> {@link DateConverter}
     * <li>Double.class --> {@link DoubleConverter}
     * <li>Float.class --> {@link FloatConverter}
     * <li>Integer.class --> {@link IntegerConverter}
     * <li>Long.class --> {@link LongConverter}
     * <li>Short.class --> {@link ShortConverter}
     * </ul>
     */
    protected void registerDefaultConverters()
    {
        register(Boolean.class, new BooleanConverter());
        register(Byte.class, new ByteConverter());
        register(Character.class, new CharacterConverter());
        register(Date.class, new DateConverter());
        register(Double.class, new DoubleConverter());
        register(Float.class, new FloatConverter());
        register(Integer.class, new IntegerConverter());
        register(Long.class, new LongConverter());
        register(Short.class, new ShortConverter());
    }


    /**
     * Registers the specified converter. If the clazz is {@code null} no
     * converter is registered. If a converter for the specified class is
     * already registered it is overwritten with the ne converter.
     */
    @Override
    public <T> void register(Class<T> clazz, Converter<T> converter)
    {
        if (clazz != null)
        {
            registry.put(clazz, converter);
        }
    }


    /**
     * {@inheritDoc}
     */
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
