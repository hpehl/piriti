package name.pehl.piriti.rebind.xml.propertyhandler;

import name.pehl.piriti.commons.client.WhitespaceHandling;
import name.pehl.piriti.converter.client.Converter;
import name.pehl.piriti.converter.client.ConverterRegistry;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.AbstractConverterPropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerRegistry;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * {@link PropertyHandler} implementation which uses a {@link Converter} from
 * the {@link ConverterRegistry}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class ConverterPropertyHandler extends AbstractConverterPropertyHandler
{
    public ConverterPropertyHandler(TreeLogger logger)
    {
        super(logger);
    }


    /**
     * Reads the string value using
     * <code>XPath.getValue(&lt;element&gt;, &lt;xpath&gt;)</code>
     * 
     * @param writer
     * @param propertyContext
     * @see name.pehl.piriti.rebind.propertyhandler.AbstractConverterPropertyHandler#readInputAsString(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    protected void readInputAsString(IndentedWriter writer, PropertyContext propertyContext)
    {
        writer.write("String %s = %s.selectValue(\"%s\", %s);", propertyContext.getVariableNames()
                .getValueAsStringVariable(), propertyContext.getVariableNames().getInputVariable(), propertyContext
                .getPath(), propertyContext.getWhitespaceHandling() == WhitespaceHandling.REMOVE);
    }


    @Override
    public void markupStart(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        writer.write("// markupStart() NYI");
    }


    @Override
    public void writeValue(IndentedWriter writer, PropertyContext propertyContext,
            PropertyHandlerRegistry propertyHandlerRegistry) throws UnableToCompleteException
    {
        writer.write("// writeValue() NYI");
    }


    @Override
    public void markupEnd(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        writer.write("// markupEnd() NYI");
    }
}
