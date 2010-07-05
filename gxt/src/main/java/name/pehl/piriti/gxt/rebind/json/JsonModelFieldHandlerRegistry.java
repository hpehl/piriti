package name.pehl.piriti.gxt.rebind.json;

import name.pehl.piriti.gxt.rebind.json.fieldhandler.ArrayFieldHandler;
import name.pehl.piriti.gxt.rebind.json.fieldhandler.BooleanFieldHandler;
import name.pehl.piriti.gxt.rebind.json.fieldhandler.CollectionFieldHandler;
import name.pehl.piriti.gxt.rebind.json.fieldhandler.ConverterFieldHandler;
import name.pehl.piriti.gxt.rebind.json.fieldhandler.EnumFieldHandler;
import name.pehl.piriti.gxt.rebind.json.fieldhandler.JsonRegistryFieldHandler;
import name.pehl.piriti.gxt.rebind.json.fieldhandler.NumberFieldHandler;
import name.pehl.piriti.gxt.rebind.json.fieldhandler.StringFieldHandler;
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
    protected FieldHandler newBooleanFieldHandler()
    {
        return new BooleanFieldHandler();
    }


    @Override
    protected FieldHandler newNumberFieldHandler()
    {
        return new NumberFieldHandler();
    }


    @Override
    protected FieldHandler newConverterFieldHandler()
    {
        return new ConverterFieldHandler();
    }


    @Override
    protected FieldHandler newStringFieldHandler()
    {
        return new StringFieldHandler();
    }


    @Override
    protected FieldHandler newEnumFieldHandler()
    {
        return new EnumFieldHandler();
    }


    @Override
    protected FieldHandler newArrayFieldHandler()
    {
        return new ArrayFieldHandler();
    }


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
