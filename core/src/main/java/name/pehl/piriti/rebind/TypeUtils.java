package name.pehl.piriti.rebind;

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

import com.google.gwt.core.ext.typeinfo.JType;

/**
 * Utility methods for {@linkplain JType}s.
 * @author $Author: harald.pehl $
 * @version $Date: 2010-01-14 17:11:02 +0100 (Do, 14. Jan 2010) $ $Revision: 54 $
 */
public final class TypeUtils
{
    private TypeUtils()
    {
    }


    public static boolean isBasicType(JType type)
    {
        if (Boolean.class.getName().equals(type.getQualifiedSourceName())
                || Byte.class.getName().equals(type.getQualifiedSourceName())
                || Character.class.getName().equals(type.getQualifiedSourceName())
                || Date.class.getName().equals(type.getQualifiedSourceName())
                || Double.class.getName().equals(type.getQualifiedSourceName())
                || Float.class.getName().equals(type.getQualifiedSourceName())
                || Integer.class.getName().equals(type.getQualifiedSourceName())
                || Long.class.getName().equals(type.getQualifiedSourceName())
                || Short.class.getName().equals(type.getQualifiedSourceName())
                || String.class.getName().equals(type.getQualifiedSourceName()))
        {
            return true;
        }
        return false;
    }


    public static boolean isCollection(JType type)
    {
        if (Collection.class.getName().equals(type.getQualifiedSourceName())
                || List.class.getName().equals(type.getQualifiedSourceName())
                || ArrayList.class.getName().equals(type.getQualifiedSourceName())
                || LinkedList.class.getName().equals(type.getQualifiedSourceName())
                || Set.class.getName().equals(type.getQualifiedSourceName())
                || HashSet.class.getName().equals(type.getQualifiedSourceName())
                || SortedSet.class.getName().equals(type.getQualifiedSourceName())
                || TreeSet.class.getName().equals(type.getQualifiedSourceName()))
        {
            return true;
        }
        return false;
    }


    public static boolean isMap(JType type)
    {
        if (Map.class.getName().equals(type.getQualifiedSourceName())
                || HashMap.class.getName().equals(type.getQualifiedSourceName())
                || SortedMap.class.getName().equals(type.getQualifiedSourceName())
                || TreeMap.class.getName().equals(type.getQualifiedSourceName()))
        {
            return true;
        }
        return false;
    }
}
