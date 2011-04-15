package name.pehl.piriti.rebind.xml.propertyhandler;

import com.google.gwt.core.ext.TreeLogger;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2011-04-14 21:00:39 +0200 (Do, 14 Apr 2011) $ $Revision: 364
 *          $
 */
public class BooleanPropertyHandler extends AbstractXmlPropertyHandler
{
    public BooleanPropertyHandler(TreeLogger logger)
    {
        super(logger);
    }


    @Override
    protected String defaultValue()
    {
        return "false";
    }
}
