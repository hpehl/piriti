package name.pehl.piriti.rebind.json;

import name.pehl.piriti.rebind.VelocityCreator;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * Class which generates the code necessary to serialize a POJO to JSON.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class JsonWriterCreator extends VelocityCreator
{
    public JsonWriterCreator(GeneratorContext generatorContext, JClassType rwType, String implName, String rwClassname)
            throws UnableToCompleteException
    {
        super(generatorContext, rwType, implName, rwClassname);
    }
}
