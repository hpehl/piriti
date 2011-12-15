package name.pehl.piriti.rebind.xml;

import name.pehl.piriti.rebind.VelocityCreator;
import name.pehl.piriti.xml.client.XmlWriter;

import com.google.gwt.core.ext.GeneratorContext;
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
    static final String XML_DECL = "<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?>";


    public XmlWriterCreator(GeneratorContext generatorContext, JClassType rwType, String implName, String rwClassname)
            throws UnableToCompleteException
    {
        super(generatorContext, rwType, implName, rwClassname);
    }
}
