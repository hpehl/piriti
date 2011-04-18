package name.pehl.piriti.rebind.xml.propertyhandler;

import java.util.logging.Level;

import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerLookup;
import name.pehl.piriti.xml.client.XmlReader;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * {@link PropertyHandler} implementation for types with an own
 * {@link XmlReader}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class DefaultPropertyHandler extends AbstractXmlPropertyHandler
{
    public DefaultPropertyHandler(TreeLogger logger)
    {
        super(logger);
    }


    /**
     * Returns <code>true</code> if the properties type is a class or interface,
     * <code>false</code> otherwise.
     * 
     * @param writer
     * @param propertyContext
     * @return
     * @see name.pehl.piriti.rebind.propertyhandler.AbstractPropertyHandler#isValid(name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        if (!propertyContext.isClassOrInterface())
        {
            skipProperty(writer, propertyContext, "Type is no class or interface");
            return false;
        }
        CodeGeneration.readerWriterInitialization(writer, propertyContext.getClassOrInterfaceType());
        return true;
    }


    @Override
    public void readInput(IndentedWriter writer, PropertyContext propertyContext,
            PropertyHandlerLookup propertyHandlerLookup) throws UnableToCompleteException
    {
        String readerVariable = propertyContext.getVariableNames().newVariableName("Reader");
        writer.write("XmlReader<%s> %s = xmlRegistry.getReader(%s.class);", propertyContext.getType()
                .getParameterizedQualifiedSourceName(), readerVariable, propertyContext.getType()
                .getQualifiedSourceName());
        writer.write("if (%s != null) {", readerVariable);
        writer.indent();
        writer.write("Node nestedNode = %s.selectNode(\"%s\");", propertyContext.getVariableNames().getInputVariable(),
                propertyContext.getPathOrName());
        writer.write("if (nestedNode != null && nestedNode instanceof Element) {");
        writer.indent();
        writer.write("%s = %s.read((Element) nestedNode);", propertyContext.getVariableNames().getValueVariable(),
                readerVariable);
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.write("else {");
        writer.indent();
        super.readInput(writer, propertyContext, propertyHandlerLookup);
        writer.outdent();
        writer.write("}");
    }


    @Override
    public void writeValue(IndentedWriter writer, PropertyContext propertyContext,
            PropertyHandlerLookup propertyHandlerLookup) throws UnableToCompleteException
    {
        CodeGeneration.log(writer, Level.WARNING, "writeValue() NYI");
    }
}
