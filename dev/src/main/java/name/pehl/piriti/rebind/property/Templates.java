package name.pehl.piriti.rebind.property;

public class Templates
{
    public static final Templates NO_TEMPLATES = new Templates("undefined");

    /**
     * Template for code generation
     */
    private final String main;

    /**
     * Template for the element type
     */
    private final String elementType;

    /**
     * Template for the value type
     */
    private final String valueType;


    public Templates(String main)
    {
        this(main, null);
    }


    public Templates(String main, String elementType)
    {
        this(main, elementType, null);
    }

    public Templates(String main, String elementType, String valueType)
    {
        super();
        this.main = main;
        this.elementType = elementType;
        this.valueType = valueType;
    }


    public String getMain()
    {
        return main;
    }


    public String getElementType()
    {
        return elementType;
    }

    public String getValueType() {
        return valueType;
    }
}
