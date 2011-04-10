package name.pehl.piriti.client.constraints;

import name.pehl.piriti.commons.client.Path;
import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import com.google.gwt.core.client.GWT;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */

public class Amoeba
{
    // --------------------------------------------------- json reader / writer

    // @formatter:off
    public interface AmoebaJsonWriter extends JsonWriter<Amoeba> {}
    public static final AmoebaJsonWriter JSON_WRITER = GWT.create(AmoebaJsonWriter.class);

    public interface AmoebaJsonReader extends JsonReader<Amoeba> {}
    public static final AmoebaJsonReader JSON_READER = GWT.create(AmoebaJsonReader.class);
    // @formatter:on

    // ---------------------------------------------------- xml reader / writer

    // @formatter:off
    public interface AmoebaXmlReader extends XmlReader<Amoeba> {}
    public static final AmoebaXmlReader XML_READER = GWT.create(AmoebaXmlReader.class);

    public interface AmoebaXmlWriter extends XmlWriter<Amoeba> {}
    public static final AmoebaXmlWriter XML_WRITER = GWT.create(AmoebaXmlWriter.class);
    // @formatter:on

    // ------------------------------------------------------------------- data

    String name;

    @Path("__size__")
    int size;


    public Amoeba()
    {
        this.name = "blueprint";
        this.size = 0;
    }


    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (name == null ? 0 : name.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        Amoeba other = (Amoeba) obj;
        if (name == null)
        {
            if (other.name != null)
            {
                return false;
            }
        }
        else if (!name.equals(other.name))
        {
            return false;
        }
        return true;
    }


    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Amoeba [name=").append(name).append("]");
        return builder.toString();
    }


    public String getName()
    {
        return name;
    }


    public void setName(String name)
    {
        this.name = name;
    }


    public int getSize()
    {
        return size;
    }


    public void setSize(int size)
    {
        this.size = size;
    }
}
