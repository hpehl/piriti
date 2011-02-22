package name.pehl.piriti.rebind.xml.propertyhandler;

import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.propertyhandler.AbstractPropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * Simple {@link PropertyHandler} implementation for strings.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class StringPropertyHandler extends AbstractPropertyHandler
{
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
    public void readInput(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        if (propertyContext.useCustomConverter())
        {
            writer.write("String %s = %s.selectValue(\"%s\", %s);", propertyContext.getVariableNames()
                    .getValueAsStringVariable(), propertyContext.getVariableNames().getInputVariable(), propertyContext
                    .getPath(), propertyContext.isStripWsnl());
            CodeGeneration.useConverterForReading(writer, propertyContext);
        }
        else
        {
            writer.write("%s = %s.selectValue(\"%s\", %s);", propertyContext.getVariableNames().getValueVariable(),
                    propertyContext.getVariableNames().getInputVariable(), propertyContext.getPath(),
                    propertyContext.isStripWsnl());
        }
    }


    @Override
    public void markupStart(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        writer.write("// markupStart() NYI");
    }


    @Override
    public void writeValue(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        writer.write("// writeValue() NYI");
    }


    @Override
    public void markupEnd(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        writer.write("// markupEnd() NYI");
    }
}
