package name.pehl.piriti.rebind.json;

import name.pehl.piriti.client.json.JsonWriter;
import name.pehl.piriti.rebind.AbstractWriterCreator;
import name.pehl.piriti.rebind.AbstractWriterGenerator;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * GWT Generator for {@linkplain JsonWriter}s. The generator delegates to
 * {@link JsonWriterCreator} which is responsible for generating the code.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class JsonWriterGenerator extends AbstractWriterGenerator
{
    @Override
    protected AbstractWriterCreator createCreator(GeneratorContext context, JClassType interfaceType, String implName,
            TreeLogger logger) throws UnableToCompleteException
    {
        return new JsonWriterCreator(context, interfaceType, implName, JsonWriter.class.getCanonicalName(), logger);
    }
}
