package name.pehl.piriti.rebind;

/**
 * @author $Author$
 * @version $Date$ $Revision: 1135
 *          $
 */
public class Variables
{
    private static final String AS_STRING_SUFFIX = "AsString";

    private final int index;
    private final String value;


    public Variables(String value)
    {
        this(0, value);
    }


    private Variables(int index, String value)
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
        return newVariableName(AS_STRING_SUFFIX);
    }


    public String newVariableName(String suffix)
    {
        return getValue() + suffix;
    }


    Variables next()
    {
        return new Variables(index + 1, value);
    }
}
