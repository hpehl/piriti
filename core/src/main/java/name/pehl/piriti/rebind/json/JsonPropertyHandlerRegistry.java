package name.pehl.piriti.rebind.json;

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

import name.pehl.piriti.rebind.json.propertyhandler.ArrayPropertyHandler;
import name.pehl.piriti.rebind.json.propertyhandler.BooleanPropertyHandler;
import name.pehl.piriti.rebind.json.propertyhandler.CollectionPropertyHandler;
import name.pehl.piriti.rebind.json.propertyhandler.ConverterPropertyHandler;
import name.pehl.piriti.rebind.json.propertyhandler.EnumPropertyHandler;
import name.pehl.piriti.rebind.json.propertyhandler.JsonRegistryPropertyHandler;
import name.pehl.piriti.rebind.json.propertyhandler.NumberPropertyHandler;
import name.pehl.piriti.rebind.json.propertyhandler.StringPropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerRegistry;

import com.google.gwt.core.ext.typeinfo.JPrimitiveType;

/**
 * {@link PropertyHandlerRegistry} used by the {@link JsonWriterCreator}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 136 $
 */
public abstract class JsonPropertyHandlerRegistry implements PropertyHandlerRegistry
{
    protected Map<String, PropertyHandler> registry;


    /**
     * Construct a new instance of this class and registers the initial field
     * handlers.
     */
    public JsonPropertyHandlerRegistry()
    {
        registry = new HashMap<String, PropertyHandler>();
        registerInitialFieldHandlers();
    }


    /**
     * Registers the initial field handler for the json reader. The following
     * handlers are registered:
     * <ul>
     * <li>{@linkplain BooleanPropertyHandler}
     * <ul>
     * <li>boolean, Boolean
     * </ul>
     * <li>{@link NumberPropertyHandler}
     * <ul>
     * <li>byte, Byte
     * <li>short, Short
     * <li>int, Integer
     * <li>long, Long
     * <li>float, Float
     * <li>double, Double
     * </ul>
     * <li>{@link ConverterPropertyHandler}
     * <ul>
     * <li>char, Character
     * <li>Date
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
    protected void registerInitialFieldHandlers()
    {
        PropertyHandler handler = null;

        // Boolean
        handler = newBooleanFieldHandler();
        registry.put(JPrimitiveType.BOOLEAN.getQualifiedSourceName(), handler);
        registry.put(JPrimitiveType.BOOLEAN.getQualifiedBoxedSourceName(), handler);

        // Numbers
        handler = newNumberFieldHandler();
        registry.put(JPrimitiveType.BYTE.getQualifiedSourceName(), handler);
        registry.put(JPrimitiveType.BYTE.getQualifiedBoxedSourceName(), handler);
        registry.put(JPrimitiveType.SHORT.getQualifiedSourceName(), handler);
        registry.put(JPrimitiveType.SHORT.getQualifiedBoxedSourceName(), handler);
        registry.put(JPrimitiveType.INT.getQualifiedSourceName(), handler);
        registry.put(JPrimitiveType.INT.getQualifiedBoxedSourceName(), handler);
        registry.put(JPrimitiveType.LONG.getQualifiedSourceName(), handler);
        registry.put(JPrimitiveType.LONG.getQualifiedBoxedSourceName(), handler);
        registry.put(JPrimitiveType.FLOAT.getQualifiedSourceName(), handler);
        registry.put(JPrimitiveType.FLOAT.getQualifiedBoxedSourceName(), handler);
        registry.put(JPrimitiveType.DOUBLE.getQualifiedSourceName(), handler);
        registry.put(JPrimitiveType.DOUBLE.getQualifiedBoxedSourceName(), handler);

        // Characters and dates are handled by the ConverterFieldHandler
        handler = newConverterFieldHandler();
        registry.put(JPrimitiveType.CHAR.getQualifiedSourceName(), handler);
        registry.put(JPrimitiveType.CHAR.getQualifiedBoxedSourceName(), handler);
        registry.put(Date.class.getName(), handler);

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


    protected PropertyHandler newBooleanFieldHandler()
    {
        return new BooleanPropertyHandler();
    }


    protected PropertyHandler newNumberFieldHandler()
    {
        return new NumberPropertyHandler();
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
        return new JsonRegistryPropertyHandler();
    }
}
