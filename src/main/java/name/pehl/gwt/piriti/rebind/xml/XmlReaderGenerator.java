package name.pehl.gwt.piriti.rebind.xml;

import name.pehl.gwt.piriti.client.xml.XmlReader;
import name.pehl.gwt.piriti.rebind.AbstractReaderCreator;
import name.pehl.gwt.piriti.rebind.AbstractReaderGenerator;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * GWT Generator for XmlReaders. The generator delegates to
 * {@link XmlReaderCreator} which is responsible for generating the
 * implementation.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class XmlReaderGenerator extends AbstractReaderGenerator
{
    @Override
    protected AbstractReaderCreator createCreator(GeneratorContext context, JClassType interfaceType, String implName,
            TreeLogger logger) throws UnableToCompleteException
    {
        return new XmlReaderCreator(context, interfaceType, implName, XmlReader.class.getCanonicalName(), logger);
    }
}
