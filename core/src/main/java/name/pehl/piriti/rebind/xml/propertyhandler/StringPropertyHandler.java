package name.pehl.piriti.rebind.xml.propertyhandler;

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
public class StringPropertyHandler extends AbstractXmlPropertyHandler
{
    public StringPropertyHandler(TreeLogger logger)
    {
        super(logger);
    }


    @Override
    protected void readInputDirectly(IndentedWriter writer, PropertyContext propertyContext)
    {
        // For String there's no Converter registered, so this method is called
        // by AbstractPropertyHandler.readInput()
        writer.write("%s = %s;", propertyContext.getVariableNames().getValueVariable(), propertyContext
                .getVariableNames().getValueAsStringVariable());
    }
}
