package name.pehl.piriti.rebind.xml.propertyhandler;

import name.pehl.piriti.rebind.json.propertyhandler.IdPropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.AbstractPropertyHandlerLookup;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;

import com.google.gwt.core.ext.TreeLogger;

public class XmlPropertyHandlerLookup extends AbstractPropertyHandlerLookup
{
    public XmlPropertyHandlerLookup(TreeLogger logger)
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
        return new IdRefPropertyHandler(logger);
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
        return new ArrayPropertyHandler(logger);
    }


    @Override
    protected PropertyHandler newCollectionHandler()
    {
        return new CollectionPropertyHandler(logger);
    }


    @Override
    protected PropertyHandler newDefaultHandler()
    {
        return new DefaultPropertyHandler(logger);
    }
}
