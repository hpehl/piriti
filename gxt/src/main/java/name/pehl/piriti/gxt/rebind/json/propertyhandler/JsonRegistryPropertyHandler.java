package name.pehl.piriti.gxt.rebind.json.propertyhandler;

import name.pehl.piriti.gxt.json.client.JsonModelReader;
import name.pehl.piriti.json.client.JsonReader;

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
