package name.pehl.piriti.rebind;

import name.pehl.piriti.rebind.fieldhandler.FieldHandler;

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
 * the {@link FieldHandler}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
 */
public class FieldContext
{
    private static final String AS_STRING_SUFFIX = "AsString";
    private static final String READER_SUFFIX = "Reader";

    private final TypeOracle typeOracle;
    private final FieldHandlerRegistry handlerRegistry;
    private final JClassType modelType;
    private final JType fieldType;
    private final String fieldName;
    private final String path;
    private final String format;
    private final String inputVariable;
    private final String valueVariable;
    private final String valueAsStringVariable;
    private final String valueReaderVariable;


    /**
     * Construct a new instance of this class
     * 
     * @param typeOracle
     *            The type oracle from the GWT generator API
     * @param handlerRegistry
     *            The handler registry for {@link FieldHandler} lookup
     * @param modelType
     *            The model type
     * @param fieldType
     *            The field type
     * @param fieldName
     *            The name of the field
     * @param path
     *            The path information which was specified in the annotation
     * @param format
     *            The format which was specified in the annotation
     * @param inputVariable
     *            The name of the input variable
     * @param valueVariable
     *            The name of the value variable
     * @throws UnableToCompleteException
     */
    public FieldContext(TypeOracle typeOracle, FieldHandlerRegistry handlerRegistry, JClassType modelType,
            JType fieldType, String fieldName, String path, String format, String inputVariable, String valueVariable)
            throws UnableToCompleteException
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
            }
            catch (NotFoundException e)
            {
                throw new UnableToCompleteException();
            }
        }
        else
        {
            this.fieldType = fieldType;
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

        // Variable names
        this.inputVariable = inputVariable;
        this.valueVariable = valueVariable;
        this.valueAsStringVariable = valueVariable + AS_STRING_SUFFIX;
        this.valueReaderVariable = valueVariable + READER_SUFFIX;
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
        return fieldType.isPrimitive();
    }


    public boolean isBasicType()
    {
        return TypeUtils.isBasicType(fieldType);
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


    public FieldHandlerRegistry getHandlerRegistry()
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
        return valueAsStringVariable;
    }


    public String getValueReaderVariable()
    {
        return valueReaderVariable;
    }
}
