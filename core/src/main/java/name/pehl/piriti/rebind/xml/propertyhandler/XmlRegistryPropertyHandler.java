package name.pehl.piriti.rebind.xml.propertyhandler;

import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.AbstractRegistryPropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerRegistry;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * {@link PropertyHandler} implementation for types with an own
 * {@link XmlReader}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class XmlRegistryPropertyHandler extends AbstractRegistryPropertyHandler
{
    public XmlRegistryPropertyHandler(TreeLogger logger)
    {
        super(logger);
    }


    /**
     * TODO Javadoc
     * 
     * @param writer
     * @param propertyContext
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.xml.propertyhandler.ConverterPropertyHandler#readInput(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public void readInput(IndentedWriter writer, PropertyContext propertyContext,
            PropertyHandlerRegistry propertyHandlerRegistry) throws UnableToCompleteException
    {
        String readerVariable = startReader(writer, propertyContext, "xmlRegistry",
                propertyContext.getClassOrInterfaceType());

        writer.write("Node nestedNode = %s.selectNode(\"%s\");", propertyContext.getVariableNames().getInputVariable(),
                propertyContext.getPath());
        writer.write("if (nestedNode != null && nestedNode instanceof Element) {");
        writer.indent();
        writer.write("%s = %s.read((Element) nestedNode);", propertyContext.getVariableNames().getValueVariable(),
                readerVariable);
        writer.outdent();
        writer.write("}");

        endReaderWriter(writer);
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


    @Override
    protected String getReaderClassname()
    {
        return XmlReader.class.getName();
    }


    @Override
    protected String getWriterClassname()
    {
        return XmlWriter.class.getName();
    }
}
