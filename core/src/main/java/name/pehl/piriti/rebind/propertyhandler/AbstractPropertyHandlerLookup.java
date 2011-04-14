package name.pehl.piriti.rebind.propertyhandler;

import static name.pehl.piriti.rebind.propertyhandler.ReferenceType.ID;
import static name.pehl.piriti.rebind.propertyhandler.ReferenceType.IDREF;
import name.pehl.piriti.rebind.LogFacade;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.TypeUtils;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.typeinfo.JType;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public abstract class AbstractPropertyHandlerLookup extends LogFacade implements PropertyHandlerLookup
{
    public AbstractPropertyHandlerLookup(TreeLogger logger)
    {
        super(logger);
    }


    /**
     * Returns a handler based on the given {@link PropertyContext}. The
     * following rules are implemented:
     * <ol>
     * <li>IDs: {@link #newIdHandler()}
     * <li>IDREFs: {@link #newIdRefHandler()}
     * <li>boolean, Boolean: {@link #newBooleanHandler()}
     * <li>byte, short, int, long, float, double, Number:
     * {@link #newNumericHandler()}
     * <li>char, Character: {@link #newCharacterHandler()}
     * <li>String: {@link #newStringHandler()}
     * <li>Arrays: {@link #newArrayHandler()}
     * <li>Collections: {@link #newCollectionHandler()}
     * <li>any other type: {@link #newDefaultHandler()}
     * </ol>
     * 
     * @param propertyContext
     * @return
     * @see name.pehl.piriti.rebind.propertyhandler.PropertyHandlerLookup#lookup(name.pehl.piriti.rebind.PropertyContext)
     */
    @Override
    public PropertyHandler lookup(PropertyContext propertyContext)
    {
        PropertyHandler handler = null;
        JType type = propertyContext.getType();

        if (propertyContext.getReferenceType() == ID)
        {
            handler = newIdHandler();
        }
        else if (propertyContext.getReferenceType() == IDREF)
        {
            handler = newIdRefHandler();
        }
        else if (TypeUtils.isBoolean(type))
        {
            handler = newBooleanHandler();
        }
        else if (TypeUtils.isNumeric(type))
        {
            handler = newNumericHandler();
        }
        else if (TypeUtils.isCharacter(type))
        {
            handler = newCharacterHandler();
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


    protected abstract PropertyHandler newIdHandler();


    protected abstract PropertyHandler newIdRefHandler();


    protected abstract PropertyHandler newBooleanHandler();


    protected abstract PropertyHandler newNumericHandler();


    protected abstract PropertyHandler newCharacterHandler();


    protected abstract PropertyHandler newStringHandler();


    protected abstract PropertyHandler newEnumHandler();


    protected abstract PropertyHandler newArrayHandler();


    protected abstract PropertyHandler newCollectionHandler();


    protected abstract PropertyHandler newDefaultHandler();
}
