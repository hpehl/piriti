package name.pehl.piriti.rebind.fieldhandler;

import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * Abstract {@link FieldHandler} implementation for types with an own reader.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
 */
public abstract class AbstractRegistryFieldHandler extends AbstractFieldHandler
{
    /**
     * Returns <code>true</code> if the field type is a class or interface and
     * if there's a public static field of type {@link #getReaderClassname()} in
     * the field type, <code>false</code> otherwise.
     * 
     * @param writer
     * @param fieldContext
     * @return
     * @see name.pehl.piriti.rebind.fieldhandler.AbstractFieldHandler#isValid(name.pehl.piriti.rebind.fieldhandler.FieldContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        if (!fieldContext.isClassOrInterface())
        {
            CodeGeneration.skipField(writer, fieldContext, "Type is no class or interface");
            return false;
        }
        CodeGeneration.writeReaderInitialization(writer, fieldContext.getClassOrInterfaceType());
        return true;
    }


    protected abstract String getReaderClassname();
}
