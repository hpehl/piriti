package name.pehl.piriti.rebind.property;



public class Variables
{
    private static final String VALUE = "value";
    private static final String AS_STRING_SUFFIX = "AsString";
    private static int globalIndex = 0;

    private final int index;
    private final String value;


    public Variables()
    {
        this(VALUE);
    }


    public Variables(String value)
    {
        this.index = globalIndex++;
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
