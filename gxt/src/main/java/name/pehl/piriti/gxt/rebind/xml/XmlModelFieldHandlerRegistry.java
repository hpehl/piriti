package name.pehl.piriti.gxt.rebind.xml;

import name.pehl.piriti.gxt.rebind.xml.fieldhandler.ArrayFieldHandler;
import name.pehl.piriti.gxt.rebind.xml.fieldhandler.CollectionFieldHandler;
import name.pehl.piriti.gxt.rebind.xml.fieldhandler.ConverterFieldHandler;
import name.pehl.piriti.gxt.rebind.xml.fieldhandler.EnumFieldHandler;
import name.pehl.piriti.gxt.rebind.xml.fieldhandler.StringFieldHandler;
import name.pehl.piriti.gxt.rebind.xml.fieldhandler.XmlRegistryFieldHandler;
import name.pehl.piriti.rebind.fieldhandler.FieldHandler;
import name.pehl.piriti.rebind.fieldhandler.FieldHandlerRegistry;
import name.pehl.piriti.rebind.xml.XmlFieldHandlerRegistry;

/**
 * {@link FieldHandlerRegistry} used by the {@link XmlModelReaderCreator}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class XmlModelFieldHandlerRegistry extends XmlFieldHandlerRegistry
{
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
        return new XmlRegistryFieldHandler();
    }
}
