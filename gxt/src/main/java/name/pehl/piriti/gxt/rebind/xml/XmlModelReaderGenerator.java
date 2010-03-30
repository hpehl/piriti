package name.pehl.piriti.gxt.rebind.xml;

import name.pehl.piriti.client.xml.XmlReader;
import name.pehl.piriti.rebind.AbstractReaderCreator;
import name.pehl.piriti.rebind.AbstractReaderGenerator;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * GWT Generator for XmlReaders. The generator delegates to
 * {@link XmlModelReaderCreator} which is responsible for generating the code.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
 */
public class XmlModelReaderGenerator extends AbstractReaderGenerator
{
    @Override
    protected AbstractReaderCreator createCreator(GeneratorContext context, JClassType interfaceType, String implName,
            TreeLogger logger) throws UnableToCompleteException
    {
        return new XmlModelReaderCreator(context, interfaceType, implName, XmlReader.class.getCanonicalName(), logger);
    }
}
