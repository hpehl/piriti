package name.pehl.gwt.piriti.rebind;

/**
 * Base class for all {@linkplain FieldHandler}s which contains common code.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public abstract class AbstractFieldHandler implements FieldHandler
{
    /**
     * Generated a comment for the field assignement containing the fields name,
     * type and the relevant xpath
     * 
     * @param writer
     * @param fieldContext
     */
    void writeComment(IndentedWriter writer, FieldContext fieldContext)
    {
        writer.write("// Handle %s", fieldContext);
    }


    /**
     * Generates the variable decleration for field assignment.
     * 
     * @param writer
     * @param fieldContext
     */
    void writeDeclaration(IndentedWriter writer, FieldContext fieldContext)
    {
        writer.write("%s %s = null;", fieldContext.getFieldType().getParameterizedQualifiedSourceName(), fieldContext
                .getValueVariable());
    }


    /**
     * Generates the code for converting the string read from XML to the fields
     * type using the ConverterRegistry.
     * 
     * @param writer
     * @param fieldContext
     */
    void writeConverterCode(IndentedWriter writer, FieldContext fieldContext)
    {
        writer.write("String %s = XPathUtils.getValue(%s, \"%s\");", fieldContext.getValueAsStringVariable(),
                fieldContext.getXmlVariable(), fieldContext.getXpath());
        writer.write("if (%s != null) {", fieldContext.getValueAsStringVariable());
        writer.indent();
        writer.write("Converter<%1$s> converter = converterRegistry.get(%1$s.class);", fieldContext.getFieldType()
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
    void writeAssignment(IndentedWriter writer, FieldContext fieldContext)
    {
        writer.write("if (%s != null) {", fieldContext.getValueVariable());
        writer.indent();
        writer.write("model.%s = %s;", fieldContext.getFieldName(), fieldContext.getValueVariable());
        writer.outdent();
        writer.write("}");
    }


    void skipField(IndentedWriter writer, FieldContext fieldContext, String reason)
    {
        writer.write("// Skipping field %s", fieldContext);
        writer.write("// " + reason);
    }
}
