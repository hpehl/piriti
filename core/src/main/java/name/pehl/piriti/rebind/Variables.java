package name.pehl.piriti.rebind;

/**
 * @author $Author$
 * @version $Date$ $Revision: 1135
 *          $
 */
public class Variables
{
    private static final String VALUE = "value";
    private static final String AS_STRING_SUFFIX = "AsString";

    private final int index;
    private final String value;


    public Variables(int index)
    {
        this(index, VALUE);
    }


    public Variables(int index, String value)
    {
        this.index = index;
        this.value = value;
    }


    public String getValue()
    {
        return value + index;
    }


    public String getValueAsString()
    {
        return newVariable(AS_STRING_SUFFIX);
    }


    public String newVariable(String suffix)
    {
        return getValue() + suffix;
    }
}
