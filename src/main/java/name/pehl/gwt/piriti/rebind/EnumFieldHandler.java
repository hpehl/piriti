package name.pehl.gwt.piriti.rebind;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * {@link FieldHandler} for enum types. This implementation reads the XML data
 * as string and tries to convert it using <code>enumType.valueOf(String)</code>
 * .
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class EnumFieldHandler extends AbstractFieldHandler
{
    /**
     * Returns <code>true</code> if the field type is an enum,
     * <code>false</code> otherwise.
     * 
     * @param writer
     * @param fieldContext
     * @return
     * @see name.pehl.gwt.piriti.rebind.AbstractFieldHandler#isValid(name.pehl.gwt.piriti.rebind.FieldContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, FieldContext fieldContext)
    {
        if (!fieldContext.isEnum())
        {
            skipField(writer, fieldContext, "Type is no enum");
        }
        return true;
    }


    @Override
    public void write(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        writeComment(writer, fieldContext);
        writeDeclaration(writer, fieldContext);
        writer.write("String %s = XPathUtils.getValue(%s, \"%s\");", fieldContext.getValueAsStringVariable(),
                fieldContext.getSourceVariable(), fieldContext.getXpath());
        writer.write("if (%s != null) {", fieldContext.getValueAsStringVariable());
        writer.indent();
        writer.write("try {");
        writer.indent();
        writer.write("%s = %s.valueOf(%s);", fieldContext.getValueVariable(), fieldContext.getEnumType()
                .getQualifiedSourceName(), fieldContext.getValueAsStringVariable());
        writer.outdent();
        writer.write("}");
        writer.write("catch (IllegalArgumentException e) {}");
        writer.outdent();
        writer.write("}");
        writeAssignment(writer, fieldContext);
    }
}
