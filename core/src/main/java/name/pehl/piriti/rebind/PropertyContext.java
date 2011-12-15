package name.pehl.piriti.rebind;

import java.util.Comparator;
import java.util.List;

import name.pehl.piriti.commons.client.InstanceCreator;
import name.pehl.piriti.commons.client.NoopInstanceCreator;
import name.pehl.piriti.converter.client.Converter;
import name.pehl.piriti.converter.client.NoopConverter;
import name.pehl.piriti.property.client.NoopPropertyGetter;
import name.pehl.piriti.property.client.NoopPropertySetter;
import name.pehl.piriti.property.client.PropertyGetter;
import name.pehl.piriti.property.client.PropertySetter;
import name.pehl.totoe.commons.client.WhitespaceHandling;

import org.apache.commons.lang.StringUtils;

import com.google.gwt.core.ext.typeinfo.JArrayType;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JEnumType;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JPrimitiveType;
import com.google.gwt.core.ext.typeinfo.JType;

/**
 * Class which contains information needed to generate code for the evaluation,
 * conversion and assignment of one property.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
 */
public class PropertyContext
{
    // -------------------------------------------------------------- constants

    /**
     * JSONPath special characters.
     */
    static final char[] JSON_PATH_SYMBOLS = new char[] {'$', '@', '.', '[', ']', '*', '#', ',', ':', '?', '(', ')',};

    // -------------------------------------------------------- private members

    private int order;

    /**
     * The type context this property belongs to
     */
    private TypeContext typeContext;

    /**
     * The property type itself
     */
    private JType type;

    private JPrimitiveType primitiveType;

    /**
     * The name of the property
     */
    private String name;

    /**
     * The path information for the mapping
     */
    private String path;

    /**
     * A custom converter
     */
    private Class<? extends Converter<?>> converter;

    private String format;

    /**
     * Whether to strip whitespace and newlines from the input
     */
    private WhitespaceHandling whitespaceHandling;

    /**
     * A custom instance creator
     */
    private Class<? extends InstanceCreator<?, ?>> instanceCreator;

    /**
     * A custom property getter
     */
    private Class<? extends PropertyGetter<?, ?>> getter;

    /**
     * A custom property setter
     */
    private Class<? extends PropertySetter<?, ?>> setter;

    private ReferenceType referenceType;

    /**
     * Whether to read the property nativly
     */
    private boolean native_;

    private Variables variables;

    /**
     * Information about access from the generated reader / writer
     * <ul>
     * <li>Index 0: Field access
     * <li>Index 1: Callable getter
     * <li>Index 2: Callable setter
     * </ul>
     */
    private boolean[] access;
    private PropertyContext child;
    private PropertyContext parent;
    private String template;
    private List<JClassType> concreteTypes;


    // ----------------------------------------------------------- constructors

