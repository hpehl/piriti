package name.pehl.piriti.rebind.propertyhandler;

import java.util.HashMap;
import java.util.Map;

import name.pehl.piriti.client.converter.Converter;
import name.pehl.piriti.client.converter.NoopConverter;
import name.pehl.piriti.client.property.PropertyGetter;
import name.pehl.piriti.client.property.PropertySetter;
import name.pehl.piriti.rebind.TypeUtils;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JArrayType;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JEnumType;
import com.google.gwt.core.ext.typeinfo.JPrimitiveType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

/**
 * Class which contains information needed to generate code for the evaluation,
 * conversion and assignment of a property. An instance of this class is passed
 * to the {@link PropertyHandler}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
 */
public class PropertyContext
{
    // -------------------------------------------------------- private members

    private final TypeOracle typeOracle;
    private final PropertyHandlerRegistry handlerRegistry;
    private final JClassType readerOrWriter;
    private final JClassType clazz;
    private final JType type;
    private final JPrimitiveType primitiveType;
    private final String name;
    private final String path;
    private final String format;
    private final boolean stripWsnl;
    private final Class<? extends Converter<?>> converter;
    private final ReferenceType referenceType;
    private final Class<? extends PropertyGetter<?, ?>> getter;
    private final Class<? extends PropertySetter<?, ?>> setter;
    private final VariableNames variableNames;
    private final Map<String, Object> metadata;


    // ----------------------------------------------------------- constructors

    /**
     * Construct a new instance of this class
     * 
     * @param typeOracle
     *            The type oracle from the GWT generator API
     * @param handlerRegistry
     *            The handler registry for {@link PropertyHandler} lookup
     * @param readerOrWriter
     *            The type of the reader or writer interface
     * @param clazz
     *            The type of the class
     * @param type
     *            The property type itself
     * @param name
     *            The name of the property
     * @param path
     *            The path information for the mapping
     * @param format
     *            The format
     * @param converter
     *            A custom converter
     * @param stripWsnl
     *            Whether to strip whitespace and newlines from the input
     * @param referenceType
     *            The refereence type.
     * @param getter
     *            A custom property getter
     * @param setter
     *            A custom property setter
     * @param variableNames
     *            Contains various variable names
     * @throws UnableToCompleteException
     */
    public PropertyContext(TypeOracle typeOracle, PropertyHandlerRegistry handlerRegistry, JClassType readerOrWriter,
            JClassType clazz, JType type, String name, String path, String format, boolean stripWsnl,
            Class<? extends Converter<?>> converter, ReferenceType referenceType,
            Class<? extends PropertyGetter<?, ?>> getter, Class<? extends PropertySetter<?, ?>> setter,
            VariableNames variableNames) throws UnableToCompleteException
    {
        // Types
        this.typeOracle = typeOracle;
        this.handlerRegistry = handlerRegistry;
        this.readerOrWriter = readerOrWriter;
        this.clazz = clazz;
        JPrimitiveType primitiveType = type.isPrimitive();
        if (primitiveType != null) // isPrimitive() is not yet available!
        {
            try
            {
                // Use the boxed type for primitives
                this.type = typeOracle.getType(primitiveType.getQualifiedBoxedSourceName());
                this.primitiveType = primitiveType;
            }
            catch (NotFoundException e)
            {
                throw new UnableToCompleteException();
            }
        }
        else
        {
            this.type = type;
            this.primitiveType = null;
        }

        // Name, path, format and converter
        this.name = name;
        this.path = path;
        if (format == null || format.length() == 0)
        {
            this.format = null;
        }
        else
        {
            this.format = format;
        }
        this.stripWsnl = stripWsnl;
        this.converter = converter;

        // Reference type, property stuff, variable names and metadata
        this.referenceType = referenceType;
        this.getter = getter;
        this.setter = setter;
        this.variableNames = variableNames;
        this.metadata = new HashMap<String, Object>();
    }


