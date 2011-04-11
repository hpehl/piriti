package name.pehl.piriti.rebind;

/**
 * @author $Author$
 * @version $Date$ $Revision: 1135
 *          $
 */
public class VariableNames
{
    private static final String AS_STRING_SUFFIX = "AsString";
    private static final String DEFAULT_INSTANCE_VARIABLE = "model";

    private final int index;
    private final String valueVariable;
    private final String instanceVariable;
    private final String inputVariable;
    private final String builderVariable;


    public VariableNames(String valueVariable, String inputVariable, String builderVariable)
    {
        this(0, DEFAULT_INSTANCE_VARIABLE, valueVariable, inputVariable, builderVariable);
    }


    private VariableNames(int index, String instanceVariable, String valueVariable, String inputVariable,
            String builderVariable)
    {
        this.index = index;
        this.instanceVariable = instanceVariable;
        this.valueVariable = valueVariable;
        this.inputVariable = inputVariable;
        this.builderVariable = builderVariable;
    }


    public String getInstanceVariable()
    {
        return instanceVariable;
    }


    public String getInputVariable()
    {
        return inputVariable;
    }


    public String getValueVariable()
    {
        return valueVariable + index;
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


    VariableNames next()
    {
        return new VariableNames(index + 1, instanceVariable, valueVariable, inputVariable, builderVariable);
    }
}
