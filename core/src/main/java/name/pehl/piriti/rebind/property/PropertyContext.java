package name.pehl.piriti.rebind.property;

import static name.pehl.piriti.rebind.property.PropertyAccess.FIELD;
import static name.pehl.piriti.rebind.property.PropertyAccess.GETTER;
import static name.pehl.piriti.rebind.property.PropertyAccess.SETTER;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import name.pehl.piriti.commons.client.InstanceCreator;
import name.pehl.piriti.commons.client.NoopInstanceCreator;
import name.pehl.piriti.converter.client.Converter;
import name.pehl.piriti.converter.client.NoopConverter;
import name.pehl.piriti.property.client.NoopPropertyGetter;
import name.pehl.piriti.property.client.NoopPropertySetter;
import name.pehl.piriti.property.client.PropertyGetter;
import name.pehl.piriti.property.client.PropertySetter;
import name.pehl.piriti.rebind.GeneratorContextHolder;
import name.pehl.piriti.rebind.type.TypeUtils;
import name.pehl.totoe.commons.client.WhitespaceHandling;

import org.apache.commons.lang.StringUtils;

import com.google.gwt.core.ext.typeinfo.JArrayType;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JPrimitiveType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

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

    public static final char[] JSON_PATH_SYMBOLS = new char[] {'$', '@', '.', '[', ']', '*', '#', ',', ':', '?', '(',
            ')',};

    public static final char[] XML_PATH_SYMBOLS = new char[] {'.', '[', ']', '/', '@',};

    // -------------------------------------------------------- private members

    private int order;

    /**
     * The property type itself. This is in any case not a primitive type. In
     * case the original type was primitive this is the boxed counterpart and
     * primitiveType holds the original primitive type.
     */
    private JType type;

    /**
     * In case the type is an array or collection this member holds the element
     * type, null otherwise.
     */
    private JType elementType;

    /**
     * If the type is an abstract class or an interface this list contains all
     * its concrete subtypes. Otherwise the list contains just the type itself.
     */
    private List<JClassType> concreteTypes;
    /**
     * The name of the property
     */
    private String name;

    /**
     * The path information for the mapping
     */
    private String path;

    /**
     * A custom converter or null if undefined.
     */
    private Class<? extends Converter<?>> converter;

    private String format;

    /**
     * Whether to strip whitespace and newlines from the input
     */
    private WhitespaceHandling whitespaceHandling;

    /**
     * Whether to read the property nativly
     */
    private boolean native_;

    /**
     * A custom instance creator or null if undefined.
     */
    private Class<? extends InstanceCreator<?, ?>> instanceCreator;

    /**
     * A custom property getter or null if undefined.
     */
    private Class<? extends PropertyGetter<?, ?>> getter;

    /**
     * A custom property setter or null if undefined.
     */
    private Class<? extends PropertySetter<?, ?>> setter;

    /**
     * Information about the accessibility of the property
     */
    private Set<PropertyAccess> access;

    private Variables variables;

    /**
     * Template for code generation
     */
    private String template;

    /**
     * Template for the element type
     */
    private String elementTypeTemplate;


    // ----------------------------------------------------------- constructors

    public PropertyContext(PropertySource propertySource, Set<PropertyAccess> access)
    {
        this.order = propertySource.getOrder();

        // Types
        TypeOracle typeOracle = GeneratorContextHolder.get().getContext().getTypeOracle();
        JPrimitiveType primitiveType = propertySource.getType().isPrimitive();
        if (primitiveType != null)
        {
            this.type = typeOracle.findType(primitiveType.getQualifiedBoxedSourceName());
        }
        else
        {
            this.type = propertySource.getType();
        }
        this.concreteTypes = new ArrayList<JClassType>();
        JArrayType arrayType = this.type.isArray();
        if (arrayType != null)
        {
            this.elementType = arrayType.getComponentType();
            TypeUtils.collectConcreteTypes(concreteTypes, this.elementType);
        }
        else if (TypeUtils.isCollection(this.type))
        {
            this.elementType = TypeUtils.getTypeVariable(this.type);
            TypeUtils.collectConcreteTypes(concreteTypes, this.elementType);
        }
        else
        {
            TypeUtils.collectConcreteTypes(concreteTypes, this.type);
        }

        // name and path
        this.name = propertySource.getName();
        if (StringUtils.isNotEmpty(propertySource.getPath()))
        {
            this.path = propertySource.getPath();
        }

        // converter and format
        if (propertySource.getConverter() != null && propertySource.getConverter() != NoopConverter.class)
        {
            this.converter = propertySource.getConverter();
        }
        if (StringUtils.isNotEmpty(propertySource.getFormat()))
        {
            this.format = propertySource.getFormat();
        }
        if (propertySource.getWhitespaceHandling() == null)
        {
            this.whitespaceHandling = WhitespaceHandling.REMOVE;
        }
        else
        {
            this.whitespaceHandling = propertySource.getWhitespaceHandling();
        }
        this.native_ = propertySource.isNative();

        // instance creator, setter and getter
        if (propertySource.getInstanceCreator() != null
                && propertySource.getInstanceCreator() != NoopInstanceCreator.class)
        {
            this.instanceCreator = propertySource.getInstanceCreator();
        }
        if (propertySource.getGetter() != null && propertySource.getGetter() != NoopPropertyGetter.class)
        {
            this.getter = propertySource.getGetter();
        }
        if (propertySource.getSetter() != null && propertySource.getSetter() != NoopPropertySetter.class)
        {
            this.setter = propertySource.getSetter();
        }
        this.access = access;

        // variables
        this.variables = new Variables();
    }


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


    // ------------------------------------------------------------- properties

    public JType getType()
    {
        return type;
    }


    public JType getElementType()
    {
        return elementType;
    }


    public List<JClassType> getConcreteTypes()
    {
        return concreteTypes;
    }


    public String getName()
    {
        return name;
    }


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


    /**
     * Return <code>true</code> if the path contains {@link #XML_PATH_SYMBOLS},
     * <code>false</code> otherwise.
     * 
     * @param path
     * @return <code>true</code> if the path contains {@link #XML_PATH_SYMBOLS}
     *         , <code>false</code> otherwise.
     */
    public boolean isXmlPath()
    {
        return StringUtils.containsAny(path, XML_PATH_SYMBOLS);
    }


    public Class<? extends Converter<?>> getConverter()
    {
        return converter;
    }


    public String getFormat()
    {
        return format;
    }


    public WhitespaceHandling getWhitespaceHandling()
    {
        return whitespaceHandling;
    }


    public boolean isNative()
    {
        return native_;
    }


    public Class<? extends InstanceCreator<?, ?>> getInstanceCreator()
    {
        return instanceCreator;
    }


    public Class<? extends PropertyGetter<?, ?>> getGetter()
    {
        return getter;
    }


    public Class<? extends PropertySetter<?, ?>> getSetter()
    {
        return setter;
    }


    public boolean isAccessibleField()
    {
        return access.contains(FIELD);
    }


    public boolean isCallableGetter()
    {
        return access.contains(GETTER);
    }


    public boolean isCallableSetter()
    {
        return access.contains(SETTER);
    }


    public Variables getVariables()
    {
        return variables;
    }


    public String getTemplate()
    {
        return template;
    }


    void setTemplate(String template)
    {
        this.template = template;
    }


    public String getElementTypeTemplate()
    {
        return elementTypeTemplate;
    }


    void setElementTypeTemplate(String elementTypeTemplate)
    {
        this.elementTypeTemplate = elementTypeTemplate;
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
