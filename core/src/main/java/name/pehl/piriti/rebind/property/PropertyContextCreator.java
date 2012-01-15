package name.pehl.piriti.rebind.property;

import static name.pehl.piriti.rebind.property.PropertyAccess.*;

import java.util.HashMap;
import java.util.Map;

import name.pehl.piriti.commons.client.NoopInstanceCreator;
import name.pehl.piriti.converter.client.Converter;
import name.pehl.piriti.converter.client.NoopConverter;
import name.pehl.piriti.property.client.NoopPropertyGetter;
import name.pehl.piriti.property.client.NoopPropertySetter;
import name.pehl.piriti.rebind.GeneratorContextHolder;
import name.pehl.piriti.rebind.Modifier;
import name.pehl.piriti.rebind.type.TypeContext;
import name.pehl.piriti.rebind.type.TypeUtils;

import org.apache.commons.lang.StringUtils;

import com.google.gwt.core.ext.typeinfo.JArrayType;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JConstructor;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class PropertyContextCreator
{
    // -------------------------------------------------------- private members

    private final TypeOracle typeOracle;
    private final TemplateFinder templateFinder;
    private final DefaultConverter defaultConverter;


    // ----------------------------------------------------------- constructors

    public PropertyContextCreator()
    {
        this.typeOracle = GeneratorContextHolder.get().getContext().getTypeOracle();
        this.templateFinder = new TemplateFinder();
        this.defaultConverter = new DefaultConverter();
    }


    // --------------------------------------------------------- public methods

    public PropertyContext createPropertyContext(TypeContext typeContext, PropertySource propertySource)
            throws InvalidPropertyException
    {
        // validation
        validatePropertyType(typeContext, propertySource);
        Map<PropertyAccess, String> access = calculateAccess(typeContext, propertySource);
        validatePropertyAccess(typeContext, propertySource, access);

        String converter = getCustomConverter(typeContext, propertySource);
        if (converter == null)
        {
            converter = defaultConverter.get(propertySource.getType());
            validateMandatoryConverters(typeContext, propertySource, converter);
        }

        String instanceCreator = getType(typeContext, propertySource, propertySource.getInstanceCreator(),
                NoopInstanceCreator.class);
        String getter = getType(typeContext, propertySource, propertySource.getGetter(), NoopPropertyGetter.class);
        String setter = getType(typeContext, propertySource, propertySource.getSetter(), NoopPropertySetter.class);

        // creation
        PropertyContext propertyContext = new PropertyContext(propertySource, access);
        propertyContext.setConverter(converter);
        propertyContext.setInstanceCreator(instanceCreator);
        propertyContext.setGetter(getter);
        propertyContext.setSetter(setter);

        // templates
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

    private Map<PropertyAccess, String> calculateAccess(TypeContext typeContext, PropertySource propertySource)
    {
        Map<PropertyAccess, String> access = new HashMap<PropertyAccess, String>();
        JField field = TypeUtils.findField(typeContext.getType(), propertySource.getName());
        if (field != null)
        {
            JClassType enclosingType = field.getEnclosingType();
            boolean samePackage = typeContext.getRwType().getPackage() == enclosingType.getPackage();
            if (!field.isFinal()
                    && (field.isPublic() || samePackage && (field.isDefaultAccess() || field.isProtected())))
            {
                access.put(FIELD, field.getName());
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
                access.put(GETTER, getter.getName());
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
                access.put(SETTER, setter.getName());
            }
        }
        return access;
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


    private void validatePropertyAccess(TypeContext typeContext, PropertySource propertySource,
            Map<PropertyAccess, String> access) throws InvalidPropertyException
    {
        if (typeContext.isReader())
        {
            if (!(access.containsKey(FIELD) || access.containsKey(GETTER)))
            {
                throw new InvalidPropertyException(typeContext, propertySource, "No accessible field or getter");
            }
        }
        else if (typeContext.isWriter())
        {
            if (!(access.containsKey(FIELD) || access.containsKey(SETTER)))
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


    private String getCustomConverter(TypeContext typeContext, PropertySource propertySource)
            throws InvalidPropertyException
    {
        JClassType converterType = null;
        String format = propertySource.getFormat();
        Class<? extends Converter<?>> converterClass = propertySource.getConverter();
        TypeOracle typeOracle = GeneratorContextHolder.get().getContext().getTypeOracle();

        if (converterClass != null && converterClass != NoopConverter.class)
        {
            // if there's a format specified, the converter must provide a
            // constructor which accepts a string, otherwise there must be a
            // default constructor.
            converterType = typeOracle.findType(converterClass.getName());
            if (converterType == null)
            {
                throw new InvalidPropertyException(typeContext, propertySource, "Converter " + converterClass.getName()
                        + " cannot be found");
            }
            if (StringUtils.isEmpty(format))
            {
                if (!TypeUtils.isDefaultInstantiable(converterType))
                {
                    throw new InvalidPropertyException(typeContext, propertySource, "Converter "
                            + converterClass.getName() + " has no default constructor");
                }
            }
            else
            {
                JType stringType = typeOracle.findType(String.class.getName());
                JConstructor constructor = converterType.findConstructor(new JType[] {stringType});
                if (constructor == null)
                {
                    throw new InvalidPropertyException(typeContext, propertySource, "Converter "
                            + converterClass.getName() + " has no constructor which accepts the format \"" + format
                            + "\"");
                }
            }
        }
        if (converterType != null)
        {
            return converterType.getQualifiedSourceName();
        }
        return null;
    }


    private void validateMandatoryConverters(TypeContext typeContext, PropertySource propertySource, String converter)
            throws InvalidPropertyException
    {
        if (converter == null)
        {
            JType type = propertySource.getType();
            if (TypeUtils.isBoolean(type) || TypeUtils.isCharacter(type) || TypeUtils.isDate(type)
                    || TypeUtils.isNumeric(type) || Object.class.getName().equals(type.getQualifiedSourceName()))
            {
                throw new InvalidPropertyException(typeContext, propertySource, "No converter for "
                        + type.getQualifiedSourceName() + " specified");
            }
        }
    }


    private String getType(TypeContext typeContext, PropertySource propertySource, Class<?> clazz, Class<?> defaultValue)
            throws InvalidPropertyException
    {
        JClassType type = null;
        if (clazz != null && clazz != defaultValue)
        {
            type = typeOracle.findType(clazz.getName());
            if (type == null)
            {
                throw new InvalidPropertyException(typeContext, propertySource, clazz.getName() + " cannot be found");
            }
            if (!TypeUtils.isDefaultInstantiable(type))
            {
                throw new InvalidPropertyException(typeContext, propertySource, clazz.getName()
                        + " has no default constructor");
            }
        }
        if (type != null)
        {
            return type.getQualifiedSourceName();
        }
        return null;
    }
}
