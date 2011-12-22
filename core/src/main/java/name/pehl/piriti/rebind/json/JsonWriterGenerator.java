package name.pehl.piriti.rebind.json;

import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.rebind.VelocityCreator;
import name.pehl.piriti.rebind.VelocityGenerator;

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
    @Override
    protected VelocityCreator createCreator(JClassType interfaceType, String implName) throws UnableToCompleteException
    {
        return new JsonWriterCreator(interfaceType, implName, JsonWriter.class.getCanonicalName());
    }
}
