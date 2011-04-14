package name.pehl.piriti.rebind.json.propertyhandler;

import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;

import com.google.gwt.core.ext.TreeLogger;

/**
 * Simple {@link PropertyHandler} implementation for strings.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class StringPropertyHandler extends AbstractJsonPropertyHandler
{
    public StringPropertyHandler(TreeLogger logger)
    {
        super(logger);
    }


    /**
     * Empty implementation
     * 
     * @see name.pehl.piriti.rebind.propertyhandler.AbstractPropertyHandler#readInputDirectly(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.PropertyContext)
     */
    @Override
    protected void readInputDirectly(IndentedWriter writer, PropertyContext propertyContext)
    {
    }
}
