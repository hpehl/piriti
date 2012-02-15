package name.pehl.piriti.rebind.json;

import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.rebind.VelocityCreator;
import name.pehl.piriti.rebind.VelocityGenerator;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * GWT Generator which creates a {@link JsonReaderCreator}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 137 $
 */
public class JsonReaderGenerator extends VelocityGenerator
{
    @Override
    protected VelocityCreator createCreator(JClassType interfaceType, String implName) throws UnableToCompleteException
    {
        return new JsonReaderCreator(interfaceType, implName, JsonReader.class.getCanonicalName());
    }
}