    public PropertyContext(int order, TypeContext typeContext, JType type, String name)
    {
        this.order = order;
        this.typeContext = typeContext;
        this.type = type;
        this.name = name;
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
                .append(type.getParameterizedQualifiedSourceName()).append(" ").append(name).append("]");
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
        return type.isClassOrInterface();
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


    public boolean hasPath()
    {
        return path != null;
    }


    public String getPathOrName()
    {
        return hasPath() ? path : name;
    }


    /**
     * Return <code>true</code> if the path contains {@link #JSON_PATH_SYMBOLS},
     * <code>false</code> otherwise.
     * 
     * @param path
     * @return <code>true</code> if the path contains {@link #JSON_PATH_SYMBOLS}
     *         , <code>false</code> otherwise.
     */
    public boolean isJsonPath()
    {
        return StringUtils.containsAny(path, JSON_PATH_SYMBOLS);
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


    public boolean hasConverter()
    {
        return converter != null;
    }


    public Class<? extends InstanceCreator<?, ?>> getInstanceCreator()
    {
        return instanceCreator;
    }


    public boolean hasInstanceCreator()
    {
        return instanceCreator != null;
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


    public boolean isNative()
    {
        return native_;
    }


    public Variables getVariables()
    {
        return variables;
    }


    void setVariables(Variables variableNames)
    {
        this.variables = variableNames;
    }


    public boolean isAccessibleField()
    {
        return access[0];
    }


    public boolean isCallableGetter()
    {
        return access[1];
    }


    public boolean isCallableSetter()
    {
        return access[2];
    }


    public boolean hasParent()
    {
        return parent != null;
    }


    public boolean hasChild()
    {
        return child != null;
    }


    public PropertyContext getChild()
    {
        return child;
    }


    public String getTemplate()
    {
        return template;
    }


    public void setTemplate(String template)
    {
        this.template = template;
    }


    public List<JClassType> getConcreteTypes()
    {
        return concreteTypes;
    }


    public void setConcreteTypes(List<JClassType> concreteTypes)
    {
        this.concreteTypes = concreteTypes;
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

    /**
     * Builder for {@link PropertyContext}.
     * 
     * @author upudxv4
     */
    public static class Builder
    {
        private final int order;
        private final TypeContext typeContext;
        private final JType type;
        private final String name;
        private String path;
        private Class<? extends Converter<?>> converter;
        private String format;
        private WhitespaceHandling whitespaceHandling;
        private Class<? extends InstanceCreator<?, ?>> instanceCreator;
        private Class<? extends PropertyGetter<?, ?>> getter;
        private Class<? extends PropertySetter<?, ?>> setter;
        private ReferenceType referenceType;
        private boolean native_;
        private Variables variableNames;
        private PropertyContext parent;


        public Builder(int order, TypeContext typeContext, JType type, String name)
        {
            this.order = order;
            this.typeContext = typeContext;
            this.type = type;
            this.name = name;
        }


        public Builder(PropertyContext parent, JType nestedType)
        {
            String nestedValue = parent.getVariables().newVariable("NestedValue");
            Variables nestedVariables = new Variables(nestedValue);
            TypeContext nestedTypeContext = parent.getTypeContext().clone(nestedVariables);
            this.order = TypeContext.nextOrder();
            this.typeContext = nestedTypeContext;
            this.type = nestedType;
            this.name = parent.getName();
            this.converter = parent.getConverter();
            this.format = parent.format;
            this.whitespaceHandling = parent.getWhitespaceHandling();
            this.instanceCreator = parent.getInstanceCreator();
            this.getter = parent.getGetter();
            this.setter = parent.getSetter();
            this.referenceType = parent.getReferenceType();
            this.native_ = parent.isNative();
            this.variableNames = nestedVariables;
            this.parent = parent;
        }


        public Builder path(String path)
        {
            this.path = path;
            return this;
        }


        public Builder converter(Class<? extends Converter<?>> converter)
        {
            this.converter = converter;
            return this;
        }


        public Builder format(String format)
        {
            this.format = format;
            return this;
        }


        public Builder whitespaceHandling(WhitespaceHandling whitespaceHandling)
        {
            this.whitespaceHandling = whitespaceHandling;
            return this;
        }


        public Builder instanceCreator(Class<? extends InstanceCreator<?, ?>> instanceCreator)
        {
            this.instanceCreator = instanceCreator;
            return this;
        }


        public Builder getter(Class<? extends PropertyGetter<?, ?>> getter)
        {
            this.getter = getter;
            return this;
        }


        public Builder setter(Class<? extends PropertySetter<?, ?>> setter)
        {
            this.setter = setter;
            return this;
        }


        public Builder referenceType(ReferenceType referenceType)
        {
            this.referenceType = referenceType;
            return this;
        }


        public Builder native_(boolean native_)
        {
            this.native_ = native_;
            return this;
        }


        public Builder variableNames(Variables variableNames)
        {
            this.variableNames = variableNames;
            return this;
        }


        public PropertyContext build()
        {
            PropertyContext context = new PropertyContext(order, typeContext, type, name);
            JPrimitiveType primitiveType = type.isPrimitive();
            if (primitiveType != null)
            {
                // Use the boxed type for primitives
                context.type = typeContext.getTypeOracle().findType(primitiveType.getQualifiedBoxedSourceName());
                context.primitiveType = primitiveType;
            }
            if (path == null || path.length() == 0)
            {
                context.path = null;
            }
            else
            {
                context.path = path;
            }
            context.converter = converter == NoopConverter.class ? null : converter;
            if (format == null || format.length() == 0)
            {
                context.format = null;
            }
            else
            {
                context.format = format;
            }
            context.whitespaceHandling = whitespaceHandling;
            context.instanceCreator = instanceCreator == NoopInstanceCreator.class ? null : instanceCreator;
            context.getter = getter == NoopPropertyGetter.class ? null : getter;
            context.setter = setter == NoopPropertySetter.class ? null : setter;
            context.referenceType = referenceType;
            context.native_ = native_;
            context.access = checkAccess(primitiveType != null ? primitiveType : type);
            context.variables = variableNames;
            if (parent != null)
            {
                context.parent = parent;
                context.parent.child = context;
            }
            return context;
        }


        private boolean[] checkAccess(JType type)
        {
            boolean access[] = new boolean[] {false, false, false};
            JField field = TypeUtils.findField(typeContext.getType(), name);
            if (field != null)
            {
                // Index 0: field
                JClassType enclosingType = field.getEnclosingType();
                boolean samePackage = typeContext.getRwType().getPackage() == enclosingType.getPackage();
                access[0] = !field.isFinal()
                        && (field.isPublic() || samePackage && (field.isDefaultAccess() || field.isProtected()));

                // Index 1: getter
                JMethod getter = null;
                if (samePackage)
                {
                    getter = TypeUtils.findGetter(typeContext.getType(), name, type, Modifier.DEFAULT,
                            Modifier.PROTECTED, Modifier.PUBLIC);
                }
                else
                {
                    getter = TypeUtils.findGetter(typeContext.getType(), name, type, Modifier.PUBLIC);
                }
                access[1] = getter != null;

                // Index 2: setter
                JMethod setter = null;
                if (samePackage)
                {
                    setter = TypeUtils.findSetter(typeContext.getType(), name, type, Modifier.DEFAULT,
                            Modifier.PROTECTED, Modifier.PUBLIC);
                }
                else
                {
                    setter = TypeUtils.findSetter(typeContext.getType(), name, type, Modifier.PUBLIC);
                }
                access[2] = setter != null;
            }

            return access;
        }
    }
}
