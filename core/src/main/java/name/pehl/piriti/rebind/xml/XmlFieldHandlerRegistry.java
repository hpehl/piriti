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

import name.pehl.piriti.rebind.FieldContext;
import name.pehl.piriti.rebind.FieldHandlerRegistry;
import name.pehl.piriti.rebind.fieldhandler.FieldHandler;
import name.pehl.piriti.rebind.xml.fieldhandler.ArrayFieldHandler;
import name.pehl.piriti.rebind.xml.fieldhandler.CollectionFieldHandler;
import name.pehl.piriti.rebind.xml.fieldhandler.ConverterFieldHandler;
import name.pehl.piriti.rebind.xml.fieldhandler.EnumFieldHandler;
import name.pehl.piriti.rebind.xml.fieldhandler.StringFieldHandler;
import name.pehl.piriti.rebind.xml.fieldhandler.XmlRegistryFieldHandler;

/**
 * {@link FieldHandlerRegistry} used by the {@link XmlReaderCreator}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class XmlFieldHandlerRegistry implements FieldHandlerRegistry
{
    private Map<String, FieldHandler> registry;


    /**
     * Construct a new instance of this class and registers the initial field
     * handlers.
     */
    public XmlFieldHandlerRegistry()
    {
        registry = new HashMap<String, FieldHandler>();
        registerInitialFieldHandlers();
    }


    /**
     * Registers the initial field handler for the xml reader. The following
     * handlers are registered:
     * <ul>
     * <li>{@linkplain ConverterFieldHandler}
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
     * <li>{@linkplain StringFieldHandler}
     * <ul>
     * <li>String
     * </ul>
     * <li>{@linkplain CollectionFieldHandler}
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
    private void registerInitialFieldHandlers()
    {
        FieldHandler handler = null;

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
     * Looks up a field handler based on the information provided in the field
     * context. The lookup logic is implemented like this:
     * <ol>
     * <li>If the fields type is a primitive return the
     * {@link ConverterFieldHandler}
     * <li>if the fields type is an enum return {@link EnumFieldHandler}
     * <li>If the fields type is an array return {@link ArrayFieldHandler}
     * <li>Try to lookup the field handler by the fields type classname (this
     * will resolve all types registered in
     * {@link #registerInitialFieldHandlers()}
     * <li>If no field handler return {@link XmlRegistryFieldHandler}.
     * </ol>
     * 
     * @param fieldContext
     * @return
     * @see name.pehl.piriti.rebind.FieldHandlerRegistry#findFieldHandler(name.pehl.piriti.rebind.FieldContext)
     */
    public FieldHandler findFieldHandler(FieldContext fieldContext)
    {
        FieldHandler handler = null;
        if (fieldContext.isPrimitive())
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
            handler = registry.get(fieldContext.getFieldType().getQualifiedSourceName());
            if (handler == null)
            {
                // Delegate to the XmlRegistry to resolve other mapped models
                handler = newRegistryFieldHandler();
            }
        }
        return handler;
    }


    protected FieldHandler newConverterFieldHandler()
    {
        return new ConverterFieldHandler();
    }


    protected FieldHandler newStringFieldHandler()
    {
        return new StringFieldHandler();
    }


    protected FieldHandler newEnumFieldHandler()
    {
        return new EnumFieldHandler();
    }


    protected FieldHandler newArrayFieldHandler()
    {
        return new ArrayFieldHandler();
    }


    protected FieldHandler newCollectionFieldHandler()
    {
        return new CollectionFieldHandler();
    }


    protected FieldHandler newRegistryFieldHandler()
    {
        return new XmlRegistryFieldHandler();
    }
}
