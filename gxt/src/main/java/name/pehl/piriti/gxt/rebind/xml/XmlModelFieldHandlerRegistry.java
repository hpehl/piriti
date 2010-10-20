package name.pehl.piriti.gxt.rebind.xml;

import name.pehl.piriti.gxt.rebind.xml.propertyhandler.CollectionFieldHandler;
import name.pehl.piriti.gxt.rebind.xml.propertyhandler.XmlRegistryFieldHandler;
import name.pehl.piriti.rebind.propertyhandler.FieldHandler;
import name.pehl.piriti.rebind.propertyhandler.FieldHandlerRegistry;
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
