package name.pehl.piriti.gxt.rebind.json;

import name.pehl.piriti.gxt.rebind.json.propertyhandler.CollectionPropertyHandler;
import name.pehl.piriti.gxt.rebind.json.propertyhandler.JsonRegistryPropertyHandler;
import name.pehl.piriti.rebind.json.JsonReaderPropertyHandlerRegistry;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerRegistry;

/**
 * {@link PropertyHandlerRegistry} used by the {@link JsonModelReaderCreator}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 136 $
 */
public class JsonModelReaderPropertyHandlerRegistry extends JsonReaderPropertyHandlerRegistry
{
    @Override
    protected PropertyHandler newCollectionFieldHandler()
    {
        return new CollectionPropertyHandler();
    }


    @Override
    protected PropertyHandler newRegistryFieldHandler()
    {
        return new JsonRegistryPropertyHandler();
    }
}
