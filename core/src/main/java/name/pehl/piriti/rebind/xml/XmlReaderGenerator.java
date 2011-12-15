package name.pehl.piriti.rebind.xml;

import name.pehl.piriti.rebind.VelocityCreator;
import name.pehl.piriti.rebind.VelocityGenerator;
import name.pehl.piriti.xml.client.XmlReader;

import com.google.gwt.core.ext.GeneratorContext;
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
    /**
     * Creates a {@link XmlReaderCreator}.
     * 
     * @param context
     * @param interfaceType
     * @param implName
     * @return {@link XmlReaderCreator}
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.AbstractGenerator#createCreator(com.google.gwt.core.ext.GeneratorContext,
     *      com.google.gwt.core.ext.typeinfo.JClassType, java.lang.String,
     *      com.google.gwt.core.ext.TreeLogger)
     */
    @Override
    protected VelocityCreator createCreator(GeneratorContext context, JClassType interfaceType, String implName)
            throws UnableToCompleteException
    {
        return new XmlReaderCreator(context, interfaceType, implName, XmlReader.class.getCanonicalName());
    }
}
