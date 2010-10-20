package name.pehl.piriti.rebind.xml.propertyhandler;

import name.pehl.piriti.client.xml.XmlReader;
import name.pehl.piriti.client.xml.XmlWriter;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.propertyhandler.AbstractRegistryFieldHandler;
import name.pehl.piriti.rebind.propertyhandler.FieldContext;
import name.pehl.piriti.rebind.propertyhandler.FieldHandler;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * {@link FieldHandler} implementation for types with an own {@link XmlReader}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class XmlRegistryFieldHandler extends AbstractRegistryFieldHandler
{
    /**
     * TODO Javadoc
     * 
     * @param writer
     * @param fieldContext
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.xml.propertyhandler.ConverterFieldHandler#readInput(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.propertyhandler.FieldContext)
     */
    @Override
    public void readInput(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        String readerVariable = startReader(writer, fieldContext, "xmlRegistry", fieldContext.getClassOrInterfaceType());

        writer.write("Node nestedNode = %s.selectNode(\"%s\");", fieldContext.getInputVariable(),
                fieldContext.getPath());
        writer.write("if (nestedNode != null && nestedNode instanceof Element) {");
        writer.indent();
        writer.write("%s = %s.read((Element) nestedNode);", fieldContext.getValueVariable(), readerVariable);
        writer.outdent();
        writer.write("}");

        endReader(writer);
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
