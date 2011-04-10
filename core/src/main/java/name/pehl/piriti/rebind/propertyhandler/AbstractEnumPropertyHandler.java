package name.pehl.piriti.rebind.propertyhandler;

import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * Abstract {@link PropertyHandler} for enum types.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
 */
public abstract class AbstractEnumPropertyHandler extends AbstractPropertyHandler
{
    public AbstractEnumPropertyHandler(TreeLogger logger)
    {
        super(logger);
    }


    /**
     * Returns <code>true</code> if the field type is an enum,
     * <code>false</code> otherwise.
     * 
     * @param writer
     * @param propertyContext
     * @return
     * @see name.pehl.piriti.rebind.propertyhandler.AbstractPropertyHandler#isValid(name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        if (!propertyContext.isEnum())
        {
            CodeGeneration.skipProperty(writer, propertyContext, "Type is no enum");
            return false;
        }
        return true;
    }
}
