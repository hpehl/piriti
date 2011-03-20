package name.pehl.piriti.gxt.rebind.xml;

import name.pehl.piriti.rebind.AbstractCreator;
import name.pehl.piriti.rebind.AbstractGenerator;
import name.pehl.piriti.xml.client.XmlReader;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * GWT Generator which creates a {@link XmlModelReaderCreator}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
 */
public class XmlModelReaderGenerator extends AbstractGenerator
{
    /**
     * Creates a {@link XmlModelReaderCreator}.
     * 
     * @param context
     * @param interfaceType
     * @param implName
     * @param logger
     * @return {@link XmlModelReaderCreator}
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.AbstractGenerator#createCreator(com.google.gwt.core.ext.GeneratorContext,
     *      com.google.gwt.core.ext.typeinfo.JClassType, java.lang.String,
     *      com.google.gwt.core.ext.TreeLogger)
     */
    @Override
    protected AbstractCreator createCreator(GeneratorContext context, JClassType interfaceType, String implName,
            TreeLogger logger) throws UnableToCompleteException
    {
        return new XmlModelReaderCreator(context, interfaceType, implName, XmlReader.class.getCanonicalName(), logger);
    }
}
