package name.pehl.piriti.rebind.json;

import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.rebind.VelocityCreator;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * Creator for {@linkplain JsonReader}s.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 137 $
 */
public class JsonReaderCreator extends VelocityCreator
{
    public JsonReaderCreator(GeneratorContext generatorContext, JClassType rwType, String implName, String rwClassname)
            throws UnableToCompleteException
    {
        super(generatorContext, rwType, implName, rwClassname);
    }
}
