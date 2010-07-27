package name.pehl.piriti.gxt.rebind.json;

import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.rebind.AbstractCreator;
import name.pehl.piriti.rebind.AbstractGenerator;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * GWT Generator for JsonReaders. The generator delegates to
 * {@link JsonModelReaderCreator} which is responsible for generating the code.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 137 $
 */
public class JsonModelReaderGenerator extends AbstractGenerator
{
    @Override
    protected AbstractCreator createCreator(GeneratorContext context, JClassType interfaceType, String implName,
            TreeLogger logger) throws UnableToCompleteException
    {
        return new JsonModelReaderCreator(context, interfaceType, implName, JsonReader.class.getCanonicalName(), logger);
    }
}
