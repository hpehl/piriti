package name.pehl.piriti.rebind.json;

import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.rebind.VelocityCreator;
import name.pehl.piriti.rebind.VelocityGenerator;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * GWT Generator which creates a {@link JsonWriterCreator}.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class JsonWriterGenerator extends VelocityGenerator
{
    /**
     * Creates a {@link JsonWriterCreator}.
     * 
     * @param context
     * @param interfaceType
     * @param implName
     * @return {@link JsonWriterCreator}
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.AbstractGenerator#createCreator(com.google.gwt.core.ext.GeneratorContext,
     *      com.google.gwt.core.ext.typeinfo.JClassType, java.lang.String,
     *      com.google.gwt.core.ext.TreeLogger)
     */
    @Override
    protected VelocityCreator createCreator(GeneratorContext context, JClassType interfaceType, String implName)
            throws UnableToCompleteException
    {
        return new JsonWriterCreator(context, interfaceType, implName, JsonWriter.class.getCanonicalName());
    }
}
