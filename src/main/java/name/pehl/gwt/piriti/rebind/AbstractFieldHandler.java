package name.pehl.gwt.piriti.rebind;

import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JParameterizedType;

/**
 * Base class for all {@linkplain FieldHandler}s which contains common code.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public abstract class AbstractFieldHandler implements FieldHandler
{
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(FieldContext fieldContext)
    {
        boolean valid = true;

        // Same type as the model type is not allowed
        if (fieldContext.getModelType().equals(fieldContext.getType()))
        {
            valid = false;
        }

        // If the type is an array check the component type
        else if (fieldContext.isArray() && fieldContext.getModelType().equals(fieldContext.getArrayType().getComponentType()))
        {
            valid = false;
        }

        // In case of a collection / map check the type arguments
        else if (fieldContext.isCollection() || fieldContext.isMap())
        {
            JParameterizedType parameterizedType = fieldContext.getType().isParameterized();
            if (parameterizedType != null)
            {
                JClassType[] typeArgs = parameterizedType.getTypeArgs();
                for (JClassType typeArg : typeArgs)
                {
                    if (fieldContext.getModelType().equals(typeArg))
                    {
                        valid = false;
                        break;
                    }
                }
            }
            else
            {
                // collections and maps without type arguments are not
                // supported!
                valid = false;
            }
        }

        return valid;
    }
    
    /**
     * Generated a comment for the field assignement containing the fields name,
     * type and the relevant xpath
     * 
     * @param writer
     * @param fieldContext
     */
    protected void writeComment(IndentedWriter writer, FieldContext fieldContext)
    {
        writer.write("// Handle %s", fieldContext);
    }


    /**
     * Generates the variable decleration for field assignment.
     * 
     * @param writer
     * @param fieldContext
     */
    protected void writeDeclaration(IndentedWriter writer, FieldContext fieldContext)
    {
        writer.write("%s %s = null;", fieldContext.getType().getParameterizedQualifiedSourceName(), fieldContext
                .getValueVariable());
    }


    /**
     * Generates the code for converting the string read from XML to the fields
     * type using the ConverterRegistry.
     * 
     * @param writer
     * @param fieldContext
     */
    protected void writeConverterCode(IndentedWriter writer, FieldContext fieldContext)
    {
        writer.write("String %s = XPathUtils.getValue(%s, \"%s\");", fieldContext.getValueAsStringVariable(),
                fieldContext.getSourceVariable(), fieldContext.getXpath());
        writer.write("if (%s != null) {", fieldContext.getValueAsStringVariable());
        writer.indent();
        writer.write("Converter<%1$s> converter = converterRegistry.get(%1$s.class);", fieldContext.getType()
                .getQualifiedSourceName());
        writer.write("if (converter != null) {");
        writer.indent();
        if (fieldContext.getFormat() != null)
        {
            writer.write("%s = converter.convert(%s, \"%s\");", fieldContext.getValueVariable(), fieldContext
                    .getValueAsStringVariable(), fieldContext.getFormat());
        }
        else
        {
            writer.write("%s = converter.convert(%s, null);", fieldContext.getValueVariable(), fieldContext
                    .getValueAsStringVariable());
        }
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
    }


    /**
     * Generates the assignment for the field. The assignment is only done when
     * the xpath expression returns valid data (!= null).
     * 
     * @param writer
     * @param fieldContext
     */
    protected void writeAssignment(IndentedWriter writer, FieldContext fieldContext)
    {
        writer.write("if (%s != null) {", fieldContext.getValueVariable());
        writer.indent();
        writer.write("model.%s = %s;", fieldContext.getField().getName(), fieldContext.getValueVariable());
        writer.outdent();
        writer.write("}");
    }
}
