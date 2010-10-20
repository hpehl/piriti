package name.pehl.piriti.rebind.xml.propertyhandler;

import com.google.gwt.core.ext.UnableToCompleteException;

import name.pehl.piriti.client.converter.Converter;
import name.pehl.piriti.client.converter.ConverterRegistry;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.propertyhandler.AbstractConverterPropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;

/**
 * {@link PropertyHandler} implementation which uses a {@link Converter} from the
 * {@link ConverterRegistry}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class ConverterFieldHandler extends AbstractConverterPropertyHandler
{
    /**
     * Reads the string value using
     * <code>XPath.getValue(&lt;element&gt;, &lt;xpath&gt;)</code>
     * 
     * @param writer
     * @param fieldContext
     * @see name.pehl.piriti.rebind.propertyhandler.AbstractConverterPropertyHandler#readInputAsString(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    protected void readInputAsString(IndentedWriter writer, PropertyContext fieldContext)
    {
        writer.write("String %s = %s.selectValue(\"%s\", %s);", fieldContext.getValueAsStringVariable(), fieldContext
                .getInputVariable(), fieldContext.getPath(), fieldContext.isStripWsnl());
    }


    @Override
    public void markupStart(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException
    {
        writer.write("// markupStart() NYI");
    }


    @Override
    public void writeValue(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException
    {
        writer.write("// writeValue() NYI");
    }


    @Override
    public void markupEnd(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException
    {
        writer.write("// markupEnd() NYI");
    }
}
