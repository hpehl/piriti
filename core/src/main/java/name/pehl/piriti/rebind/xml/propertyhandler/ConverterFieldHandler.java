package name.pehl.piriti.rebind.xml.propertyhandler;

import com.google.gwt.core.ext.UnableToCompleteException;

import name.pehl.piriti.client.converter.Converter;
import name.pehl.piriti.client.converter.ConverterRegistry;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.propertyhandler.AbstractConverterFieldHandler;
import name.pehl.piriti.rebind.propertyhandler.FieldContext;
import name.pehl.piriti.rebind.propertyhandler.FieldHandler;

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
     * @see name.pehl.piriti.rebind.propertyhandler.AbstractConverterFieldHandler#readInputAsString(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.propertyhandler.FieldContext)
     */
    @Override
    protected void readInputAsString(IndentedWriter writer, FieldContext fieldContext)
    {
        writer.write("String %s = %s.selectValue(\"%s\", %s);", fieldContext.getValueAsStringVariable(), fieldContext
                .getInputVariable(), fieldContext.getPath(), fieldContext.isStripWsnl());
    }


    @Override
    public void markupStart(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        writer.write("// markupStart() NYI");
    }


    @Override
    public void writeValue(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        writer.write("// writeValue() NYI");
    }


    @Override
    public void markupEnd(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        writer.write("// markupEnd() NYI");
    }
}
