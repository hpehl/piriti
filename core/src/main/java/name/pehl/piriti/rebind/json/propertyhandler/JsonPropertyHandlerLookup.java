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
        // TODO Auto-generated method stub
        return null;
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
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    protected PropertyHandler newNumericHandler()
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    protected PropertyHandler newCharacterHandler()
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    protected PropertyHandler newStringHandler()
    {
        return new StringPropertyHandler(logger);
    }


    @Override
    protected PropertyHandler newEnumHandler()
    {
        // TODO Auto-generated method stub
        return null;
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
        // TODO Auto-generated method stub
        return null;
    }

}
