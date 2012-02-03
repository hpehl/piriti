package name.pehl.piriti.rebind.property;

import static name.pehl.piriti.rebind.property.PropertyAccess.*;
import static name.pehl.piriti.rebind.property.ReferenceType.ID;
import static name.pehl.piriti.rebind.property.ReferenceType.REFERENCE;

import java.util.Map;

import name.pehl.piriti.rebind.type.TypeContext;
import name.pehl.piriti.rebind.type.TypeUtils;

import org.apache.commons.lang.StringUtils;

import com.google.gwt.core.ext.typeinfo.JArrayType;
import com.google.gwt.core.ext.typeinfo.JType;

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

        if (TypeUtils.isMap(propertyType))
        {
            throw new InvalidPropertyException(typeContext, propertyContext.getType(), propertyContext.getName(),
                    "Maps are not supported");
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
            if (typeContext.isJson()
                    && StringUtils.containsAny(propertyContext.getPath(), PropertyContext.JSON_PATH_SYMBOLS))
            {
                throw new InvalidPropertyException(typeContext, propertyContext.getType(), propertyContext.getName(),
                        "JSONPath expressions are not supported for writing");

            }
            if (typeContext.isXml()
                    && StringUtils.containsAny(propertyContext.getPath(), PropertyContext.XML_PATH_SYMBOLS))
            {
                throw new InvalidPropertyException(typeContext, propertyContext.getType(), propertyContext.getName(),
                        "XPath expressions are not supported for writing");

            }
        }
    }


    private void validateAccess(TypeContext typeContext, PropertyContext propertyContext)
            throws InvalidPropertyException
    {
        Map<PropertyAccess, String> access = propertyContext.getAccess();
        if (typeContext.isReader())
        {
            if (!(access.containsKey(FIELD) || access.containsKey(GETTER)))
            {
                throw new InvalidPropertyException(typeContext, propertyContext.getType(), propertyContext.getName(),
                        "No accessible field or getter");
            }
        }
        else if (typeContext.isWriter())
        {
            if (!(access.containsKey(FIELD) || access.containsKey(SETTER)))
            {
                throw new InvalidPropertyException(typeContext, propertyContext.getType(), propertyContext.getName(),
                        "No accessible field or setter");
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
