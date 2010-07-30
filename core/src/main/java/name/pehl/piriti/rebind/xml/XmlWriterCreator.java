package name.pehl.piriti.rebind.xml;

import name.pehl.piriti.client.xml.XmlWriter;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.fieldhandler.FieldContext;
import name.pehl.piriti.rebind.fieldhandler.FieldHandler;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * Creator for {@linkplain XmlWriter}s.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class XmlWriterCreator extends AbstractXmlCreator
{
    // ----------------------------------------------------------- constructors

    public XmlWriterCreator(GeneratorContext context, JClassType interfaceType, String implName,
            String readerClassname, TreeLogger logger) throws UnableToCompleteException
    {
        super(context, interfaceType, implName, readerClassname, logger);
    }


    // --------------------------------------------------------- create methods

    @Override
    protected void createMethods(IndentedWriter writer) throws UnableToCompleteException
    {
        writeList(writer);
        writer.newline();

        writeSingle(writer);
        writer.newline();
    }


    // ---------------------------------------------------------- write methods

    protected void writeList(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public String toXml(List<%s> models, String rootElement) {",
                modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("String xml = null;");
        writer.write("if (models != null && rootElement != null) {");
        writer.indent();
        writer.write("StringBuilder xmlBuilder = new StringBuilder();");
        writer.write("xmlBuilder.append(\"<\");");
        writer.write("xmlBuilder.append(rootElement);");
        writer.write("xmlBuilder.append(\">\");");
        writer.write("for (%s model : models) {", modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("String xmlValue = toXml(model);");
        writer.write("if (xmlValue != null) {");
        writer.indent();
        writer.write("xmlBuilder.append(xmlValue);");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.write("xmlBuilder.append(\"</\");");
        writer.write("xmlBuilder.append(rootElement);");
        writer.write("xmlBuilder.append(\">\");");
        writer.write("xml = xmlBuilder.toString();");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("return xml;");
        writer.write("}");
    }


    protected void writeSingle(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public String toXml(%s model) {", modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("String xml = null;");
        writer.write("if (model != null) {");
        writer.indent();
        writer.write("StringBuilder xmlBuilder = new StringBuilder();");

        // This creates all FieldHandler / FieldContexts and calls handleField()
        // in a loop
        handleFields(writer);

        writer.write("xml = xmlBuilder.toString();");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("return xml;");
        writer.write("}");
    }


    // ---------------------------------------------------- overwritten methods

    @Override
    protected void handleField(IndentedWriter writer, FieldHandler fieldHandler, FieldContext fieldContext,
            boolean hasNext) throws UnableToCompleteException
    {
        fieldHandler.comment(writer, fieldContext);
        fieldHandler.declare(writer, fieldContext);
        fieldHandler.readField(writer, fieldContext);
        fieldHandler.markupStart(writer, fieldContext);
        fieldHandler.writeValue(writer, fieldContext);
        fieldHandler.markupEnd(writer, fieldContext);
    }
}
