package name.pehl.piriti.rebind.propertyhandler;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public class VariableNames
{
    private static final String AS_STRING_SUFFIX = "AsString";

    private final String inputVariable;
    private final String valueVariable;
    private final String builderVariable;


    public VariableNames(String inputVariable, String valueVariable, String builderVariable)
    {
        this.inputVariable = inputVariable;
        this.valueVariable = valueVariable;
        this.builderVariable = builderVariable;
    }


    public String getInputVariable()
    {
        return inputVariable;
    }


    public String getValueVariable()
    {
        return valueVariable;
    }


    public String getBuilderVariable()
    {
        return builderVariable;
    }


    public String getValueAsStringVariable()
    {
        return newVariableName(AS_STRING_SUFFIX);
    }


    public String newVariableName(String suffix)
    {
        return getValueVariable() + suffix;
    }
}
