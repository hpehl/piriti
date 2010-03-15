package name.pehl.piriti.rebind;

import name.pehl.piriti.rebind.fieldhandler.FieldHandler;

/**
 * Registry to find {@link FieldHandler}s based on a {@link FieldContext}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public interface FieldHandlerRegistry
{
    /**
     * Looks up a field handler based on the information provided in the field
     * context.
     * 
     * @param fieldContext
     * @return
     */
    FieldHandler findFieldHandler(FieldContext fieldContext);
}
