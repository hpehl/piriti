package name.pehl.piriti.rebind.property;

import com.google.gwt.core.ext.typeinfo.JArrayType;
import com.google.gwt.core.ext.typeinfo.JType;
import name.pehl.piriti.rebind.type.TypeContext;
import name.pehl.piriti.rebind.type.TypeUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

import static name.pehl.piriti.rebind.property.PropertyAccess.*;
import static name.pehl.piriti.rebind.property.ReferenceType.ID;
import static name.pehl.piriti.rebind.property.ReferenceType.REFERENCE;

public class PropertyContextValidator
{
    public void validate(TypeContext typeContext, PropertyContext propertyContext) throws InvalidPropertyException
    {
        validateType(typeContext, propertyContext);
        validatePath(typeContext, propertyContext);
        validateAccess(typeContext, propertyContext);
        validateMandatoryConverters(typeContext, propertyContext);
        validateTemplates(typeContext, propertyContext);
    }


    private void validateType(TypeContext typeContext, PropertyContext propertyContext) throws InvalidPropertyException
    {
        JType propertyType = propertyContext.getType();
        JArrayType arrayType = propertyType.isArray();
        if (arrayType != null)
        {
            JType elementType = arrayType.getComponentType();
            if (elementType.isArray() != null)
            {
                throw new InvalidPropertyException(typeContext, propertyContext.getType(), propertyContext.getName(),
                        "Multi-dimensional arrays are not supported");
            }
            if (TypeUtils.isCollection(elementType) || TypeUtils.isMap(elementType))
            {
                throw new InvalidPropertyException(typeContext, propertyContext.getType(), propertyContext.getName(),
                        "Arrays of collections / maps are not supported");
            }
        }

        if (TypeUtils.isCollection(propertyType))
        {
            JType elementType = TypeUtils.getTypeVariable(propertyType);
            if (elementType == null)
            {
                throw new InvalidPropertyException(typeContext, propertyContext.getType(), propertyContext.getName(),
                        "No type parameter found");
            }
            if (elementType.isArray() != null)
            {
                throw new InvalidPropertyException(typeContext, propertyContext.getType(), propertyContext.getName(),
                        "Collections of arrays are not supported");
            }
            if (TypeUtils.isCollection(elementType) || TypeUtils.isMap(elementType))
            {
                throw new InvalidPropertyException(typeContext, propertyContext.getType(), propertyContext.getName(),
                        "Collections of collections / maps are not supported");
            }
        }

        // TODO Take ReferenceType into account!
        if (propertyContext.getReferenceType() == ID)
        {

        }
        else if (propertyContext.getReferenceType() == REFERENCE)
        {

        }
    }


    private void validatePath(TypeContext typeContext, PropertyContext propertyContext) throws InvalidPropertyException
    {
        if (typeContext.isWriter())
        {
            String path = propertyContext.getPath();
            if (path != null)
            {
                JType type = propertyContext.getType();
                if (typeContext.isJson() && propertyContext.isJsonPath())
                {
                    throw new InvalidPropertyException(typeContext, type, propertyContext.getName(), "The JSONPath "
                            + path + " is not supported for writing");

                }
                else if (typeContext.isXml() && propertyContext.isXpath())
                {
                    // supported XPath expressions for XmlWriters are
                    // a/b/c/.../x/y/z or
                    // a/b/c/.../x/y/@z or just @z
                    // The attribute must be the last part of the XPath
                    // expression and cannot be used for collections
                    if (StringUtils.containsAny(path, new char[] {'.', '[', ']', '(', ')',}))
                    {
                        throw new InvalidPropertyException(typeContext, type, propertyContext.getName(), "The XPath "
                                + path + " is not supported for writing");
                    }
                    if (path.contains("@"))
                    {
                        if (type.isArray() != null || TypeUtils.isCollection(type))
                        {
                            throw new InvalidPropertyException(typeContext, type, propertyContext.getName(),
                                    "The XPath " + path + " is not supported when writing arrays/collections");
                        }
                        String[] parts = path.split("@");
                        if (parts.length != 2)
                        {
                            throw new InvalidPropertyException(typeContext, type, propertyContext.getName(),
                                    "The XPath " + path + " is not supported for writing");
                        }
                        if (parts[1].contains("/"))
                        {
                            throw new InvalidPropertyException(typeContext, type, propertyContext.getName(),
                                    "The XPath " + path + " is not supported for writing");
                        }
                    }
                }
            }
        }
    }


    private void validateAccess(TypeContext typeContext, PropertyContext propertyContext)
            throws InvalidPropertyException
    {
        Map<PropertyAccess, String> access = propertyContext.getAccess();
        if (typeContext.isReader())
        {
            if (!(access.containsKey(FIELD) || access.containsKey(SETTER)) && propertyContext.getSetter() == null)
            {
                throw new InvalidPropertyException(typeContext, propertyContext.getType(), propertyContext.getName(),
                        "No accessible field or setter and no custom setter specified");
            }
        }
        else if (typeContext.isWriter())
        {
            if (!(access.containsKey(FIELD) || access.containsKey(GETTER)) && propertyContext.getGetter() == null)
            {
                throw new InvalidPropertyException(typeContext, propertyContext.getType(), propertyContext.getName(),
                        "No accessible field or getter and no custom getter specified");
            }
        }
    }


    private void validateMandatoryConverters(TypeContext typeContext, PropertyContext propertyContext)
            throws InvalidPropertyException
    {
        String converter = propertyContext.getConverter();
        if (converter == null)
        {
            JType type = propertyContext.getType();
            if (TypeUtils.isBoolean(type) || TypeUtils.isCharacter(type) || TypeUtils.isDate(type)
                    || TypeUtils.isNumeric(type) || Object.class.getName().equals(type.getQualifiedSourceName()))
            {
                throw new InvalidPropertyException(typeContext, propertyContext.getType(), propertyContext.getName(),
                        "No converter for " + type.getQualifiedSourceName() + " specified");
            }
        }
    }


    private void validateTemplates(TypeContext typeContext, PropertyContext propertyContext)
            throws InvalidPropertyException
    {
        Templates templates = propertyContext.getTemplates();
        if (templates == null || templates == Templates.NO_TEMPLATES)
        {
            throw new InvalidPropertyException(typeContext, propertyContext.getType(), propertyContext.getName(),
                    "No templates found");
        }
    }
}
