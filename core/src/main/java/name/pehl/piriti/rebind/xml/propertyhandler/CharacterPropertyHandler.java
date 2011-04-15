package name.pehl.piriti.rebind.xml.propertyhandler;

import com.google.gwt.core.ext.TreeLogger;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2011-04-13 16:40:59 +0200 (Mi, 13 Apr 2011) $ $Revision: 364
 *          $
 */
public class CharacterPropertyHandler extends AbstractXmlPropertyHandler
{
    public CharacterPropertyHandler(TreeLogger logger)
    {
        super(logger);
    }


    @Override
    protected String defaultValue()
    {
        return "'\\0'";
    }
}
