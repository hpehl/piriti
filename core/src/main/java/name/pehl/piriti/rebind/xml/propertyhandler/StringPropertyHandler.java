package name.pehl.piriti.rebind.xml.propertyhandler;

import name.pehl.piriti.commons.client.WhitespaceHandling;
import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.AbstractPropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerRegistry;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * Simple {@link PropertyHandler} implementation for strings.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class StringPropertyHandler extends AbstractPropertyHandler
{
    public StringPropertyHandler(TreeLogger logger)
    {
        super(logger);
    }


    /**
     * Returns always <code>true</code>.
     * 
     * @param writer
     * @param propertyContext
     * @return always <code>true</code>
     * @see name.pehl.piriti.rebind.propertyhandler.AbstractPropertyHandler#isValid(name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        return true;
    }


    /**
     * TODO Javadoc
     * 
     * @param writer
     * @param propertyContext
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.propertyhandler.PropertyHandler#readInput(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public void readInput(IndentedWriter writer, PropertyContext propertyContext,
            PropertyHandlerRegistry propertyHandlerRegistry) throws UnableToCompleteException
    {
        if (propertyContext.useCustomConverter())
        {
            writer.write("String %s = %s.selectValue(\"%s\", %s);", propertyContext.getVariableNames()
                    .getValueAsStringVariable(), propertyContext.getVariableNames().getInputVariable(), propertyContext
                    .getPath(), propertyContext.getWhitespaceHandling() == WhitespaceHandling.REMOVE);
            CodeGeneration.useConverterForReading(writer, propertyContext);
        }
        else
        {
            writer.write("%s = %s.selectValue(\"%s\", %s);", propertyContext.getVariableNames().getValueVariable(),
                    propertyContext.getVariableNames().getInputVariable(), propertyContext.getPath(),
                    propertyContext.getWhitespaceHandling() == WhitespaceHandling.REMOVE);
        }
    }


    /**
     * @param writer
     * @param propertyContext
     * @see name.pehl.piriti.rebind.propertyhandler.AbstractPropertyHandler#readInputAsString(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.PropertyContext)
     */
    @Override
    protected void readInputAsString(IndentedWriter writer, PropertyContext propertyContext)
    {
        // TODO Implement me!
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
