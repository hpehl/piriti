package name.pehl.piriti.rebind.xml.propertyhandler;

import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
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
    protected void readInputUsingReader(IndentedWriter writer, PropertyContext propertyContext)
    {
        String selectedNodeVariable = propertyContext.getVariableNames().newVariableName("SelectedNode");
        writer.write("Node %s = %s.selectNode(\"%s\");", selectedNodeVariable, propertyContext.getVariableNames()
                .getInputVariable(), propertyContext.getPathOrName());
        writer.write("if (%1$s != null && %1$s instanceof Element) {", selectedNodeVariable);
        writer.indent();
        writer.write("%s = %s.read((Element) %s);", propertyContext.getVariableNames().getValueVariable(),
                readerVariable, selectedNodeVariable);
        writer.outdent();
        writer.write("}");
    }


    @Override
    protected void writeValueUsingWriter(IndentedWriter writer, PropertyContext propertyContext)
    {
        writer.write("%s.append(%s.toXml(%s));", propertyContext.getVariableNames().getBuilderVariable(),
                writerVariable, propertyContext.getVariableNames().getValueVariable());
    }
}
