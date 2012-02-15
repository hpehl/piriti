package name.pehl.piriti.rebind.type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import name.pehl.piriti.commons.client.CreateWith;
import name.pehl.piriti.commons.client.InstanceCreator;
import name.pehl.piriti.commons.client.MapUpTo;
import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.rebind.GeneratorContextHolder;
import name.pehl.piriti.rebind.property.PropertyContext;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

/**
 * Holds information about the reader / writer type, the type to be
 * (de)serialized and its properties, references and id.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class TypeContext
{
    // -------------------------------------------------------------- constants

    private static int internalOrder = Integer.MAX_VALUE / 2;

    // -------------------------------------------------------- private members

    private final JClassType type;
    private final JClassType rwType;
    private JClassType instanceCreator;
    private JClassType stopAt;
    private PropertyContext id;
    private final Map<String, PropertyContext> properties;
    private final Map<String, PropertyContext> references;


    // ----------------------------------------------------------- constructors

    /**
     * Construct a new instance of this class
     * 
     * @param type
     *            The type of the class
     * @param rwType
     *            The type of the reader or writer interface
     */
    public TypeContext(JClassType type, JClassType rwType)
    {
        TypeOracle typeOracle = GeneratorContextHolder.get().getContext().getTypeOracle();

        this.type = type;
        this.rwType = rwType;

        this.references = new HashMap<String, PropertyContext>();
        this.properties = new HashMap<String, PropertyContext>();

        // For instanceCreator and stopAt evaluate
        // 1. the type and
        // 2. the rwType
        this.instanceCreator = null;
        if (this.type.isAnnotationPresent(CreateWith.class))
        {
            Class<? extends InstanceCreator<?, ?>> clazz = this.type.getAnnotation(CreateWith.class).value();
            this.instanceCreator = typeOracle.findType(clazz.getName());
        }
        if (this.rwType.isAnnotationPresent(CreateWith.class))
        {
            Class<? extends InstanceCreator<?, ?>> clazz = this.rwType.getAnnotation(CreateWith.class).value();
            this.instanceCreator = typeOracle.findType(clazz.getName());
        }

        this.stopAt = null;
        if (this.type.isAnnotationPresent(MapUpTo.class))
        {
            this.stopAt = typeOracle.findType(this.type.getAnnotation(MapUpTo.class).value().getName()).getSuperclass();
        }
        if (this.rwType.isAnnotationPresent(MapUpTo.class))
        {
            this.stopAt = typeOracle.findType(this.rwType.getAnnotation(MapUpTo.class).value().getName())
                    .getSuperclass();
        }
        if (this.stopAt == null)
        {
            this.stopAt = typeOracle.findType(Object.class.getName());
        }
        if (stopAt == null)
        {
            throw new AssertionError("Type info for java.lang.Object not found!");
        }
    }


    // ---------------------------------------------------------------- methods

    public static int nextOrder()
    {
        return internalOrder++;
    }


    @Override
    public String toString()
    {
        return new StringBuilder().append("TypeContext [").append(rwType.getParameterizedQualifiedSourceName())
                .append(" for ").append(type.getParameterizedQualifiedSourceName()).append("]").toString();
    }


    // -------------------------------------- methods related to the class type

    public boolean isJson()
    {
        Set<? extends JClassType> hierarchy = rwType.getFlattenedSupertypeHierarchy();
        for (JClassType type : hierarchy)
        {
            if (JsonReader.class.getName().equals(type.getQualifiedSourceName())
                    || JsonWriter.class.getName().equals(type.getQualifiedSourceName()))
            {
                return true;
            }
        }
        return false;
    }


    public boolean isXml()
    {
        Set<? extends JClassType> hierarchy = rwType.getFlattenedSupertypeHierarchy();
        for (JClassType type : hierarchy)
        {
            if (XmlReader.class.getName().equals(type.getQualifiedSourceName())
                    || XmlWriter.class.getName().equals(type.getQualifiedSourceName()))
            {
                return true;
            }
        }
        return false;
    }


    // ------------------------------------------------------------ collections

    public void addProperty(PropertyContext propertyContext)
    {
        properties.put(propertyContext.getName(), propertyContext);
    }


    public PropertyContext removeProperty(String property)
    {
        if (property != null)
        {
            return properties.remove(property);
        }
        return null;
    }


    public void addReference(PropertyContext propertyContext)
    {
        references.put(propertyContext.getName(), propertyContext);

        // Prevent duplicate processing
        removeProperty(propertyContext.getName());
    }


    public PropertyContext removeReference(String property)
    {
        if (property != null)
        {
            return references.remove(property);
        }
        return null;
    }


    // ------------------------------------------------------------- properties

    /**
     * The type of the POJO to be (de)serialized.
     * 
     * @return the type of the POJO to be (de)serialized
     */
    public JClassType getType()
    {
        return type;
    }


    public JClassType getRwType()
    {
        return rwType;
    }


    /**
     * @return <code>true</code> if {@link #getRwType()} references a
     *         {@link JsonReader} or {@link XmlReader}, <code>false</code>
     *         otherwise.
     */
    public boolean isReader()
    {
        boolean reader = false;
        Set<? extends JClassType> hierarchy = rwType.getFlattenedSupertypeHierarchy();
        if (hierarchy != null)
        {
            for (JClassType h : hierarchy)
            {
                if (h.getQualifiedSourceName().equals(JsonReader.class.getName())
                        || h.getQualifiedSourceName().equals(XmlReader.class.getName()))
                {
                    return true;
                }
            }
        }
        return reader;
    }


    /**
     * @return <code>true</code> if {@link #getRwType()} references a
     *         {@link JsonWriter} or {@link XmlWriter}, <code>false</code>
     *         otherwise.
     */
    public boolean isWriter()
    {
        boolean writer = false;
        Set<? extends JClassType> hierarchy = rwType.getFlattenedSupertypeHierarchy();
        if (hierarchy != null)
        {
            for (JClassType h : hierarchy)
            {
                if (h.getQualifiedSourceName().equals(JsonWriter.class.getName())
                        || h.getQualifiedSourceName().equals(XmlWriter.class.getName()))
                {
                    return true;
                }
            }
        }
        return writer;
    }


    public JClassType getInstanceCreator()
    {
        return instanceCreator;
    }


    public JClassType getStopAt()
    {
        return stopAt;
    }


    public PropertyContext getId()
    {
        return id;
    }


    public void setId(PropertyContext propertyContext)
    {
        this.id = propertyContext;

        if (propertyContext != null)
        {
            // Prevent duplicate processing
            removeProperty(propertyContext.getName());
        }
    }


    public List<PropertyContext> getProperties()
    {
        List<PropertyContext> ordered = new ArrayList<PropertyContext>(properties.values());
        Collections.sort(ordered, new PropertyContext.PropertyContextOrder());
        return ordered;
    }


    public List<PropertyContext> getReferences()
    {
        List<PropertyContext> ordered = new ArrayList<PropertyContext>(references.values());
        Collections.sort(ordered, new PropertyContext.PropertyContextOrder());
        return ordered;
    }
}
