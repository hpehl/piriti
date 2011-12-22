package name.pehl.piriti.rebind.property;

import name.pehl.piriti.rebind.type.TypeContext;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
@SuppressWarnings("serial")
public class InvalidPropertyException extends Exception
{
    public InvalidPropertyException(TypeContext typeContext, PropertySource propertySource, String message)
    {
        super(String.format("Invalid property %s %s in %s: %s", propertySource.getType()
                .getParameterizedQualifiedSourceName(), propertySource.getName(), typeContext.getType()
                .getParameterizedQualifiedSourceName(), message));
    }
}
