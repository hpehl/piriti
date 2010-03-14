package name.pehl.gwt.piriti.rebind.fieldhandler;

import name.pehl.gwt.piriti.rebind.FieldContext;
import name.pehl.gwt.piriti.rebind.IndentedWriter;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * Abstract {@link FieldHandler} for enum types.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
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
     * @see name.pehl.gwt.piriti.rebind.fieldhandler.AbstractFieldHandler#isValid(name.pehl.gwt.piriti.rebind.FieldContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        if (!fieldContext.isEnum())
        {
            skipField(writer, fieldContext, "Type is no enum");
        }
        return true;
    }
}
