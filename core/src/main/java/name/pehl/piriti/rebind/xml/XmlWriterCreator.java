package name.pehl.piriti.rebind.xml;

import name.pehl.piriti.rebind.VelocityCreator;
import name.pehl.piriti.xml.client.XmlWriter;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * Creator for {@linkplain XmlWriter}s.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class XmlWriterCreator extends VelocityCreator
{
    public XmlWriterCreator(JClassType rwType, String implName, String rwClassname) throws UnableToCompleteException
    {
        super(rwType, implName, rwClassname);
    }


    @Override
    protected String getTemplate()
    {
        return "name/pehl/piriti/rebind/xml/writer/writer.vm";
    }
}
