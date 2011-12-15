package name.pehl.piriti.rebind.xml;

import name.pehl.piriti.rebind.VelocityCreator;
import name.pehl.piriti.xml.client.XmlReader;

import com.google.gwt.core.ext.GeneratorContext;
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
    public XmlReaderCreator(GeneratorContext generatorContext, JClassType rwType, String implName, String rwClassname)
            throws UnableToCompleteException
    {
        super(generatorContext, rwType, implName, rwClassname);
    }
}
