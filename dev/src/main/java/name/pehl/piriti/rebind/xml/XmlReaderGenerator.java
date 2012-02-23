package name.pehl.piriti.rebind.xml;

import name.pehl.piriti.rebind.VelocityGenerator;
import name.pehl.piriti.xml.client.XmlReader;

/**
 * GWT Generator which creates a {@link xmlReaderCreator}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
 */
public class XmlReaderGenerator extends VelocityGenerator
{
    @Override
    protected String getInterfaceName()
    {
        return XmlReader.class.getCanonicalName();
    }


    @Override
    protected String getTemplateName()
    {
        return "name/pehl/piriti/rebind/xml/reader/reader.vm";
    }
}
