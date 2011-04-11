package name.pehl.piriti.rebind.xml;

import name.pehl.piriti.rebind.AbstractCreator;
import name.pehl.piriti.rebind.AbstractGenerator;
import name.pehl.piriti.xml.client.XmlWriter;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * GWT Generator which creates a {@link XmlWriterCreator}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
 */
public class XmlWriterGenerator extends AbstractGenerator
{
    /**
     * Creates a {@link XmlWriterCreator}.
     * 
     * @param context
     * @param interfaceType
     * @param implName
     * @param logger
     * @return {@link XmlWriterCreator}
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.AbstractGenerator#createCreator(com.google.gwt.core.ext.GeneratorContext,
     *      com.google.gwt.core.ext.typeinfo.JClassType, java.lang.String,
     *      com.google.gwt.core.ext.TreeLogger)
     */
    @Override
    protected AbstractCreator createCreator(GeneratorContext context, JClassType interfaceType, String implName,
            TreeLogger logger) throws UnableToCompleteException
    {
        return new XmlWriterCreator(context, interfaceType, implName, XmlWriter.class.getCanonicalName(), logger);
    }
}