    // --------------------------------------------------------- object methods

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder().append(type.getParameterizedQualifiedSourceName()).append(" ")
                .append(name).append(", path=\"").append(path).append("\"");
        if (format != null)
        {
            builder.append(", format=\"").append(format).append("\"");
        }
        return builder.toString();
    }


    // -------------------------------------- methods related to the class type

    public boolean isGxt()
    {
        JClassType[] interfaces = clazz.getImplementedInterfaces();
        if (interfaces != null && interfaces.length != 0)
        {
            for (JClassType interfaze : interfaces)
            {
                if (interfaze.getQualifiedSourceName().equals("com.extjs.gxt.ui.client.data.Model"))
                {
                    return true;
                }
            }
        }
        return false;
    }


    // --------------------------------- methods related to the properties type

    /**
     * Whether the properties type is primitive.
     * 
     * @return <code>true</code> if the properties type is primitive,
     *         <code>false</code> otherwise.
     */
    public boolean isPrimitive()
    {
        return getPrimitiveType() != null;
    }


    /**
     * The properties primitive type.
     * 
     * @return the properties primitive type.
     */
    public JPrimitiveType getPrimitiveType()
    {
        return primitiveType;
    }


    /**
     * Whether the properties type is either primitive or a
     * {@linkplain TypeUtils#isBasicType(JType) basic type}.
     * 
     * @return <code>true</code> if the properties type is either primitive or a
     *         {@linkplain TypeUtils#isBasicType(JType) basic type},
     *         <code>false</code> othewrwise.
     */
    public boolean isBasicType()
    {
        return isPrimitive() || TypeUtils.isBasicType(type);
    }


    /**
     * Whether the properties type is an enum.
     * 
     * @return <code>true</code> if the properties type is an enum,
     *         <code>false</code> othwerwise.
     */
    public boolean isEnum()
    {
        return getEnumType() != null;
    }


    /**
     * The properties enum type.
     * 
     * @return the properties enum type.
     */
    public JEnumType getEnumType()
    {
        return type.isEnum();
    }


    /**
     * Whether the properties type is a class or interface.
     * 
     * @return <code>true</code> if the properties type is a class or interface,
     *         <code>false</code> otherwise.
     */
    public boolean isClassOrInterface()
    {
        return getClassOrInterfaceType() != null;
    }


    /**
     * The properties class or interface type.
     * 
     * @return the properties class or interface type.
     */
    public JClassType getClassOrInterfaceType()
    {
        return type.isClass();
    }


    /**
     * Whether the properties type is an array.
     * 
     * @return <code>true</code> if the properties type is an array,
     *         <code>false</code> otherwise.
     */
    public boolean isArray()
    {
        return getArrayType() != null;
    }


    /**
     * The properties array type.
     * 
     * @return the properties array type.
     */
    public JArrayType getArrayType()
    {
        return type.isArray();
    }


    /**
     * Whether the properties type is a collection.
     * 
     * @return <code>true</code> if the properties type is a collection,
     *         <code>false</code> otherwise.
     */
    public boolean isCollection()
    {
        return TypeUtils.isCollection(type);
    }


    // ------------------------------------------------------------- properties

    public TypeOracle getTypeOracle()
    {
        return typeOracle;
    }


    public PropertyHandlerRegistry getHandlerRegistry()
    {
        return handlerRegistry;
    }


    public JClassType getReaderOrWriter()
    {
        return readerOrWriter;
    }


    /**
     * The type of the properties class.
     * 
     * @return the type of the properties class.
     */
    public JClassType getClazz()
    {
        return clazz;
    }


    /**
     * The properties type.
     * 
     * @return the properties type.
     */
    public JType getType()
    {
        return type;
    }


    /**
     * The name of the property.
     * 
     * @return the name of the property.
     */
    public String getName()
    {
        return name;
    }


    /**
     * The path information for the mapping.
     * 
     * @return the path information for the mapping.
     */
    public String getPath()
    {
        return path;
    }


    public String getFormat()
    {
        return format;
    }


    public boolean isStripWsnl()
    {
        return stripWsnl;
    }


    public Class<? extends Converter<?>> getConverter()
    {
        return converter;
    }


    public boolean isCustomConverter()
    {
        return converter != null && !NoopConverter.class.equals(converter);
    }


    public ReferenceType getReferenceType()
    {
        return referenceType;
    }


    public Class<? extends PropertyGetter<?, ?>> getGetter()
    {
        return getter;
    }


    public Class<? extends PropertySetter<?, ?>> getSetter()
    {
        return setter;
    }


    public VariableNames getVariableNames()
    {
        return variableNames;
    }


    // --------------------------------------------------------------- metadata

    public <T> void addMetadata(String key, T value)
    {
        metadata.put(key, value);
    }


    @SuppressWarnings("unchecked")
    public <T> T getMetadata(String key)
    {
        return (T) metadata.get(key);
    }
}
