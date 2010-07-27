package name.pehl.piriti.rebind.xml;

import name.pehl.piriti.client.xml.XmlReader;
import name.pehl.piriti.rebind.AbstractCreator;
import name.pehl.piriti.rebind.AbstractGenerator;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * GWT Generator for XmlReaders. The generator delegates to
 * {@link XmlReaderCreator} which is responsible for generating the code.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
 */
public class XmlReaderGenerator extends AbstractGenerator
{
    @Override
    protected AbstractCreator createCreator(GeneratorContext context, JClassType interfaceType, String implName,
            TreeLogger logger) throws UnableToCompleteException
    {
        return new XmlReaderCreator(context, interfaceType, implName, XmlReader.class.getCanonicalName(), logger);
    }
}
