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
    private final String inputType;
    private final String inputVariable;
    private final String readerType;
    private final String writerType;
    private final String registryType;
    private final String registryVariable;
    private final String builderVariable;


    public VariableNames(String valueVariable, String inputType, String inputVariable, String readerType,
            String writerType, String registryType, String registryVariable, String builderVariable)
    {
        this(0, DEFAULT_INSTANCE_VARIABLE, valueVariable, inputType, inputVariable, readerType, writerType,
                registryType, registryVariable, builderVariable);
    }


    private VariableNames(int index, String instanceVariable, String valueVariable, String inputType,
            String inputVariable, String readerType, String writerType, String registryType, String registryVariable,
            String builderVariable)
    {
        this.index = index;
        this.instanceVariable = instanceVariable;
        this.valueVariable = valueVariable;
        this.inputType = inputType;
        this.inputVariable = inputVariable;
        this.readerType = readerType;
        this.writerType = writerType;
        this.registryType = registryType;
        this.registryVariable = registryVariable;
        this.builderVariable = builderVariable;
    }


    public String getInstanceVariable()
    {
        return instanceVariable;
    }


    public String getInputType()
    {
        return inputType;
    }


    public String getInputVariable()
    {
        return inputVariable;
    }


    public String getReaderType()
    {
        return readerType;
    }


    public String getWriterType()
    {
        return writerType;
    }


    public String getRegistryType()
    {
        return registryType;
    }


    public String getRegistryVariable()
    {
        return registryVariable;
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
        return new VariableNames(index + 1, instanceVariable, valueVariable, inputType, inputVariable, readerType,
                writerType, registryType, registryVariable, builderVariable);
    }
}
