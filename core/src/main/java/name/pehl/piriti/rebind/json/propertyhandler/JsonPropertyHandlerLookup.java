package name.pehl.piriti.rebind.json.propertyhandler;

import name.pehl.piriti.rebind.propertyhandler.AbstractPropertyHandlerLookup;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;

import com.google.gwt.core.ext.TreeLogger;

public class JsonPropertyHandlerLookup extends AbstractPropertyHandlerLookup
{
    public JsonPropertyHandlerLookup(TreeLogger logger)
    {
        super(logger);
    }


    @Override
    protected PropertyHandler newIdHandler()
    {
        return new IdPropertyHandler(logger);
    }


    @Override
    protected PropertyHandler newIdRefHandler()
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    protected PropertyHandler newBooleanHandler()
    {
        return new BooleanPropertyHandler(logger);
    }


    @Override
    protected PropertyHandler newNumericHandler()
    {
        return new NumericPropertyHandler(logger);
    }


    @Override
    protected PropertyHandler newCharacterHandler()
    {
        return new CharacterPropertyHandler(logger);
    }


    @Override
    protected PropertyHandler newStringHandler()
    {
        return new StringPropertyHandler(logger);
    }


    @Override
    protected PropertyHandler newEnumHandler()
    {
        return new EnumPropertyHandler(logger);
    }


    @Override
    protected PropertyHandler newArrayHandler()
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    protected PropertyHandler newCollectionHandler()
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    protected PropertyHandler newDefaultHandler()
    {
        return new DefaultPropertyHandler(logger);
    }

}
