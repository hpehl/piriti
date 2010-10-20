package name.pehl.piriti.gxt.rebind.json;

import name.pehl.piriti.gxt.rebind.json.propertyhandler.CollectionFieldHandler;
import name.pehl.piriti.gxt.rebind.json.propertyhandler.JsonRegistryFieldHandler;
import name.pehl.piriti.rebind.json.JsonReaderFieldHandlerRegistry;
import name.pehl.piriti.rebind.propertyhandler.FieldHandler;
import name.pehl.piriti.rebind.propertyhandler.FieldHandlerRegistry;

/**
 * {@link FieldHandlerRegistry} used by the {@link JsonModelReaderCreator}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 136 $
 */
public class JsonModelReaderFieldHandlerRegistry extends JsonReaderFieldHandlerRegistry
{
    @Override
    protected FieldHandler newCollectionFieldHandler()
    {
        return new CollectionFieldHandler();
    }


    @Override
    protected FieldHandler newRegistryFieldHandler()
    {
        return new JsonRegistryFieldHandler();
    }
}
