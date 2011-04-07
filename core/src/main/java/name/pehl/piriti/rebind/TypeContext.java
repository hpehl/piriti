package name.pehl.piriti.rebind;

import java.util.HashSet;
import java.util.Set;

import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;
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
    // -------------------------------------------------------- private members

    private final TypeOracle typeOracle;
    private final JClassType rwType;
    private final JClassType type;

    private PropertyContext id;
    private final Set<PropertyContext> references;
    private final Set<PropertyContext> properties;

    private final VariableNames variableNames;


    // ----------------------------------------------------------- constructors

    /**
     * Construct a new instance of this class
     * 
     * @param typeOracle
     *            The type oracle from the GWT generator API
     * @param rwType
     *            The type of the reader or writer interface
     * @param type
     *            The type of the class
     */
    public TypeContext(TypeOracle typeOracle, JClassType rwType, JClassType type, VariableNames variableNames)
    {
        this.typeOracle = typeOracle;
        this.rwType = rwType;
        this.type = type;
        this.variableNames = variableNames;
        this.references = new HashSet<PropertyContext>();
        this.properties = new HashSet<PropertyContext>();
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


    /**
     * The type of the POJO to be (de)serialized.
     * 
     * @return the type of the POJO to be (de)serialized
     */
    public JClassType getType()
    {
        return type;
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
