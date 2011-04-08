package name.pehl.piriti.rebind;

import java.util.HashMap;
import java.util.Map;

import name.pehl.piriti.commons.client.WhitespaceHandling;
import name.pehl.piriti.converter.client.Converter;
import name.pehl.piriti.converter.client.NoopConverter;
import name.pehl.piriti.property.client.NoopPropertyGetter;
import name.pehl.piriti.property.client.NoopPropertySetter;
import name.pehl.piriti.property.client.PropertyGetter;
import name.pehl.piriti.property.client.PropertySetter;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.ReferenceType;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JArrayType;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JEnumType;
import com.google.gwt.core.ext.typeinfo.JPrimitiveType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;

/**
 * Class which contains information needed to generate code for the evaluation,
 * conversion and assignment of one property. An instance of this class is
 * passed to the {@link PropertyHandler}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
 */
public class PropertyContext
{
    // -------------------------------------------------------- private members

    private final TypeContext typeContext;
    private final JType type;
    private final JPrimitiveType primitiveType;
    private final String name;
    private final String path;
    private final String format;
    private final WhitespaceHandling whitespaceHandling;
    private final Class<? extends Converter<?>> converter;
    private final Class<? extends PropertyGetter<?, ?>> getter;
    private final Class<? extends PropertySetter<?, ?>> setter;
    private final ReferenceType referenceType;
    private final VariableNames variableNames;
    private final Map<String, Object> metadata;


    // ----------------------------------------------------------- constructors

    /**
     * Construct a new instance of this class
     * 
     * @param typeContext
     *            The type context this property belongs to
     * @param type
     *            The property type itself
     * @param name
     *            The name of the property
     * @param path
     *            The path information for the mapping
     * @param format
     *            The format
     * @param whitespaceHandling
     *            Whether to strip whitespace and newlines from the input
     * @param converter
     *            A custom converter
     * @param getter
     *            A custom property getter
     * @param setter
     *            A custom property setter
     * @param referenceType
     *            The refereence type.
     * @param variableNames
     *            Contains various variable names
     * @throws UnableToCompleteException
     */
    public PropertyContext(TypeContext typeContext, JType type, String name, String path, String format,
            WhitespaceHandling whitespaceHandling, Class<? extends Converter<?>> converter,
            Class<? extends PropertyGetter<?, ?>> getter, Class<? extends PropertySetter<?, ?>> setter,
            ReferenceType referenceType, VariableNames variableNames) throws UnableToCompleteException
    {
        this.typeContext = typeContext;
        JPrimitiveType primitiveType = type.isPrimitive();
        if (primitiveType != null) // isPrimitive() is not yet available!
        {
            try
            {
                // Use the boxed type for primitives
                this.type = typeContext.getTypeOracle().getType(primitiveType.getQualifiedBoxedSourceName());
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
        // Name, path, order, format, stripWsnl and converter
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
        this.whitespaceHandling = whitespaceHandling;
        this.converter = converter == NoopConverter.class ? null : converter;

        // Reference type, property stuff, variable names and metadata
        this.getter = getter == NoopPropertyGetter.class ? null : getter;
        this.setter = setter == NoopPropertySetter.class ? null : setter;
        this.referenceType = referenceType;
        this.variableNames = variableNames;
        this.metadata = new HashMap<String, Object>();
    }


    // -------------------------------------------------- create nested context

    public PropertyContext createNested(JType type, String path) throws UnableToCompleteException
    {
        String nestedValueVariable = getVariableNames().newVariableName("NestedValue");
        String nestedInputVariable = getVariableNames().newVariableName("NestedInput");
        VariableNames nestedVariableNames = new VariableNames(nestedValueVariable, nestedInputVariable,
                getVariableNames().getBuilderVariable());

        TypeContext nestedTypeContext = getTypeContext().clone(nestedVariableNames);

        PropertyContext nested = new PropertyContext(nestedTypeContext, type, getName(), path, getFormat(),
                getWhitespaceHandling(), getConverter(), null, null, null, nestedVariableNames);
        return nested;
    }


    // --------------------------------------------------------- object methods

    /**
     * Based on name and path
     * 
     * @return
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (path == null ? 0 : path.hashCode());
        return result;
    }


    /**
     * Based on name and path
     * 
     * @param obj
     * @return
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
        if (getClass() != obj.getClass())
        {
            return false;
        }
        PropertyContext other = (PropertyContext) obj;
        if (name == null)
        {
            if (other.name != null)
            {
                return false;
            }
        }
        else if (!name.equals(other.name))
        {
            return false;
        }
        if (path == null)
        {
            if (other.path != null)
            {
                return false;
            }
        }
        else if (!path.equals(other.path))
        {
            return false;
        }
        return true;
    }


    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder().append("PropertyContext [")
                .append(type.getParameterizedQualifiedSourceName()).append(" ").append(name).append(", path=\"")
                .append(path).append("\"");
        if (format != null)
        {
            builder.append(", format=\"").append(format).append("\"");
        }
        builder.append("]");
        return builder.toString();
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

    public TypeContext getTypeContext()
    {
        return typeContext;
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


    public WhitespaceHandling getWhitespaceHandling()
    {
        return whitespaceHandling;
    }


    public Class<? extends Converter<?>> getConverter()
    {
        return converter;
    }


    public boolean useCustomConverter()
    {
        return converter != null;
    }


    public Class<? extends PropertyGetter<?, ?>> getGetter()
    {
        return getter;
    }


    public Class<? extends PropertySetter<?, ?>> getSetter()
    {
        return setter;
    }


    public ReferenceType getReferenceType()
    {
        return referenceType;
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
