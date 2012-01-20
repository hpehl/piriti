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


    public Templates(String main)
    {
        this(main, null);
    }


    public Templates(String main, String elementType)
    {
        super();
        this.main = main;
        this.elementType = elementType;
    }


    public String getMain()
    {
        return main;
    }


    public String getElementType()
    {
        return elementType;
    }
}
