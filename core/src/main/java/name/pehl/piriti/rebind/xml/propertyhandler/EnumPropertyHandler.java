package name.pehl.piriti.rebind.xml.propertyhandler;

import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;

import com.google.gwt.core.ext.TreeLogger;

/**
 * {@link PropertyHandler} for enum types. This implementation reads the XML
 * data as string and tries to convert it using
 * <code>enumType.valueOf(String)</code> .
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class EnumPropertyHandler extends AbstractXmlPropertyHandler
{
    public EnumPropertyHandler(TreeLogger logger)
    {
        super(logger);
    }


    @Override
    protected String defaultValue()
    {
        return "null";
    }
}
