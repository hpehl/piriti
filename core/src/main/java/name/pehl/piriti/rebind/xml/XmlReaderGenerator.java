package name.pehl.piriti.rebind.xml;

import name.pehl.piriti.rebind.VelocityCreator;
import name.pehl.piriti.rebind.VelocityGenerator;
import name.pehl.piriti.xml.client.XmlReader;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * GWT Generator which creates a {@link xmlReaderCreator}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
 */
public class XmlReaderGenerator extends VelocityGenerator
{
    @Override
    protected VelocityCreator createCreator(JClassType interfaceType, String implName) throws UnableToCompleteException
    {
        return new XmlReaderCreator(interfaceType, implName, XmlReader.class.getCanonicalName());
    }
}
