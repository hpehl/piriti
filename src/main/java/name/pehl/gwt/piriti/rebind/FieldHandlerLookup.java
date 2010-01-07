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
 * @author $Author:$
 * @version $Revision:$
 */
public class FieldHandlerLookup
{
    private Map<String, FieldHandler> lookup;


    public FieldHandlerLookup()
    {
        FieldHandler handler = null;
        lookup = new HashMap<String, FieldHandler>();

        // Basics
        handler = new DefaultFieldHandler();
        lookup.put(Boolean.class.getName(), handler);
        lookup.put(Byte.class.getName(), handler);
        lookup.put(Character.class.getName(), handler);
        lookup.put(Date.class.getName(), handler);
        lookup.put(Double.class.getName(), handler);
        lookup.put(Float.class.getName(), handler);
        lookup.put(Integer.class.getName(), handler);
        lookup.put(Long.class.getName(), handler);
        lookup.put(Short.class.getName(), handler);

        // String
        handler = new StringFieldHandler();
        lookup.put(String.class.getName(), handler);

        // Collections
        handler = new CollectionFieldHandler();
        lookup.put(Collection.class.getName(), handler);
        lookup.put(List.class.getName(), handler);
        lookup.put(ArrayList.class.getName(), handler);
        lookup.put(LinkedList.class.getName(), handler);
        lookup.put(Set.class.getName(), handler);
        lookup.put(HashSet.class.getName(), handler);
        lookup.put(SortedSet.class.getName(), handler);
        lookup.put(TreeSet.class.getName(), handler);

        // Maps
        handler = new MapFieldHandler();
        lookup.put(Map.class.getName(), handler);
        lookup.put(HashMap.class.getName(), handler);
        lookup.put(SortedMap.class.getName(), handler);
        lookup.put(TreeMap.class.getName(), handler);
    }


    public FieldHandler get(String classname)
    {
        if (classname != null)
        {
            return lookup.get(classname);
        }
        return null;
    }
}
