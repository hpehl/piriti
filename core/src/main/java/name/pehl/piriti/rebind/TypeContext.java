package name.pehl.piriti.rebind;

import java.util.HashSet;
import java.util.Set;

import name.pehl.piriti.commons.client.CreateWith;
import name.pehl.piriti.commons.client.InstanceCreator;
import name.pehl.piriti.commons.client.MapUpTo;
import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
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
    // -------------------------------------------------------- private members

    private final TypeOracle typeOracle;
    private final JClassType type;
    private final JClassType rwType;
    private Class<? extends InstanceCreator<?, ?>> instanceCreator;
    private JClassType stopAt;
    private final Set<PropertyContext> properties;
    private PropertyContext id;
    private final Set<PropertyContext> references;
    private final VariableNames variableNames;


    // ----------------------------------------------------------- constructors

    /**
     * Construct a new instance of this class
     * 
     * @param typeOracle
     *            The type oracle from the GWT generator API
     * @param type
     *            The type of the class
     * @param rwType
     *            The type of the reader or writer interface
     * @throws UnableToCompleteException
     */
    public TypeContext(TypeOracle typeOracle, JClassType type, JClassType rwType, VariableNames variableNames)
            throws UnableToCompleteException
    {
        this.typeOracle = typeOracle;
        this.type = type;
        this.rwType = rwType;

        this.variableNames = variableNames;
        this.references = new HashSet<PropertyContext>();
        this.properties = new HashSet<PropertyContext>();

        // For instanceCreator and stopAt evaluate
        // 1. the type and
        // 2. the rwType
        try
        {
            this.instanceCreator = null;
            CreateWith createWith = this.type.getAnnotation(CreateWith.class);
            if (createWith != null)
            {
                this.instanceCreator = createWith.value();
            }
            createWith = this.rwType.getAnnotation(CreateWith.class);
            if (createWith != null)
            {
                this.instanceCreator = createWith.value();
            }

            this.stopAt = null;
            MapUpTo mapUpTo = this.type.getAnnotation(MapUpTo.class);
            if (mapUpTo != null)
            {
                this.stopAt = this.typeOracle.getType(mapUpTo.value().getName()).getSuperclass();
            }
            mapUpTo = this.rwType.getAnnotation(MapUpTo.class);
            if (mapUpTo != null)
            {
                this.stopAt = this.typeOracle.getType(mapUpTo.value().getName()).getSuperclass();
            }
            if (this.stopAt == null)
            {
                this.stopAt = this.typeOracle.getType(Object.class.getName());
            }
        }
        catch (NotFoundException e)
        {
            throw new UnableToCompleteException();
        }
    }


    public TypeContext clone(VariableNames variableNames) throws UnableToCompleteException
    {
        return new TypeContext(this.typeOracle, this.type, this.rwType, variableNames);
    }


    // --------------------------------------------------------- object methods

    @Override
    public String toString()
    {
        return new StringBuilder().append("TypeContext [").append(rwType.getParameterizedQualifiedSourceName())
                .append(" for ").append(type.getParameterizedQualifiedSourceName()).append("]").toString();
    }


    // -------------------------------------- methods related to the class type

    public boolean isGxt()
    {
        Set<? extends JClassType> hierarchy = type.getFlattenedSupertypeHierarchy();
        if (hierarchy != null)
        {
            for (JClassType h : hierarchy)
            {
                if (h.getQualifiedSourceName().equals("com.extjs.gxt.ui.client.data.Model"))
                {
                    return true;
                }
            }
        }
        return false;
    }


    // ------------------------------------------------------------ collections

    public void addReference(PropertyContext propertyContext)
    {
        references.add(propertyContext);
    }


    public void addProperty(PropertyContext propertyContext)
    {
        properties.add(propertyContext);
    }


    // ------------------------------------------------------------- properties

    public TypeOracle getTypeOracle()
    {
        return typeOracle;
    }


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


    public Class<? extends InstanceCreator<?, ?>> getInstanceCreator()
    {
        return instanceCreator;
    }


    public JClassType getStopAt()
    {
        return stopAt;
    }


    public VariableNames getVariableNames()
    {
        return variableNames;
    }


    public PropertyContext getId()
    {
        return id;
    }


    public void setId(PropertyContext id)
    {
        this.id = id;
    }


    public Set<PropertyContext> getProperties()
    {
        return properties;
    }


    public Set<PropertyContext> getReferences()
    {
        return references;
    }
}
