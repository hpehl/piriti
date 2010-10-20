package name.pehl.piriti.rebind.propertyhandler;

import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * Abstract {@link FieldHandler} for enum types.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
 */
public abstract class AbstractEnumFieldHandler extends AbstractFieldHandler
{
    /**
     * Returns <code>true</code> if the field type is an enum,
     * <code>false</code> otherwise.
     * 
     * @param writer
     * @param fieldContext
     * @return
     * @see name.pehl.piriti.rebind.propertyhandler.AbstractFieldHandler#isValid(name.pehl.piriti.rebind.propertyhandler.FieldContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        if (!fieldContext.isEnum())
        {
            CodeGeneration.skipField(writer, fieldContext, "Type is no enum");
            return false;
        }
        return true;
    }
}
