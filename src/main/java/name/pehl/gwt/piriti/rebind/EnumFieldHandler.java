package name.pehl.gwt.piriti.rebind;

import com.google.gwt.core.ext.typeinfo.JEnumType;

/**
 * {@link FieldHandler} for enum types. This implementation reads the XML data
 * as string and tries to convert it using <code>enumType.valueOf(String)</code>
 * .
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class EnumFieldHandler extends AbstractFieldHandler
{
    @Override
    public void write(IndentedWriter writer, FieldContext fieldContext)
    {
        assert fieldContext.isEnum();
        JEnumType enumType = fieldContext.getEnumType();

        writeComment(writer, fieldContext);
        writeDeclaration(writer, fieldContext);
        writer.write("String %s = XPathUtils.getValue(%s, \"%s\");", fieldContext.getValueAsStringVariable(),
                fieldContext.getSourceVariable(), fieldContext.getXpath());
        writer.write("if (%s != null) {", fieldContext.getValueAsStringVariable());
        writer.indent();
        writer.write("try {");
        writer.indent();
        writer.write("%s = %s.valueOf(%s);", fieldContext.getValueVariable(), enumType.getQualifiedSourceName(),
                fieldContext.getValueAsStringVariable());
        writer.outdent();
        writer.write("}");
        writer.write("catch (IllegalArgumentException e) {}");
        writer.outdent();
        writer.write("}");
        writeAssignment(writer, fieldContext);
    }
}
