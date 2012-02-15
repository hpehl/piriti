package name.pehl.piriti.rebind.property;

import name.pehl.piriti.rebind.type.TypeContext;

import com.google.gwt.core.ext.typeinfo.JType;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
@SuppressWarnings("serial")
public class InvalidPropertyException extends Exception
{
    public InvalidPropertyException(TypeContext typeContext, JType type, String name, String message)
    {
        super(String.format("Invalid property %s %s in %s: %s", type.getParameterizedQualifiedSourceName(), name,
                typeContext.getType().getParameterizedQualifiedSourceName(), message));
    }
}
