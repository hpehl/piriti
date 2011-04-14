package name.pehl.piriti.rebind.xml;

import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerRegistry;
import name.pehl.piriti.xml.client.XmlWriter;

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
    // --------------------------------------------------------- initialization

    public XmlWriterCreator(GeneratorContext generatorContext, JClassType rwType, String implName, String rwClassname,
            TreeLogger logger) throws UnableToCompleteException
    {
        super(generatorContext, rwType, implName, rwClassname, logger);
    }


    @Override
    protected PropertyHandlerRegistry setupPropertyHandlerLookup()
    {
        return new XmlPropertyHandlerRegistry(logger);
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
        writer.write("public String toXml(List<%s> models, String rootElement) {", typeContext.getType()
                .getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("String xml = null;");
        writer.write("if (models != null && rootElement != null) {");
        writer.indent();
        writer.write("StringBuilder xmlBuilder = new StringBuilder();");
        writer.write("xmlBuilder.append(\"<\");");
        writer.write("xmlBuilder.append(rootElement);");
        writer.write("xmlBuilder.append(\">\");");
        writer.write("for (%s model : models) {", typeContext.getType().getParameterizedQualifiedSourceName());
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
        writer.write("public String toXml(%s %s) {", typeContext.getType().getParameterizedQualifiedSourceName(),
                typeContext.getVariableNames().getInstanceVariable());
        writer.indent();
        writer.write("String xml = null;");
        writer.write("if (model != null) {");
        writer.indent();
        writer.write("StringBuilder xmlBuilder = new StringBuilder();");

        // This creates all FieldHandler / FieldContexts and calls handleField()
        // in a loop
        handleProperties(writer);

        writer.write("xml = xmlBuilder.toString();");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("return xml;");
        writer.write("}");
    }


    // ---------------------------------------------------- overwritten methods

    @Override
    protected void handleProperty(IndentedWriter writer, PropertyHandler fieldHandler, PropertyContext fieldContext,
            boolean hasNext) throws UnableToCompleteException
    {
        fieldHandler.log(writer, fieldContext);
        fieldHandler.declare(writer, fieldContext);
        fieldHandler.readProperty(writer, fieldContext);
        fieldHandler.markupStart(writer, fieldContext);
        fieldHandler.writeValue(writer, fieldContext, propertyHandlerRegistry);
        fieldHandler.markupEnd(writer, fieldContext);
    }
}
