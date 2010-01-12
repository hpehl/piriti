package name.pehl.gwt.piriti.rebind;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Registry which maps classnames to {@linkplain FieldHandler}s. This registry
 * is used by the {@link XmlReaderCreator} when generating code.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class FieldHandlerRegistry
{
    private Map<String, FieldHandler> registry;


    public FieldHandlerRegistry()
    {
        registry = new HashMap<String, FieldHandler>();
        registerDefaultFieldHandlers();
    }


    protected void registerDefaultFieldHandlers()
    {
        FieldHandler handler = null;

        // Basics
        handler = new DefaultFieldHandler();
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
        handler = new StringFieldHandler();
        registry.put(String.class.getName(), handler);

        // Collections
        handler = new CollectionFieldHandler();
        registry.put(Collection.class.getName(), handler);
        registry.put(List.class.getName(), handler);
        registry.put(ArrayList.class.getName(), handler);
        registry.put(LinkedList.class.getName(), handler);
        registry.put(Set.class.getName(), handler);
        registry.put(HashSet.class.getName(), handler);
        registry.put(SortedSet.class.getName(), handler);
        registry.put(TreeSet.class.getName(), handler);

        // Maps
        handler = new MapFieldHandler();
        registry.put(Map.class.getName(), handler);
        registry.put(HashMap.class.getName(), handler);
        registry.put(SortedMap.class.getName(), handler);
        registry.put(TreeMap.class.getName(), handler);
    }


    public FieldHandler findFieldHandler(FieldContext fieldContext)
    {
        FieldHandler handler = null;
        if (fieldContext.isPrimitive())
        {
            handler = new DefaultFieldHandler();
        }
        else if (fieldContext.isEnum())
        {
            handler = new EnumFieldHandler();
        }
        else if (fieldContext.isArray())
        {
            handler = new ArrayFieldHandler();
        }
        else
        {
            // Ask the registry for all other stuff (basic types,
            // collections, maps, ...)
            handler = registry.get(fieldContext.getFieldType().getQualifiedSourceName());
            if (handler == null)
            {
                // Delegate to the XmlRegistry to resolve other mapped models
                handler = new XmlRegistryFieldHandler();
            }
        }
        return handler;
    }
}
