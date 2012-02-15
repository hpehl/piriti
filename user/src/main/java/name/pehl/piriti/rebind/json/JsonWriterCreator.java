package name.pehl.piriti.rebind.json;

import name.pehl.piriti.rebind.VelocityCreator;

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
    public JsonWriterCreator(JClassType rwType, String implName, String rwClassname) throws UnableToCompleteException
    {
        super(rwType, implName, rwClassname);
    }


    @Override
    protected String getTemplate()
    {
        return "name/pehl/piriti/rebind/json/writer/writer.vm";
    }
}
