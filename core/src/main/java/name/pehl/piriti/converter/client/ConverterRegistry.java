package name.pehl.piriti.converter.client;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Registry with built-in converter. Currently these converter are registered:
 * <ul>
 * <li>Boolean.class --> {@link BooleanConverter}
 * <li>Byte.class --> {@link ByteConverter}
 * <li>Character.class --> {@link CharacterConverter}
 * <li>java.util.Date.class --> {@link DateConverter}
 * <li>java.sql.Date.class --> {@link SqlDateConverter}
 * <li>Double.class --> {@link DoubleConverter}
 * <li>Float.class --> {@link FloatConverter}
 * <li>Integer.class --> {@link IntegerConverter}
 * <li>Long.class --> {@link LongConverter}
 * <li>Short.class --> {@link ShortConverter}
 * <li>Time.class --> {@link TimeConverter}
 * <li>Timestamp.class --> {@link TimestampConverter}
 * </ul>
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 7 $
 */
public final class ConverterRegistry
{
    private final Map<Class<?>, Converter<?>> registry;


    /**
     * Construct a new instance of this class and registers the built-in
     * converter by calling {@link #registerDefaultConverter()}.
     */
    public ConverterRegistry()
    {
        registry = new HashMap<Class<?>, Converter<?>>();
        registerDefaultConverter();
    }


    /**
     * Registers the following converter:
     * <ul>
     * <li>Boolean.class --> {@link BooleanConverter}
     * <li>Byte.class --> {@link ByteConverter}
     * <li>Character.class --> {@link CharacterConverter}
     * <li>java.util.Date.class --> {@link DateConverter}
     * <li>java.sql.Date.class --> {@link SqlDateConverter}
     * <li>Double.class --> {@link DoubleConverter}
     * <li>Float.class --> {@link FloatConverter}
     * <li>Integer.class --> {@link IntegerConverter}
     * <li>Long.class --> {@link LongConverter}
     * <li>Short.class --> {@link ShortConverter}
     * <li>Time.class --> {@link TimeConverter}
     * <li>Timestamp.class --> {@link TimestampConverter}
     * </ul>
     */
    private void registerDefaultConverter()
    {
        register(Boolean.class, new BooleanConverter());
        register(Byte.class, new ByteConverter());
        register(Character.class, new CharacterConverter());
        register(Date.class, new DateConverter());
        register(java.sql.Date.class, new SqlDateConverter());
        register(Double.class, new DoubleConverter());
        register(Float.class, new FloatConverter());
        register(Integer.class, new IntegerConverter());
        register(Long.class, new LongConverter());
        register(Short.class, new ShortConverter());
        register(Time.class, new TimeConverter());
        register(Timestamp.class, new TimestampConverter());
    }


    /**
     * If a converter for the specified class is already registered it is
     * overwritten with the converter.
     */
    public <T> void register(Class<T> clazz, Converter<T> converter)
    {
        registry.put(clazz, converter);
    }


    @SuppressWarnings("unchecked")
    public <T> Converter<T> get(Class<T> clazz)
    {
        return (Converter<T>) registry.get(clazz);
    }
}
