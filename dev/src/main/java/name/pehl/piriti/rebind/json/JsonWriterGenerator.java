package name.pehl.piriti.rebind.json;

import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.rebind.VelocityGenerator;

/**
 * GWT Generator which creates a {@link JsonWriterCreator}.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class JsonWriterGenerator extends VelocityGenerator
{
    @Override
    protected String getInterfaceName()
    {
        return JsonWriter.class.getCanonicalName();
    }


    @Override
    protected String getTemplateName()
    {
        return "name/pehl/piriti/rebind/json/writer/writer.vm";
    }
}
