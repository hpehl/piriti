package name.pehl.piriti.rebind;

import java.util.Comparator;

import name.pehl.piriti.commons.client.WhitespaceHandling;
import name.pehl.piriti.converter.client.Converter;
import name.pehl.piriti.converter.client.NoopConverter;
import name.pehl.piriti.property.client.NoopPropertyGetter;
import name.pehl.piriti.property.client.NoopPropertySetter;
import name.pehl.piriti.property.client.PropertyGetter;
import name.pehl.piriti.property.client.PropertySetter;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;

import com.google.gwt.core.ext.TreeLogger;
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
public class PropertyContext extends LogFacade
{
    // -------------------------------------------------------- private members

    private final int order;
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
    private final boolean natural;
    private VariableNames variableNames;


    // ----------------------------------------------------------- constructors

    /**
     * Construct a new instance of this class
     * 
     * @param order
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
     * @throws UnableToCompleteException
     */
    public PropertyContext(int order, TypeContext typeContext, JType type, String name, String path, String format,
            WhitespaceHandling whitespaceHandling, Class<? extends Converter<?>> converter,
            Class<? extends PropertyGetter<?, ?>> getter, Class<? extends PropertySetter<?, ?>> setter,
            ReferenceType referenceType, boolean natural, TreeLogger logger) throws UnableToCompleteException
    {
        super(logger);

        this.order = order;
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
        if (path == null || path.length() == 0)
        {
            this.path = null;
        }
        else
        {
            this.path = path;
        }
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

        // Native field
        this.natural = natural;
    }


    // -------------------------------------------------- create nested context

    public PropertyContext createNested(JType type, String path) throws UnableToCompleteException
    {
        String nestedValueVariable = getVariableNames().newVariableName("NestedValue");
        String nestedInputVariable = getVariableNames().newVariableName("NestedInput");
        VariableNames nestedVariableNames = new VariableNames(nestedValueVariable, getVariableNames().getInputType(),
                nestedInputVariable, getVariableNames().getBuilderVariable());

        TypeContext nestedTypeContext = getTypeContext().clone(nestedVariableNames);

        PropertyContext nested = new PropertyContext(TypeContext.nextOrder(), nestedTypeContext, type, getName(), path,
                getFormat(), getWhitespaceHandling(), getConverter(), null, null, null, false, logger);
        nested.setVariableNames(nestedVariableNames);
        return nested;
    }


    // --------------------------------------------------------- object methods

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (type == null ? 0 : type.hashCode());
        return result;
    }


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
        if (type == null)
        {
            if (other.type != null)
            {
                return false;
            }
        }
        else if (!type.equals(other.type))
        {
            return false;
        }
        return true;
    }


    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder().append("PropertyContext [")
                .append(type.getParameterizedQualifiedSourceName()).append(" ").append(name);
        if (path != null)
        {
            builder.append(", path=\"").append(path).append("\"");
        }
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


    public String getPathOrName()
    {
        return path != null ? path : name;
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


    public boolean isNatural()
    {
        return natural;
    }


    public VariableNames getVariableNames()
    {
        return variableNames;
    }


    void setVariableNames(VariableNames variableNames)
    {
        this.variableNames = variableNames;
    }

    // ---------------------------------------------------------- inner classes

    public static class PropertyContextOrder implements Comparator<PropertyContext>
    {
        @Override
        public int compare(PropertyContext context1, PropertyContext context2)
        {
            return context1.order - context2.order;
        }
    }
}
