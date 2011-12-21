package name.pehl.piriti.rebind.property;

import static name.pehl.piriti.rebind.ReferenceType.ID;
import static name.pehl.piriti.rebind.ReferenceType.IDREF;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.type.TypeUtils;

import com.google.gwt.core.ext.typeinfo.JPrimitiveType;
import com.google.gwt.core.ext.typeinfo.JType;

/**
 * Class to find {@link PropertyHandler}s based on a {@link PropertyContext} .
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class PropertyHandlerLookup
{
    /**
     * Looks up a property handler based on the information provided in the
     * property context.
     * 
     * @param propertyContext
     * @return
     */
    public PropertyHandler lookup(PropertyContext propertyContext)
    {
        PropertyHandler handler = null;
        JType type = propertyContext.getType();
        JPrimitiveType primitiveType = propertyContext.getPrimitiveType();

        if (propertyContext.getReferenceType() == ID)
        {
            handler = newIdHandler();
        }
        else if (propertyContext.getReferenceType() == IDREF)
        {
            handler = newIdRefHandler();
        }
        else if (TypeUtils.isBoolean(type) || TypeUtils.isBoolean(primitiveType))
        {
            handler = newBooleanHandler();
        }
        else if (TypeUtils.isNumeric(type) || TypeUtils.isNumeric(primitiveType))
        {
            handler = newNumericHandler();
        }
        else if (TypeUtils.isCharacter(type) || TypeUtils.isCharacter(primitiveType))
        {
            handler = newCharacterHandler();
        }
        else if (TypeUtils.isDate(type))
        {
            handler = newDateHandler();
        }
        else if (TypeUtils.isString(type))
        {
            handler = newStringHandler();
        }
        else if (type.isEnum() != null)
        {
            handler = newEnumHandler();
        }
        else if (type.isArray() != null)
        {
            handler = newArrayHandler();
        }
        else if (TypeUtils.isCollection(type))
        {
            handler = newCollectionHandler();
        }
        else
        {
            handler = newDefaultHandler();
        }
        return handler;
    }


    protected PropertyHandler newIdHandler()
    {
        return new IdPropertyHandler();
    }


    protected PropertyHandler newIdRefHandler()
    {
        return new IdRefPropertyHandler();
    }


    protected PropertyHandler newBooleanHandler()
    {
        return new BooleanPropertyHandler();
    }


    protected PropertyHandler newNumericHandler()
    {
        return new NumericPropertyHandler();
    }


    protected PropertyHandler newCharacterHandler()
    {
        return new CharacterPropertyHandler();
    }


    protected PropertyHandler newDateHandler()
    {
        return new DatePropertyHandler();
    }


    protected PropertyHandler newStringHandler()
    {
        return new StringPropertyHandler();
    }


    protected PropertyHandler newEnumHandler()
    {
        return new EnumPropertyHandler();
    }


    protected PropertyHandler newArrayHandler()
    {
        return new ArrayPropertyHandler();
    }


    protected PropertyHandler newCollectionHandler()
    {
        return new CollectionPropertyHandler();
    }


    protected PropertyHandler newDefaultHandler()
    {
        return new DefaultPropertyHandler();
    }
}
