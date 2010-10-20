package name.pehl.piriti.gxt.rebind.json.propertyhandler;

import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.gxt.client.json.JsonModelReader;

/**
 * {@link PropertyHandler} implementation for types with an own {@link JsonReader}.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class JsonRegistryPropertyHandler extends name.pehl.piriti.rebind.json.propertyhandler.JsonRegistryPropertyHandler
{
    @Override
    protected String getReaderClassname()
    {
        return JsonModelReader.class.getName();
    }

    // TODO:
    // @Override
    // protected String getWriterClassname()
    // {
    // return JsonModelWriter.class.getName();
    // }
}
