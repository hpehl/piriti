package name.pehl.piriti.gxt.rebind.xml;

import name.pehl.piriti.gxt.rebind.xml.propertyhandler.CollectionPropertyHandler;
import name.pehl.piriti.gxt.rebind.xml.propertyhandler.XmlRegistryPropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerRegistry;
import name.pehl.piriti.rebind.xml.XmlPropertyHandlerRegistry;

/**
 * {@link PropertyHandlerRegistry} used by the {@link XmlModelReaderCreator}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class XmlModelPropertyHandlerRegistry extends XmlPropertyHandlerRegistry
{
    @Override
    protected PropertyHandler newCollectionFieldHandler()
    {
        return new CollectionPropertyHandler();
    }


    @Override
    protected PropertyHandler newRegistryFieldHandler()
    {
        return new XmlRegistryPropertyHandler();
    }
}
