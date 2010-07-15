package name.pehl.piriti.client.mapping;

import java.util.HashSet;
import java.util.Set;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class Mappings
{
    // -------------------------------------------------------- private members

    private Mapping id;
    private Set<Mapping> fields;
    private Set<Mapping> references;


    // ----------------------------------------------------------- constructors

    public Mappings()
    {
        fields = new HashSet<Mapping>();
        references = new HashSet<Mapping>();
    }


    // --------------------------------------------------------------------- id

    public Mappings id(String name)
    {
        id(name, null, true);
        return this;
    }


    public Mappings id(String name, String xpath)
    {
        id(name, xpath, true);
        return this;
    }


    public Mappings id(String name, String xpath, boolean stripWsNl)
    {
        id = new Mapping(name, xpath, null, stripWsNl);
        return this;
    }


    // ----------------------------------------------------------------- fields

    public Mappings field(String name)
    {
        field(name, null, null, true);
        return this;
    }


    public Mappings field(String name, String xpath)
    {
        field(name, xpath, null, true);
        return this;
    }


    public Mappings field(String name, String xpath, String format)
    {
        field(name, xpath, format, true);
        return this;
    }


    public Mappings field(String name, String xpath, String format, boolean stripWsNl)
    {
        fields.add(new Mapping(name, xpath, format, stripWsNl));
        return this;
    }


    // ------------------------------------------------------------- references

    public Mappings reference(String name)
    {
        reference(name, null, true);
        return this;
    }


    public Mappings reference(String name, String xpath)
    {
        reference(name, xpath, true);
        return this;
    }


    public Mappings reference(String name, String xpath, boolean stripWsNl)
    {
        references.add(new Mapping(name, xpath, null, stripWsNl));
        return this;
    }


    // --------------------------------------------------------- public methods

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Mappings [");
        if (id != null)
        {
            builder.append("id: ").append(id).append(", ");
        }
        builder.append("fields: ").append(fields);
        builder.append(", references: ").append(references);
        builder.append("]");
        return builder.toString();
    }


    // ------------------------------------------------------------- properties

    public Mapping getId()
    {
        return id;
    }


    public Set<Mapping> getFields()
    {
        return fields;
    }


    public Set<Mapping> getReferences()
    {
        return references;
    }
}
