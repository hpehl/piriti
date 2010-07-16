package name.pehl.piriti.gxt.rebind.json;

import name.pehl.piriti.gxt.rebind.json.fieldhandler.CollectionFieldHandler;
import name.pehl.piriti.gxt.rebind.json.fieldhandler.JsonRegistryFieldHandler;
import name.pehl.piriti.rebind.fieldhandler.FieldHandler;
import name.pehl.piriti.rebind.fieldhandler.FieldHandlerRegistry;
import name.pehl.piriti.rebind.json.JsonFieldHandlerRegistry;

/**
 * {@link FieldHandlerRegistry} used by the {@link JsonModelReaderCreator}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 136 $
 */
public class JsonModelFieldHandlerRegistry extends JsonFieldHandlerRegistry
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
