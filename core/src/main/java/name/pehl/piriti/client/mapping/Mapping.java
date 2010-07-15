package name.pehl.piriti.client.mapping;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class Mapping
{
    private String name;
    private String xpath;
    private String format;
    private boolean stripWsNl;


    public Mapping(String name, String xpath, String format, boolean stripWsNl)
    {
        this.name = name;
        this.xpath = xpath;
        this.format = format;
        this.stripWsNl = stripWsNl;
    }


    /**
     * Based on {@code name}.
     * 
     * @return
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }


    /**
     * Based on {@code name}.
     * 
     * @param obj
     * @return
     * @see java.lang.Object#equals(java.lang.Object)
     */
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
        Mapping other = (Mapping) obj;
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


    /**
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Mapping [").append(name);
        if (xpath != null)
        {
            builder.append(", ").append(xpath);
        }
        if (format != null)
        {
            builder.append(", ").append(format);
        }
        if (!stripWsNl)
        {
            // as true is the default, only append if false
            builder.append(", ").append(stripWsNl);
        }
        builder.append("]");
        return builder.toString();
    }


    /**
     * @return the name.
     */
    public String getName()
    {
        return name;
    }


    /**
     * @return the xpath.
     */
    public String getXpath()
    {
        return xpath;
    }


    /**
     * @return the format.
     */
    public String getFormat()
    {
        return format;
    }


    /**
     * @return the stripWsNl.
     */
    public boolean isStripWsNl()
    {
        return stripWsNl;
    }
}
