package name.pehl.piriti.rebind.property;

import static name.pehl.piriti.rebind.property.PropertyAccess.*;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import name.pehl.piriti.commons.client.NoopInstanceCreator;
import name.pehl.piriti.converter.client.Converter;
import name.pehl.piriti.converter.client.NoopConverter;
import name.pehl.piriti.property.client.NoopPropertyGetter;
import name.pehl.piriti.property.client.NoopPropertySetter;
import name.pehl.piriti.rebind.Modifier;
import name.pehl.piriti.rebind.type.TypeContext;
import name.pehl.piriti.rebind.type.TypeUtils;

import org.apache.commons.lang.StringUtils;

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
    private final DefaultConverterLookup defaultConverterLookup;
    private final PropertyTemplatesLookup propertyTemplatesLookup;


    // ----------------------------------------------------------- constructors

    @Inject
    public PropertyContextCreator(TypeOracle typeOracle, PropertyTemplatesLookup propertyTemplatesLookup,
            DefaultConverterLookup defaultConverterLookup)
    {
        this.typeOracle = typeOracle;
        this.propertyTemplatesLookup = propertyTemplatesLookup;
        this.defaultConverterLookup = defaultConverterLookup;
    }


    // --------------------------------------------------------- public methods

    public PropertyContext createPropertyContext(TypeContext typeContext, PropertySource propertySource,
            ReferenceType referenceType) throws InvalidPropertyException
    {
        // gather all necessary information
        Map<PropertyAccess, String> access = calculateAccess(typeContext, propertySource);
        String converter = getCustomConverter(typeContext, propertySource);
        if (converter == null)
        {
            converter = defaultConverterLookup.get(propertySource.getType());
        }
        String instanceCreator = getType(typeContext, propertySource, propertySource.getInstanceCreator(),
                NoopInstanceCreator.class);
        String getter = getType(typeContext, propertySource, propertySource.getGetter(), NoopPropertyGetter.class);
        String setter = getType(typeContext, propertySource, propertySource.getSetter(), NoopPropertySetter.class);
        Templates templates = propertyTemplatesLookup.get(typeContext, propertySource.getType(), referenceType);

        // creation
        PropertyContext propertyContext = new PropertyContext(typeOracle, propertySource, access, referenceType);
        propertyContext.setConverter(converter);
        propertyContext.setInstanceCreator(instanceCreator);
        propertyContext.setGetter(getter);
        propertyContext.setSetter(setter);
        propertyContext.setTemplates(templates);
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


    private String getCustomConverter(TypeContext typeContext, PropertySource propertySource)
            throws InvalidPropertyException
    {
        JClassType converterType = null;
        String format = propertySource.getFormat();
        Class<? extends Converter<?>> converterClass = propertySource.getConverter();

        if (converterClass != null && converterClass != NoopConverter.class)
        {
            // if there's a format specified, the converter must provide a
            // constructor which accepts a string, otherwise there must be a
            // default constructor.
            converterType = typeOracle.findType(converterClass.getName());
            if (converterType == null)
            {
                throw new InvalidPropertyException(typeContext, propertySource.getType(), propertySource.getName(),
                        "Converter " + converterClass.getName() + " cannot be found");
            }
            if (StringUtils.isEmpty(format))
            {
                if (!TypeUtils.isDefaultInstantiable(converterType))
                {
                    throw new InvalidPropertyException(typeContext, propertySource.getType(), propertySource.getName(),
                            "Converter " + converterClass.getName() + " has no default constructor");
                }
            }
            else
            {
                JType stringType = typeOracle.findType(String.class.getName());
                JConstructor constructor = converterType.findConstructor(new JType[] {stringType});
                if (constructor == null)
                {
                    throw new InvalidPropertyException(typeContext, propertySource.getType(), propertySource.getName(),
                            "Converter " + converterClass.getName() + " has no constructor which accepts the format \""
                                    + format + "\"");
                }
            }
        }
        if (converterType != null)
        {
            return converterType.getQualifiedSourceName();
        }
        return null;
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
                throw new InvalidPropertyException(typeContext, propertySource.getType(), propertySource.getName(),
                        clazz.getName() + " cannot be found");
            }
            if (!TypeUtils.isDefaultInstantiable(type))
            {
                throw new InvalidPropertyException(typeContext, propertySource.getType(), propertySource.getName(),
                        clazz.getName() + " has no default constructor");
            }
        }
        if (type != null)
        {
            return type.getQualifiedSourceName();
        }
        return null;
    }
}
