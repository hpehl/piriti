package name.pehl.piriti.rebind.propertyhandler;

import java.util.HashMap;
import java.util.Map;

import name.pehl.piriti.rebind.TypeUtils;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JArrayType;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JEnumType;
import com.google.gwt.core.ext.typeinfo.JPrimitiveType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

/**
 * Class which contains information needed to generate code for the evaluation,
 * conversion and assignment of a field. An instance of this class is passed to
 * the {@link PropertyHandler}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
 */
public class PropertyContext
{
    private static final String AS_STRING_SUFFIX = "AsString";

    private final TypeOracle typeOracle;
    private final PropertyHandlerRegistry handlerRegistry;
    private final JClassType modelType;
    private final JType fieldType;
    private final JPrimitiveType primitiveType;
    private final String fieldName;
    private final String path;
    private final String format;
    private final boolean stripWsnl;
    private AssignmentType assignmentType;
    private AssignmentPolicy assignmentPolicy;
    private final String inputVariable;
    private final String valueVariable;
    private final String builderVariable;
    private final Map<String, Object> metadata;


    /**
     * Construct a new instance of this class
     * 
     * @param typeOracle
     *            The type oracle from the GWT generator API
     * @param handlerRegistry
     *            The handler registry for {@link PropertyHandler} lookup
     * @param modelType
     *            The model type
     * @param fieldType
     *            The field type
     * @param fieldName
     *            The name of the field
     * @param path
     *            The path information for the mapping
     * @param format
     *            The format
     * @param assignmentType
     *            Kind of assignment.
     * @param assignmentPolicy
     *            How to do the assignement.
     * @param inputVariable
     *            The name of the input variable
     * @param valueVariable
     *            The name of the value variable
     * @param builderVariable
     *            The name of the variable for the {@link StringBuilder} which
     *            is used for serialization
     * @throws UnableToCompleteException
     */
    public PropertyContext(TypeOracle typeOracle, PropertyHandlerRegistry handlerRegistry, JClassType modelType,
            JType fieldType, String fieldName, String path, String format, boolean stripWsnl,
            AssignmentType assignmentType, AssignmentPolicy assignmentPolicy, String inputVariable,
            String valueVariable, String builderVariable) throws UnableToCompleteException
    {
        // Types
        this.typeOracle = typeOracle;
        this.handlerRegistry = handlerRegistry;
        this.modelType = modelType;
        JPrimitiveType primitiveType = fieldType.isPrimitive();
        if (primitiveType != null) // isPrimitive() is not yet available!
        {
            try
            {
                // Use the boxed type for primitives
                this.fieldType = typeOracle.getType(primitiveType.getQualifiedBoxedSourceName());
                this.primitiveType = primitiveType;
            }
            catch (NotFoundException e)
            {
                throw new UnableToCompleteException();
            }
        }
        else
        {
            this.fieldType = fieldType;
            this.primitiveType = null;
        }

        // Field properties
        this.fieldName = fieldName;
        this.path = path;
        if (format == null || format.length() == 0)
        {
            this.format = null;
        }
        else
        {
            this.format = format;
        }
        this.stripWsnl = stripWsnl;
        this.assignmentType = assignmentType;
        this.assignmentPolicy = assignmentPolicy;

        // Variable names
        this.inputVariable = inputVariable;
        this.valueVariable = valueVariable;
        this.builderVariable = builderVariable;

        this.metadata = new HashMap<String, Object>();
    }


    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder().append(fieldType.getParameterizedQualifiedSourceName()).append(" ")
                .append(fieldName).append(", path=\"").append(path).append("\"");
        if (format != null)
        {
            builder.append(", format=\"").append(format).append("\"");
        }
        return builder.toString();
    }


    public boolean isPrimitive()
    {
        return getPrimitiveType() != null;
    }


    public JPrimitiveType getPrimitiveType()
    {
        return primitiveType;
    }


    public boolean isBasicType()
    {
        return isPrimitive() || TypeUtils.isBasicType(fieldType);
    }


    public boolean isEnum()
    {
        return getEnumType() != null;
    }


    public JEnumType getEnumType()
    {
        return fieldType.isEnum();
    }


    public boolean isClassOrInterface()
    {
        return getClassOrInterfaceType() != null;
    }


    public JClassType getClassOrInterfaceType()
    {
        return fieldType.isClass();
    }


    public boolean isArray()
    {
        return getArrayType() != null;
    }


    public JArrayType getArrayType()
    {
        return fieldType.isArray();
    }


    public boolean isCollection()
    {
        return TypeUtils.isCollection(fieldType);
    }


    public TypeOracle getTypeOracle()
    {
        return typeOracle;
    }


    public PropertyHandlerRegistry getHandlerRegistry()
    {
        return handlerRegistry;
    }


    public JClassType getModelType()
    {
        return modelType;
    }


    public JType getFieldType()
    {
        return fieldType;
    }


    public String getFieldName()
    {
        return fieldName;
    }


    public String getPath()
    {
        return path;
    }


    public String getFormat()
    {
        return format;
    }


    public boolean isStripWsnl()
    {
        return stripWsnl;
    }


    public AssignmentType getAssignmentType()
    {
        return assignmentType;
    }


    public AssignmentPolicy getAssignmentPolicy()
    {
        return assignmentPolicy;
    }


    public String getInputVariable()
    {
        return inputVariable;
    }


    public String getValueVariable()
    {
        return valueVariable;
    }


    public String getValueAsStringVariable()
    {
        return newVariableName(AS_STRING_SUFFIX);
    }


    public String newVariableName(String suffix)
    {
        return getValueVariable() + suffix;
    }


    public String getBuilderVariable()
    {
        return builderVariable;
    }


    public <T> void addMetadata(String key, T value)
    {
        metadata.put(key, value);
    }


    @SuppressWarnings("unchecked")
    public <T> T getMetadata(String key)
    {
        return (T) metadata.get(key);
    }
}
