package name.pehl.piriti.rebind.xml.fieldhandler;

import name.pehl.piriti.client.converter.Converter;
import name.pehl.piriti.client.converter.ConverterRegistry;
import name.pehl.piriti.rebind.FieldContext;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.fieldhandler.AbstractConverterFieldHandler;
import name.pehl.piriti.rebind.fieldhandler.FieldHandler;

/**
 * {@link FieldHandler} implementation which uses a {@link Converter} from the
 * {@link ConverterRegistry}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class ConverterFieldHandler extends AbstractConverterFieldHandler
{
    /**
     * Reads the string value using
     * <code>XPath.getValue(&lt;element&gt;, &lt;xpath&gt;)</code>
     * 
     * @param writer
     * @param fieldContext
     * @see name.pehl.piriti.rebind.fieldhandler.AbstractConverterFieldHandler#writeReadValueAsString(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.FieldContext)
     */
    @Override
    protected void writeReadValueAsString(IndentedWriter writer, FieldContext fieldContext)
    {
        writer.write("String %s = this.xpath.getValue(%s, \"%s\");", fieldContext.getValueAsStringVariable(),
                fieldContext.getInputVariable(), fieldContext.getPath());
    }
}
