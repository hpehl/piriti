package name.pehl.piriti.rebind.property;

import static name.pehl.piriti.rebind.property.PropertyAccess.*;

import java.util.HashSet;
import java.util.Set;

import name.pehl.piriti.rebind.Modifier;
import name.pehl.piriti.rebind.type.TypeContext;
import name.pehl.piriti.rebind.type.TypeUtils;

import org.apache.commons.lang.StringUtils;

import com.google.gwt.core.ext.typeinfo.JArrayType;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JType;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class PropertyContextCreator
{
    // -------------------------------------------------------- private members

    private final TemplateFinder templateFinder;


    // ----------------------------------------------------------- constructors

    public PropertyContextCreator()
    {
        this.templateFinder = new TemplateFinder();
    }


    // --------------------------------------------------------- public methods

    public PropertyContext createPropertyContext(TypeContext typeContext, PropertySource propertySource)
            throws InvalidPropertyException
    {
        Set<PropertyAccess> access = calculateAccess(typeContext, propertySource);
        validateProperty(typeContext, propertySource, access);
        PropertyContext propertyContext = new PropertyContext(propertySource, access);

        // find and assign templates
        String path = templateFinder.getPath(typeContext);
        String template = path + templateFinder.getTemplate(propertyContext.getType());
        propertyContext.setTemplate(template);
        if (propertyContext.getElementType() != null)
        {
            String elementTypeTemplate = path + "elementtype/"
                    + templateFinder.getTemplate(propertyContext.getElementType());
            propertyContext.setElementTypeTemplate(elementTypeTemplate);
        }
        return propertyContext;
    }


    // --------------------------------------------------------- helper methods

    private Set<PropertyAccess> calculateAccess(TypeContext typeContext, PropertySource propertySource)
    {
        Set<PropertyAccess> access = new HashSet<PropertyAccess>();
        JField field = TypeUtils.findField(typeContext.getType(), propertySource.getName());
        if (field != null)
        {
            JClassType enclosingType = field.getEnclosingType();
            boolean samePackage = typeContext.getRwType().getPackage() == enclosingType.getPackage();
            if (!field.isFinal()
                    && (field.isPublic() || samePackage && (field.isDefaultAccess() || field.isProtected())))
            {
                access.add(FIELD);
            }

            JMethod getter = null;
            if (samePackage)
            {
                getter = TypeUtils.findGetter(typeContext.getType(), propertySource.getName(),
                        propertySource.getType(), Modifier.DEFAULT, Modifier.PROTECTED, Modifier.PUBLIC);
            }
            else
            {
                getter = TypeUtils.findGetter(typeContext.getType(), propertySource.getName(),
                        propertySource.getType(), Modifier.PUBLIC);
            }
            if (getter != null)
            {
                access.add(GETTER);
            }

            JMethod setter = null;
            if (samePackage)
            {
                setter = TypeUtils.findSetter(typeContext.getType(), propertySource.getName(),
                        propertySource.getType(), Modifier.DEFAULT, Modifier.PROTECTED, Modifier.PUBLIC);
            }
            else
            {
                setter = TypeUtils.findSetter(typeContext.getType(), propertySource.getName(),
                        propertySource.getType(), Modifier.PUBLIC);
            }
            if (setter != null)
            {
                access.add(SETTER);
            }
        }
        return access;
    }


    private void validateProperty(TypeContext typeContext, PropertySource propertySource, Set<PropertyAccess> access)
            throws InvalidPropertyException
    {
        validatePropertyType(typeContext, propertySource);
        if (typeContext.isReader())
        {
            if (!(access.contains(FIELD) || access.contains(GETTER)))
            {
                throw new InvalidPropertyException(typeContext, propertySource, "No accessible field or getter");
            }
        }
        else if (typeContext.isWriter())
        {
            if (!(access.contains(FIELD) || access.contains(SETTER)))
            {
                throw new InvalidPropertyException(typeContext, propertySource, "No accessible field or setter");
            }
            if (typeContext.isJson()
                    && StringUtils.containsAny(propertySource.getPath(), PropertyContext.JSON_PATH_SYMBOLS))
            {
                throw new InvalidPropertyException(typeContext, propertySource,
                        "JSONPath expressions are not supported for writing");

            }
            if (typeContext.isXml()
                    && StringUtils.containsAny(propertySource.getPath(), PropertyContext.XML_PATH_SYMBOLS))
            {
                throw new InvalidPropertyException(typeContext, propertySource,
                        "XPath expressions which are not supported for writing");

            }
        }
    }


    private void validatePropertyType(TypeContext typeContext, PropertySource propertySource)
            throws InvalidPropertyException
    {
        JType propertyType = propertySource.getType();
        JArrayType arrayType = propertyType.isArray();
        if (arrayType != null)
        {
            JType elementType = arrayType.getComponentType();
            if (elementType.isArray() != null)
            {
                throw new InvalidPropertyException(typeContext, propertySource,
                        "Multi-dimensional arrays are not supported");
            }
            if (TypeUtils.isCollection(elementType) || TypeUtils.isMap(elementType))
            {
                throw new InvalidPropertyException(typeContext, propertySource,
                        "Arrays of collections / maps are not supported");
            }
        }

        if (TypeUtils.isCollection(propertyType))
        {
            JType elementType = TypeUtils.getTypeVariable(propertyType);
            if (elementType == null)
            {
                throw new InvalidPropertyException(typeContext, propertySource, "No type parameter found");
            }
            if (elementType.isArray() != null)
            {
                throw new InvalidPropertyException(typeContext, propertySource,
                        "Collections of arrays are not supported");
            }
            if (TypeUtils.isCollection(elementType) || TypeUtils.isMap(elementType))
            {
                throw new InvalidPropertyException(typeContext, propertySource,
                        "Collections of collections / maps are not supported");
            }
        }

        if (TypeUtils.isMap(propertyType))
        {
            throw new InvalidPropertyException(typeContext, propertySource, "Maps are not supported");
        }
    }
}
