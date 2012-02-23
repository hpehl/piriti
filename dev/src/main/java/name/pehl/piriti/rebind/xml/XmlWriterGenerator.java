package name.pehl.piriti.rebind.xml;

import name.pehl.piriti.rebind.VelocityGenerator;
import name.pehl.piriti.xml.client.XmlWriter;

/**
 * GWT Generator which creates a {@link XmlWriterCreator}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
 */
public class XmlWriterGenerator extends VelocityGenerator
{
    @Override
    protected String getInterfaceName()
    {
        return XmlWriter.class.getCanonicalName();
    }


    @Override
    protected String getTemplateName()
    {
        return "name/pehl/piriti/rebind/xml/writer/writer.vm";
    }
}
