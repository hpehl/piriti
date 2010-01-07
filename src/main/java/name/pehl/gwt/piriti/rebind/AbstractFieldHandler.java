package name.pehl.gwt.piriti.rebind;

public abstract class AbstractFieldHandler implements FieldHandler
{
    protected void writeComment(IndentedWriter writer, FieldContext fieldContext)
    {
        writer.write("// Process field %s: type %s, xpath \"%s\"", fieldContext.getField().getName(), fieldContext
                .getType().getParameterizedQualifiedSourceName(), fieldContext.getXpath());
    }


    protected void writeDeclaration(IndentedWriter writer, FieldContext fieldContext)
    {
        writer.write("%s %s = null;", fieldContext.getType().getParameterizedQualifiedSourceName(), fieldContext
                .getValueVariable());
    }


    protected void writeConverterCode(IndentedWriter writer, FieldContext fieldContext)
    {
        writer.write("String %s = XPathUtils.getValue(%s, \"%s\");", fieldContext.getValueAsStringVariable(),
                fieldContext.getSourceVariable(), fieldContext.getXpath());
        writer.write("if (%s != null) {", fieldContext.getValueAsStringVariable());
        writer.indent();
        writer.write("Converter<%1$s> converter = converterFactory.get(%1$s.class);", fieldContext.getType()
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


    protected void writeAssignment(IndentedWriter writer, FieldContext fieldContext)
    {
        writer.write("if (%s != null) {", fieldContext.getValueVariable());
        writer.indent();
        writer.write("model.%s = %s;", fieldContext.getField().getName(), fieldContext.getValueVariable());
        writer.outdent();
        writer.write("}");
    }
}
