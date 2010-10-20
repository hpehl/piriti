package name.pehl.piriti.rebind.xml;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import name.pehl.piriti.rebind.propertyhandler.Assignment.AssignmentType;
import name.pehl.piriti.rebind.propertyhandler.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerRegistry;
import name.pehl.piriti.rebind.xml.propertyhandler.ArrayPropertyHandler;
import name.pehl.piriti.rebind.xml.propertyhandler.CollectionPropertyHandler;
import name.pehl.piriti.rebind.xml.propertyhandler.ConverterPropertyHandler;
import name.pehl.piriti.rebind.xml.propertyhandler.EnumPropertyHandler;
import name.pehl.piriti.rebind.xml.propertyhandler.IdPropertyHandler;
import name.pehl.piriti.rebind.xml.propertyhandler.IdRefPropertyHandler;
import name.pehl.piriti.rebind.xml.propertyhandler.StringPropertyHandler;
import name.pehl.piriti.rebind.xml.propertyhandler.XmlRegistryPropertyHandler;

/**
 * {@link PropertyHandlerRegistry} used by the {@link XmlReaderCreator}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class XmlPropertyHandlerRegistry implements PropertyHandlerRegistry
{
    private final Map<String, PropertyHandler> registry;


    /**
     * Construct a new instance of this class and registers the initial field
     * handlers.
     */
    public XmlPropertyHandlerRegistry()
    {
        registry = new HashMap<String, PropertyHandler>();
        registerInitialPropertyHandlers();
    }


    /**
     * Registers the initial property handlers for the xml reader. The following
     * handlers are registered:
     * <ul>
     * <li>{@linkplain ConverterPropertyHandler}
     * <ul>
     * <li>Boolean
     * <li>Byte
     * <li>Character
     * <li>Date
     * <li>Double
     * <li>Float
     * <li>Integer
     * <li>Long
     * <li>Short
     * </ul>
     * <li>{@linkplain StringPropertyHandler}
     * <ul>
     * <li>String
     * </ul>
     * <li>{@linkplain CollectionPropertyHandler}
     * <ul>
     * <li>Collection
     * <li>List
     * <li>ArrayList
     * <li>LinkedList
     * <li>Set
     * <li>HashSet
     * <li>SortedSet
     * <li>TreeSet
     * </ul>
     * <ul>
     */
    private void registerInitialPropertyHandlers()
    {
        PropertyHandler handler = null;

        // Basics
        handler = newConverterFieldHandler();
        registry.put(Boolean.class.getName(), handler);
        registry.put(Byte.class.getName(), handler);
        registry.put(Character.class.getName(), handler);
        registry.put(Date.class.getName(), handler);
        registry.put(Double.class.getName(), handler);
        registry.put(Float.class.getName(), handler);
        registry.put(Integer.class.getName(), handler);
        registry.put(Long.class.getName(), handler);
        registry.put(Short.class.getName(), handler);

        // String
        handler = newStringFieldHandler();
        registry.put(String.class.getName(), handler);

        // Collections
        handler = newCollectionFieldHandler();
        registry.put(Collection.class.getName(), handler);
        registry.put(List.class.getName(), handler);
        registry.put(ArrayList.class.getName(), handler);
        registry.put(LinkedList.class.getName(), handler);
        registry.put(Set.class.getName(), handler);
        registry.put(HashSet.class.getName(), handler);
        registry.put(SortedSet.class.getName(), handler);
        registry.put(TreeSet.class.getName(), handler);
    }


    /**
     * Looks up a property handler based on the information provided in the
     * property context. The lookup logic is implemented like this:
     * <ol>
     * <li>If the assignment type is {@link AssignmentType#ID} return
     * {@link IdPropertyHandler}
     * <li>if the assignment type is {@link AssignmentType#IDREF} return
     * {@link IdRefPropertyHandler}
     * <li>if the fields type is a primitive return the
     * {@link ConverterPropertyHandler}
     * <li>if the fields type is an enum return {@link EnumPropertyHandler}
     * <li>If the fields type is an array return {@link ArrayPropertyHandler}
     * <li>Try to lookup the field handler by the fields type classname (this
     * will resolve all types registered in
     * {@link #registerInitialPropertyHandlers()}
     * <li>If no field handler return {@link XmlRegistryPropertyHandler}.
     * </ol>
     * 
     * @param fieldContext
     * @return
     * @see name.pehl.piriti.rebind.propertyhandler.PropertyHandlerRegistry#findPropertyHandler(name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public PropertyHandler findPropertyHandler(PropertyContext fieldContext)
    {
        PropertyHandler handler = null;

        if (fieldContext.getAssignment().getType() == AssignmentType.ID)
        {
            handler = newIdFieldHandler();
        }
        else if (fieldContext.getAssignment().getType() == AssignmentType.IDREF)
        {
            handler = newIdRefFieldHandler();
        }
        else if (fieldContext.isPrimitive())
        {
            handler = newConverterFieldHandler();
        }
        else if (fieldContext.isEnum())
        {
            handler = newEnumFieldHandler();
        }
        else if (fieldContext.isArray())
        {
            handler = newArrayFieldHandler();
        }
        else
        {
            // Ask the registry for all other stuff (basic types,
            // collections, maps, ...)
            handler = registry.get(fieldContext.getType().getQualifiedSourceName());
            if (handler == null)
            {
                // Delegate to the XmlRegistry to resolve other mapped models
                handler = newRegistryFieldHandler();
            }
        }
        return handler;
    }


    protected PropertyHandler newIdFieldHandler()
    {
        return new IdPropertyHandler();
    }


    protected PropertyHandler newIdRefFieldHandler()
    {
        return new IdRefPropertyHandler();
    }


    protected PropertyHandler newConverterFieldHandler()
    {
        return new ConverterPropertyHandler();
    }


    protected PropertyHandler newStringFieldHandler()
    {
        return new StringPropertyHandler();
    }


    protected PropertyHandler newEnumFieldHandler()
    {
        return new EnumPropertyHandler();
    }


    protected PropertyHandler newArrayFieldHandler()
    {
        return new ArrayPropertyHandler();
    }


    protected PropertyHandler newCollectionFieldHandler()
    {
        return new CollectionPropertyHandler();
    }


    protected PropertyHandler newRegistryFieldHandler()
    {
        return new XmlRegistryPropertyHandler();
    }
}
