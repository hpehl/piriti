package name.pehl.piriti.rebind.json;

import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.rebind.VelocityGenerator;

/**
 * GWT Generator which creates a {@link JsonReaderCreator}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 137 $
 */
public class JsonReaderGenerator extends VelocityGenerator
{
    @Override
    protected String getInterfaceName()
    {
        return JsonReader.class.getCanonicalName();
    }


    @Override
    protected String getTemplateName()
    {
        return "name/pehl/piriti/rebind/json/reader/reader.vm";
    }
}
