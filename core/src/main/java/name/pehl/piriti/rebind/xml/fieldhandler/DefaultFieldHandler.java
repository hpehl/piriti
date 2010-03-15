package name.pehl.piriti.rebind.xml.fieldhandler;

import name.pehl.piriti.rebind.FieldContext;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.fieldhandler.AbstractFieldHandler;
import name.pehl.piriti.rebind.fieldhandler.FieldHandler;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * Default implementation for a {@link FieldHandler}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class DefaultFieldHandler extends AbstractFieldHandler
{
    /**
     * Returns always <code>true</code>.
     * 
     * @param writer
     * @param fieldContext
     * @return always <code>true</code>
     * @see name.pehl.piriti.rebind.fieldhandler.AbstractFieldHandler#isValid(name.pehl.piriti.rebind.FieldContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        return true;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void writeConverterCode(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        writer.write("String %s = XPathUtils.getValue(%s, \"%s\");", fieldContext.getValueAsStringVariable(),
                fieldContext.getInputVariable(), fieldContext.getPath());
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
}
