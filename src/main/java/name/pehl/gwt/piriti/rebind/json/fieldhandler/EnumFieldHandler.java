package name.pehl.gwt.piriti.rebind.json.fieldhandler;

import name.pehl.gwt.piriti.rebind.FieldContext;
import name.pehl.gwt.piriti.rebind.IndentedWriter;
import name.pehl.gwt.piriti.rebind.fieldhandler.AbstractEnumFieldHandler;
import name.pehl.gwt.piriti.rebind.fieldhandler.FieldHandler;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * {@link FieldHandler} for enum types. This implementation reads the XML data
 * as string and tries to convert it using <code>enumType.valueOf(String)</code>
 * .
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class EnumFieldHandler extends AbstractEnumFieldHandler
{
    /**
     * TODO Javadoc
     * 
     * @param writer
     * @param fieldContext
     * @throws UnableToCompleteException
     * @see name.pehl.gwt.piriti.rebind.xml.fieldhandler.DefaultFieldHandler#writeConverterCode(name.pehl.gwt.piriti.rebind.IndentedWriter,
     *      name.pehl.gwt.piriti.rebind.FieldContext)
     */
    @Override
    public void writeConverterCode(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        writer.write("String %s = XPathUtils.getValue(%s, \"%s\");", fieldContext.getValueAsStringVariable(),
                fieldContext.getInputVariable(), fieldContext.getPath());
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
    }
}
