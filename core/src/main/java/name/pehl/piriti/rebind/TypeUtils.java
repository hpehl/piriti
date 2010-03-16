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

import com.google.gwt.core.ext.typeinfo.JPrimitiveType;
import com.google.gwt.core.ext.typeinfo.JType;

/**
 * Utility methods for {@linkplain JType}s.
 * 
 * @author $Author: harald.pehl $
 * @version $Date: 2010-01-14 17:11:02 +0100 (Do, 14. Jan 2010) $ $Revision: 54
 *          $
 */
public final class TypeUtils
{
    private TypeUtils()
    {
    }


    public static boolean isBoolean(JType type)
    {
        if (type != null)
        {
            JPrimitiveType primitive = type.isPrimitive();
            return (primitive != null && primitive.equals(JPrimitiveType.BOOLEAN))
                    || type.getQualifiedSourceName().equals(Boolean.class.getName());
        }
        return false;
    }


    public static boolean isByte(JType type)
    {
        if (type != null)
        {
            JPrimitiveType primitive = type.isPrimitive();
            return (primitive != null && primitive.equals(JPrimitiveType.BYTE))
                    || type.getQualifiedSourceName().equals(Byte.class.getName());
        }
        return false;
    }


    public static boolean isCharacter(JType type)
    {
        if (type != null)
        {
            JPrimitiveType primitive = type.isPrimitive();
            return (primitive != null && primitive.equals(JPrimitiveType.CHAR))
                    || type.getQualifiedSourceName().equals(Character.class.getName());
        }
        return false;
    }


    public static boolean isDate(JType type)
    {
        if (type != null)
        {
            return type.getQualifiedSourceName().equals(Date.class.getName());
        }
        return false;
    }


    public static boolean isDouble(JType type)
    {
        if (type != null)
        {
            JPrimitiveType primitive = type.isPrimitive();
            return (primitive != null && primitive.equals(JPrimitiveType.DOUBLE))
                    || type.getQualifiedSourceName().equals(Double.class.getName());
        }
        return false;
    }


    public static boolean isFloat(JType type)
    {
        if (type != null)
        {
            JPrimitiveType primitive = type.isPrimitive();
            return (primitive != null && primitive.equals(JPrimitiveType.FLOAT))
                    || type.getQualifiedSourceName().equals(Float.class.getName());
        }
        return false;
    }


    public static boolean isInteger(JType type)
    {
        if (type != null)
        {
            JPrimitiveType primitive = type.isPrimitive();
            return (primitive != null && primitive.equals(JPrimitiveType.INT))
                    || type.getQualifiedSourceName().equals(Integer.class.getName());
        }
        return false;
    }


    public static boolean isLong(JType type)
    {
        if (type != null)
        {
            JPrimitiveType primitive = type.isPrimitive();
            return (primitive != null && primitive.equals(JPrimitiveType.LONG))
                    || type.getQualifiedSourceName().equals(Long.class.getName());
        }
        return false;
    }


    public static boolean isShort(JType type)
    {
        if (type != null)
        {
            JPrimitiveType primitive = type.isPrimitive();
            return (primitive != null && primitive.equals(JPrimitiveType.SHORT))
                    || type.getQualifiedSourceName().equals(Short.class.getName());
        }
        return false;
    }


    public static boolean isString(JType type)
    {
        if (type != null)
        {
            return type.getQualifiedSourceName().equals(String.class.getName());
        }
        return false;
    }


    public static boolean isBasicType(JType type)
    {
        return isBoolean(type) || isByte(type) || isCharacter(type) || isDate(type) || isDouble(type) || isFloat(type)
                || isInteger(type) || isLong(type) || isShort(type) || isString(type);
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
