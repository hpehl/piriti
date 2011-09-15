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
 * <li>Object.class --> {@link ObjectConverter}
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
    private final Map<ConverterKey, Converter<?>> registry;


    /**
     * Construct a new instance of this class and registers the built-in
     * converter by calling {@link #registerDefaultConverter()}.
     */
    public ConverterRegistry()
    {
        registry = new HashMap<ConverterKey, Converter<?>>();
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
     * <li>Object.class --> {@link ObjectConverter}
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
        register(Object.class, new ObjectConverter());
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
        registerInternal(new ConverterKey(clazz), converter);
    }


    /**
     * If a converter for the specified class is already registered it is
     * overwritten with the converter.
     */
    public <T> void register(Class<T> clazz, Converter<T> converter, String format)
    {
        registerInternal(new ConverterKey(clazz, format), converter);
    }


    private <T> void registerInternal(ConverterKey converterKey, Converter<T> converter)
    {
        registry.put(converterKey, converter);
    }


    public <T> Converter<T> get(Class<T> clazz)
    {
        return getInternal(new ConverterKey(clazz));
    }


    public <T> Converter<T> get(Class<T> clazz, String format)
    {
        return getInternal(new ConverterKey(clazz, format));
    }


    @SuppressWarnings("unchecked")
    private <T> Converter<T> getInternal(ConverterKey converterKey)
    {
        return (Converter<T>) registry.get(converterKey);
    }

    static final class ConverterKey
    {
        static final String NO_FORMAT = "__no_format__";
        private final String classname;
        private final String format;


        public ConverterKey(Class<?> clazz)
        {
            this(clazz, NO_FORMAT);
        }


        public ConverterKey(Class<?> clazz, String format)
        {
            if (clazz == null)
            {
                throw new IllegalArgumentException("clazz must not be null");
            }
            this.classname = clazz.toString();
            this.format = format;
        }


        /**
         * Based on <code>clazz</code> and <code>format</code>
         * 
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode()
        {
            final int prime = 31;
            int result = 1;
            result = prime * result + classname.hashCode();
            result = prime * result + ((format == null) ? 0 : format.hashCode());
            return result;
        }


        /**
         * Based on <code>clazz</code> and <code>format</code>
         * 
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(Object obj)
        {
            if (this == obj)
            {
                return true;
            }
            if (obj == null)
            {
                return false;
            }
            if (!(obj instanceof ConverterKey))
            {
                return false;
            }
            ConverterKey other = (ConverterKey) obj;
            if (!classname.equals(other.classname))
            {
                return false;
            }
            if (format == null)
            {
                if (other.format != null)
                {
                    return false;
                }
            }
            else if (!format.equals(other.format))
            {
                return false;
            }
            return true;
        }


        @Override
        public String toString()
        {
            return "ConverterKey[" + classname + ", " + format + "]";
        }


        public String getClassname()
        {
            return classname;
        }


        public String getFormat()
        {
            return format;
        }
    }
}
