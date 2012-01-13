package name.pehl.piriti.rebind.property;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import name.pehl.piriti.converter.client.BooleanConverter;
import name.pehl.piriti.converter.client.ByteConverter;
import name.pehl.piriti.converter.client.CharacterConverter;
import name.pehl.piriti.converter.client.DateConverter;
import name.pehl.piriti.converter.client.DoubleConverter;
import name.pehl.piriti.converter.client.FloatConverter;
import name.pehl.piriti.converter.client.IntegerConverter;
import name.pehl.piriti.converter.client.LongConverter;
import name.pehl.piriti.converter.client.ObjectConverter;
import name.pehl.piriti.converter.client.ShortConverter;
import name.pehl.piriti.converter.client.SqlDateConverter;
import name.pehl.piriti.converter.client.TimeConverter;
import name.pehl.piriti.converter.client.TimestampConverter;
import name.pehl.piriti.rebind.GeneratorContextHolder;
import name.pehl.piriti.rebind.type.TypeUtils;

import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

/**
 * Registry with built-in converter. Currently these converter are registered:
 * <ul>
 * <li>Boolean --> {@link BooleanConverter}
 * <li>Byte --> {@link ByteConverter}
 * <li>Character --> {@link CharacterConverter}
 * <li>java.util.Date --> {@link DateConverter}
 * <li>java.sql.Date --> {@link SqlDateConverter}
 * <li>Double --> {@link DoubleConverter}
 * <li>Float --> {@link FloatConverter}
 * <li>Integer --> {@link IntegerConverter}
 * <li>Long --> {@link LongConverter}
 * <li>Object --> {@link ObjectConverter}
 * <li>Short --> {@link ShortConverter}
 * <li>Time --> {@link TimeConverter}
 * <li>Timestamp --> {@link TimestampConverter}
 * </ul>
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 7 $
 */
public class DefaultConverter
{
    private final TypeOracle typeOracle;
    private final Map<JClassType, JClassType> registry;


    /**
     * Construct a new instance of this class and registers the built-in
     * converters using the {@link TypeOracle} from
     * {@link GeneratorContextHolder}.
     */
    public DefaultConverter()
    {
        typeOracle = GeneratorContextHolder.get().getContext().getTypeOracle();

        registry = new HashMap<JClassType, JClassType>();
        registry.put(typeOracle.findType(Boolean.class.getName()),
                typeOracle.findType(BooleanConverter.class.getName()));
        registry.put(typeOracle.findType(Byte.class.getName()), typeOracle.findType(ByteConverter.class.getName()));
        registry.put(typeOracle.findType(Character.class.getName()),
                typeOracle.findType(CharacterConverter.class.getName()));
        registry.put(typeOracle.findType(Date.class.getName()), typeOracle.findType(DateConverter.class.getName()));
        registry.put(typeOracle.findType(java.sql.Date.class.getName()),
                typeOracle.findType(SqlDateConverter.class.getName()));
        registry.put(typeOracle.findType(Double.class.getName()), typeOracle.findType(DoubleConverter.class.getName()));
        registry.put(typeOracle.findType(Float.class.getName()), typeOracle.findType(FloatConverter.class.getName()));
        registry.put(typeOracle.findType(Integer.class.getName()),
                typeOracle.findType(IntegerConverter.class.getName()));
        registry.put(typeOracle.findType(Long.class.getName()), typeOracle.findType(LongConverter.class.getName()));
        registry.put(typeOracle.findType(Object.class.getName()), typeOracle.findType(ObjectConverter.class.getName()));
        registry.put(typeOracle.findType(Short.class.getName()), typeOracle.findType(ShortConverter.class.getName()));
        registry.put(typeOracle.findType(Time.class.getName()), typeOracle.findType(TimeConverter.class.getName()));
        registry.put(typeOracle.findType(Timestamp.class.getName()),
                typeOracle.findType(TimestampConverter.class.getName()));
    }


    public JClassType get(JType type)
    {
        JClassType converter = null;
        if (type != null)
        {
            if (type.isArray() != null)
            {
                JType componentType = type.isArray().getComponentType();
                if (componentType.isPrimitive() != null)
                {
                    String boxedSourceName = componentType.isPrimitive().getQualifiedBoxedSourceName();
                    JClassType boxedType = typeOracle.findType(boxedSourceName);
                    converter = registry.get(boxedType);
                }
                else if (componentType.isClass() != null)
                {
                    converter = registry.get(componentType.isClass());
                }

            }
            else if (TypeUtils.isCollection(type))
            {
                JClassType typeVariable = TypeUtils.getTypeVariable(type);
                if (typeVariable != null)
                {
                    converter = registry.get(typeVariable.isClass());
                }
            }
            else
            {
                if (type.isPrimitive() != null)
                {
                    String boxedSourceName = type.isPrimitive().getQualifiedBoxedSourceName();
                    JClassType boxedType = typeOracle.findType(boxedSourceName);
                    converter = registry.get(boxedType);
                }
                else if (type.isClass() != null)
                {
                    converter = registry.get(type.isClass());
                }
            }
        }
        return converter;
    }
}
