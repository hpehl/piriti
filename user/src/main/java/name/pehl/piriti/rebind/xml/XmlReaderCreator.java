package name.pehl.piriti.rebind.xml;

import name.pehl.piriti.rebind.VelocityCreator;
import name.pehl.piriti.xml.client.XmlReader;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * Creator for {@linkplain XmlReader}s.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class XmlReaderCreator extends VelocityCreator
{
    public XmlReaderCreator(JClassType rwType, String implName, String rwClassname) throws UnableToCompleteException
    {
        super(rwType, implName, rwClassname);
    }


    @Override
    protected String getTemplate()
    {
        return "name/pehl/piriti/rebind/xml/reader/reader.vm";
    }
}
