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

import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JParameterizedType;
import com.google.gwt.core.ext.typeinfo.JPrimitiveType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;

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


    /**
     * Returns true if the type is boolean or Boolean, false otherwise.
     * 
     * @param type
     * @return true if the type is boolean or Boolean, false otherwise.
     */
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


    /**
     * Returns true if the type is byte or Byte, false otherwise.
     * 
     * @param type
     * @return true if the type is byte or Byte, false otherwise.
     */
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


    /**
     * Returns true if the type is char or Caharacter, false otherwise.
     * 
     * @param type
     * @return true if the type is char or Character, false otherwise.
     */
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


    /**
     * Returns true if the type is Date, false otherwise.
     * 
     * @param type
     * @return true if the type is Date, false otherwise.
     */
    public static boolean isDate(JType type)
    {
        if (type != null)
        {
            return type.getQualifiedSourceName().equals(Date.class.getName());
        }
        return false;
    }


    /**
     * Returns true if the type is double or Double, false otherwise.
     * 
     * @param type
     * @return true if the type is double or Double, false otherwise.
     */
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


    /**
     * Returns true if the type is float or Float, false otherwise.
     * 
     * @param type
     * @return true if the type is float or Float, false otherwise.
     */
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


    /**
     * Returns true if the type is int or Integer, false otherwise.
     * 
     * @param type
     * @return true if the type is int or Integer, false otherwise.
     */
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


    /**
     * Returns true if the type is long or Long, false otherwise.
     * 
     * @param type
     * @return true if the type is long or Long, false otherwise.
     */
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


    /**
     * Returns true if the type is short or Short, false otherwise.
     * 
     * @param type
     * @return true if the type is short or Short, false otherwise.
     */
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


    /**
     * Returns true if the type is String, false otherwise.
     * 
     * @param type
     * @return true if the type is String, false otherwise.
     */
    public static boolean isString(JType type)
    {
        if (type != null)
        {
            return type.getQualifiedSourceName().equals(String.class.getName());
        }
        return false;
    }


    /**
     * Returns true if the type is
     * <ul>
     * <li>boolean or Boolean
     * <li>byte or Byte
     * <li>char or Character
     * <li>Date
     * <li>double or Double
     * <li>float or Float
     * <li>int or Integer
     * <li>long or Long
     * <li>short or Short
     * <li>String
     * </ul>
     * false otherwise.
     * 
     * @param type
     * @return
     */
    public static boolean isBasicType(JType type)
    {
        return isBoolean(type) || isByte(type) || isCharacter(type) || isDate(type) || isDouble(type) || isFloat(type)
                || isInteger(type) || isLong(type) || isShort(type) || isString(type);
    }


    /**
     * Return true if the collection is
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
     * false otherwise,
     * 
     * @param type
     * @return
     */
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


    /**
     * Return true if the map is
     * <ul>
     * <li>Map
     * <li>HashMap
     * <li>SortedMap
     * <li>TreeMap
     * </ul>
     * false otherwise.
     * 
     * @param type
     * @return
     */
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


    /**
     * Returns the first type variable of the specified parameterized type or
     * null if {@code type} is <code>null</code> or does not have any type
     * variables.
     * 
     * @param type
     * @return
     */
    public static JClassType getTypeVariable(JType type)
    {
        JClassType parameterType = null;
        JParameterizedType parameterizedType = type.isParameterized();
        if (parameterizedType != null)
        {
            JClassType[] typeArgs = parameterizedType.getTypeArgs();
            if (typeArgs != null && typeArgs.length > 0)
            {
                parameterType = typeArgs[0];
            }
        }
        return parameterType;
    }


    public static boolean isFieldAccessible(JClassType type, String name)
    {
        boolean accessible = false;
        if (type != null)
        {
            JField field = type.getField(name);
            if (field != null)
            {
                accessible = !(field.isPrivate() || field.isFinal());
                if (!accessible)
                {
                    accessible = isFieldAccessible(type.getSuperclass(), name);
                }
            }
            else
            {
                accessible = isFieldAccessible(type.getSuperclass(), name);
            }
        }
        return accessible;
    }


    public static boolean isMethodAccessible(JClassType type, String name, JType parameter)
    {
        boolean accessible = false;
        if (type != null)
        {
            try
            {
                JMethod method = type.getMethod(name, new JType[] {parameter});
                if (method != null)
                {
                    accessible = !method.isPrivate();
                    if (!accessible)
                    {
                        accessible = isMethodAccessible(type.getSuperclass(), name, parameter);
                    }
                }
                else
                {
                    accessible = isMethodAccessible(type.getSuperclass(), name, parameter);
                }
            }
            catch (NotFoundException e)
            {
                accessible = isMethodAccessible(type.getSuperclass(), name, parameter);
            }
        }
        return accessible;
    }
}
