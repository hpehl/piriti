package name.pehl.gwt.piriti.rebind;

/**
 * Registry to find {@link FieldHandler}s based on a {@link FieldContext}.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
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
