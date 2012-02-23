package name.pehl.piriti.rebind.property;

import static name.pehl.piriti.rebind.property.PropertyAccess.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

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

    private static final char[] JSON_PATH_SYMBOLS = new char[] {'$', '@', '.', '[', ']', '*', '#', ',', ':', '?', '(',
            ')',};

    private static final char[] XPATH_SYMBOLS = new char[] {'.', '[', ']', '@', '(', ')', '/',};

    // -------------------------------------------------------- private members

    private final int order;

    /**
     * The property type itself. This is in any case not a primitive type. In
     * case the original type was primitive this is the boxed counterpart.
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
    private final List<JClassType> concreteTypes;

    /**
     * The name of the property
     */
    private final String name;

    /**
     * The path information for the mapping
     */
    private String path;

    /**
     * The classname of the default or custom converter or null if unsupported /
     * undefined.
     */
    private String converter;

    /**
     * An optional format for the default or custom converter.
     */
    private String format;

    /**
     * Whether to strip whitespace and newlines from the input
     */
    private WhitespaceHandling whitespaceHandling;

    /**
     * Whether to read the property nativly
     */
    private final boolean native_;

    /**
     * The classname of a custom instance creator or null if undefined.
     */
    private String instanceCreator;

    /**
     * The classname of a custom property getter or null if undefined.
     */
    private String getter;

    /**
     * The classname of a custom property setter or null if undefined.
     */
    private String setter;

    /**
     * Information about the accessibility of the property. The key is the
     * access type the value the name of the field, setter or getter
     */
    private final Map<PropertyAccess, String> access;

    /**
     * Whether this property is used as an ID as a normal property or as a
     * reference to an ID.
     */
    private final ReferenceType referenceType;

    private final Variables variables;

    /**
     * Template for code generation
     */
    private Templates templates;


    // ----------------------------------------------------------- constructors

    public PropertyContext(TypeOracle typeOracle, PropertySource propertySource, Map<PropertyAccess, String> access,
            ReferenceType referenceType)
    {
        this.order = propertySource.getOrder();

        // types
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

        // format
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

        // access and reference
        this.access = access;
        this.referenceType = referenceType;

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
     * Return <code>true</code> if the path contains {@link #PATH_SYMBOLS},
     * <code>false</code> otherwise.
     * 
     * @param path
     * @return <code>true</code> if the path contains {@link #PATH_SYMBOLS} ,
     *         <code>false</code> otherwise.
     */
    public boolean isXpath()
    {
        return StringUtils.containsAny(path, XPATH_SYMBOLS);
    }


    public String getConverter()
    {
        return converter;
    }


    void setConverter(String converter)
    {
        this.converter = converter;
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


    public String getInstanceCreator()
    {
        return instanceCreator;
    }


    void setInstanceCreator(String instanceCreator)
    {
        this.instanceCreator = instanceCreator;
    }


    public String getGetter()
    {
        return getter;
    }


    void setGetter(String getter)
    {
        this.getter = getter;
    }


    public String getSetter()
    {
        return setter;
    }


    void setSetter(String setter)
    {
        this.setter = setter;
    }


    public Map<PropertyAccess, String> getAccess()
    {
        return access;
    }


    public boolean isAccessibleField()
    {
        return access.containsKey(FIELD);
    }


    public boolean isCallableGetter()
    {
        return access.containsKey(GETTER);
    }


    public String getCallableGetterName()
    {
        if (isCallableGetter())
        {
            return access.get(GETTER);
        }
        return null;
    }


    public boolean isCallableSetter()
    {
        return access.containsKey(SETTER);
    }


    public String getCallableSetterName()
    {
        if (isCallableSetter())
        {
            return access.get(SETTER);
        }
        return null;
    }


    public ReferenceType getReferenceType()
    {
        return referenceType;
    }


    public Variables getVariables()
    {
        return variables;
    }


    public Templates getTemplates()
    {
        return templates;
    }


    void setTemplates(Templates templates)
    {
        this.templates = templates;
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
