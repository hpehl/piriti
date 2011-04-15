package name.pehl.piriti.rebind;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
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

/**
 * Utility methods for {@linkplain JType}s.
 * 
 * @author $Author: harald.pehl $
 * @version $Date: 2010-01-14 17:11:02 +0100 (Do, 14. Jan 2010) $ $Revision: 54
 *          $
 */
public final class TypeUtils
{

    /**
     * Private constructor to ensure that the class acts as a true utility class
     * i.e. it isn't instantiable and extensible.
     */
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
            return primitive != null && primitive.equals(JPrimitiveType.BOOLEAN)
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
            return primitive != null && primitive.equals(JPrimitiveType.BYTE)
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
            return primitive != null && primitive.equals(JPrimitiveType.CHAR)
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
            return primitive != null && primitive.equals(JPrimitiveType.DOUBLE)
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
            return primitive != null && primitive.equals(JPrimitiveType.FLOAT)
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
            return primitive != null && primitive.equals(JPrimitiveType.INT)
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
            return primitive != null && primitive.equals(JPrimitiveType.LONG)
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
            return primitive != null && primitive.equals(JPrimitiveType.SHORT)
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
     * <li>byte or Byte
     * <li>short or Short
     * <li>int or Integer
     * <li>long or Long
     * <li>float or Float
     * <li>double or Double
     * </ul>
     * false otherwise.
     * 
     * @param type
     * @return
     */
    public static boolean isNumeric(JType type)
    {
        return isByte(type) || isShort(type) || isInteger(type) || isLong(type) || isFloat(type) || isDouble(type);
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
     * <li>LinkedHashSet
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
                || LinkedHashSet.class.getName().equals(type.getQualifiedSourceName())
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


    /**
     * Tries to find the given field with the given modifier in the given type
     * or in the supertypes of the given type. If {@code modifiers} is
     * <code>null</code>, the fields modifiers are not evaluated, otherwise the
     * field must have at least one of the specified modifiers.
     * 
     * @param type
     * @param property
     * @param modifier
     * @return the specified field or <code>null</code> if no such field was
     *         found.
     */
    public static JField findField(JClassType type, String property, Modifier... modifiers)
    {
        JField field = null;
        if (type != null && property != null && property.length() != 0)
        {
            field = type.getField(property);
            if (field != null)
            {
                boolean accessible = true;
                if (modifiers != null && modifiers.length != 0)
                {
                    accessible = false;
                    for (Modifier modifier : modifiers)
                    {
                        switch (modifier)
                        {
                            case PRIVATE:
                                accessible = field.isPrivate();
                                break;
                            case DEFAULT:
                                accessible = field.isDefaultAccess();
                                break;
                            case PROTECTED:
                                accessible = field.isProtected();
                                break;
                            case PUBLIC:
                                accessible = field.isPublic();
                                break;
                            default:
                                break;
                        }
                        if (accessible)
                        {
                            break;
                        }
                    }
                }
                if (!accessible)
                {
                    field = findField(type.getSuperclass(), property, modifiers);
                }
            }
            else
            {
                field = findField(type.getSuperclass(), property, modifiers);
            }
        }
        return field;
    }


    public static JMethod findGetter(JClassType type, String property, JType returnType, Modifier... modifiers)
    {
        JMethod getter = null;
        String[] methodNames = new String[] {"get" + property.substring(0, 1).toUpperCase() + property.substring(1),
                "is" + property.substring(0, 1).toUpperCase() + property.substring(1)};
        for (String methodName : methodNames)
        {
            getter = findMethod(type, returnType, methodName, new JType[] {}, modifiers);
            if (getter != null)
            {
                break;
            }
        }
        return getter;
    }


    public static JMethod findSetter(JClassType type, String property, JType parameter, Modifier... modifiers)
    {
        String methodName = "set" + property.substring(0, 1).toUpperCase() + property.substring(1);
        return findMethod(type, JPrimitiveType.VOID, methodName, new JType[] {parameter}, modifiers);
    }


    /**
     * Tries to find the given method with the given modifier in the given type
     * or in the supertypes of the given type. If {@code modifiers} is
     * <code>null</code>, the methods modifiers are not evaluated, otherwise the
     * method must have at least one of the specified modifiers.
     * 
     * @param type
     * @param returnType
     * @param name
     * @param parameter
     * @param modifiers
     * @return the specified method or <code>null</code> if no such method was
     *         found.
     */
    private static JMethod findMethod(JClassType type, JType returnType, String name, JType[] parameter,
            Modifier... modifiers)
    {
        JMethod method = null;
        if (type != null && returnType != null && name != null && name.length() != 0 && parameter != null)
        {
            method = type.findMethod(name, parameter);
            if (method != null && method.getReturnType() == returnType)
            {
                boolean accessible = true;
                if (modifiers != null && modifiers.length != 0)
                {
                    accessible = false;
                    for (Modifier modifier : modifiers)
                    {
                        switch (modifier)
                        {
                            case PRIVATE:
                                accessible = method.isPrivate();
                                break;
                            case DEFAULT:
                                accessible = method.isDefaultAccess();
                                break;
                            case PROTECTED:
                                accessible = method.isProtected();
                                break;
                            case PUBLIC:
                                accessible = method.isPublic();
                                break;
                            default:
                                break;
                        }
                        if (accessible)
                        {
                            break;
                        }
                    }
                }
                if (!accessible)
                {
                    method = findMethod(type.getSuperclass(), returnType, name, parameter, modifiers);
                }
            }
            else
            {
                method = findMethod(type.getSuperclass(), returnType, name, parameter, modifiers);
            }
        }
        return method;
    }
}
