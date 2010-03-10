package name.pehl.gwt.piriti.rebind.json;

import name.pehl.gwt.piriti.client.json.JsonReader;
import name.pehl.gwt.piriti.rebind.AbstractReaderCreator;
import name.pehl.gwt.piriti.rebind.AbstractReaderGenerator;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * GWT Generator for JsonReaders. The generator delegates to
 * {@link JsonReaderCreator} which is responsible for generating the
 * implementation.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 137 $
 */
public class JsonReaderGenerator extends AbstractReaderGenerator
{
    @Override
    protected AbstractReaderCreator createCreator(GeneratorContext context, JClassType interfaceType, String implName,
            TreeLogger logger) throws UnableToCompleteException
    {
        return new JsonReaderCreator(context, interfaceType, implName, JsonReader.class.getCanonicalName(), logger);
    }
}
