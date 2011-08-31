package name.pehl.piriti.rebind.xml;

import name.pehl.piriti.rebind.AbstractWriterCreator;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.VariableNames;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerLookup;
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
public class XmlWriterCreator extends AbstractWriterCreator
{
    private static final String XML_DECL = "<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?>";


    // --------------------------------------------------------- initialization

    public XmlWriterCreator(GeneratorContext generatorContext, JClassType rwType, String implName, String rwClassname,
            TreeLogger logger) throws UnableToCompleteException
    {
        super(generatorContext, rwType, implName, rwClassname, logger);
    }


    @Override
    protected VariableNames setupVariableNames()
    {
        return XmlUtils.newVariableNames();
    }


    @Override
    protected PropertyHandlerLookup setupPropertyHandlerLookup()
    {
        return XmlUtils.newPropertyHandlerLookup(logger);
    }


    // --------------------------------------------------------- create methods

    @Override
    protected void createImports(IndentedWriter writer) throws UnableToCompleteException
    {
        super.createImports(writer);
        XmlUtils.createImports(writer);
    }


    @Override
    protected void createConstructorBody(IndentedWriter writer) throws UnableToCompleteException
    {
        // Order is important!
        XmlUtils.createConstructorBody(writer);
        super.createConstructorBody(writer);
    }


    @Override
    protected void createWriterMethods(IndentedWriter writer) throws UnableToCompleteException
    {
        writeList(writer);
        writer.newline();

        writeListRootElement(writer);
        writer.newline();

        writeListRootElementNestedElement(writer);
        writer.newline();

        writeSingle(writer);
        writer.newline();

        writeSingleRootElement(writer);
        writer.newline();

        writeSinglePlain(writer);
    }


    // ---------------------------------------------------------- write methods

    protected void writeList(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public String toXml(List<%s> models) {", typeContext.getType()
                .getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("return toXml(models, \"%1$ss\", \"%1$s\");", buildRootElement(typeContext.getType()));
        writer.outdent();
        writer.write("}");
    }


    protected void writeListRootElement(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public String toXml(List<%s> models, String rootElement) {", typeContext.getType()
                .getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("return toXml(models, rootElement, \"%1$s\");", buildRootElement(typeContext.getType()));
        writer.outdent();
        writer.write("}");
    }


    protected void writeListRootElementNestedElement(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public String toXml(List<%s> models, String rootElement, String nestedRootElement) {",
                typeContext.getType().getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("String xml = null;");
        writer.write("if (models != null && rootElement != null && rootElement.length() != 0 && nestedRootElement != null && nestedRootElement.length() != 0) {");
        writer.indent();
        writer.write("StringBuilder %s = new StringBuilder();", typeContext.getVariableNames().getBuilderVariable());
        writer.write("%s.append(\"" + XML_DECL + "\");", typeContext.getVariableNames().getBuilderVariable());
        writer.write("%s.append(\"<\");", typeContext.getVariableNames().getBuilderVariable());
        writer.write("%s.append(rootElement);", typeContext.getVariableNames().getBuilderVariable());
        writer.write("%s.append(\">\");", typeContext.getVariableNames().getBuilderVariable());
        writer.write("for (%s %s : models) {", typeContext.getType().getParameterizedQualifiedSourceName(), typeContext
                .getVariableNames().getInstanceVariable());
        writer.indent();
        writer.write("String xmlValue = toXml(%s, nestedRootElement);", typeContext.getVariableNames()
                .getInstanceVariable());
        writer.write("if (xmlValue != null) {");
        writer.indent();
        writer.write("%s.append(xmlValue);", typeContext.getVariableNames().getBuilderVariable());
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.write("%s.append(\"</\");", typeContext.getVariableNames().getBuilderVariable());
        writer.write("%s.append(rootElement);", typeContext.getVariableNames().getBuilderVariable());
        writer.write("%s.append(\">\");", typeContext.getVariableNames().getBuilderVariable());
        writer.write("xml = %s.toString();", typeContext.getVariableNames().getBuilderVariable());
        writer.outdent();
        writer.write("}");
        writer.write("return xml;");
        writer.outdent();
        writer.write("}");
    }


    protected void writeSingle(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public String toXml(%s %s) {", typeContext.getType().getParameterizedQualifiedSourceName(),
                typeContext.getVariableNames().getInstanceVariable());
        writer.indent();
        writer.write("return toXml(%s, \"%s\");", typeContext.getVariableNames().getInstanceVariable(),
                buildRootElement(typeContext.getType()));
        writer.outdent();
        writer.write("}");
    }


    protected void writeSingleRootElement(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public String toXml(%s %s, String rootElement) {", typeContext.getType()
                .getParameterizedQualifiedSourceName(), typeContext.getVariableNames().getInstanceVariable());
        writer.indent();
        writer.write("String xml = null;");
        writer.write("if (%s != null && rootElement != null && rootElement.length() != 0) {", typeContext
                .getVariableNames().getInstanceVariable());
        writer.indent();
        writer.write("WriteModelEvent.fire(this, %s);", typeContext.getVariableNames().getInstanceVariable());
        writer.write("StringBuilder %s = new StringBuilder();", typeContext.getVariableNames().getBuilderVariable());
        writer.write("%s.append(\"" + XML_DECL + "\");", typeContext.getVariableNames().getBuilderVariable());
        writer.write("%s.append(\"<\");", typeContext.getVariableNames().getBuilderVariable());
        writer.write("%s.append(rootElement);", typeContext.getVariableNames().getBuilderVariable());
        writer.write("%s.append(\">\");", typeContext.getVariableNames().getBuilderVariable());

        writer.write("%s.append(toPlainXml(%s));", typeContext.getVariableNames().getBuilderVariable(), typeContext
                .getVariableNames().getInstanceVariable());

        writer.write("%s.append(\"</\");", typeContext.getVariableNames().getBuilderVariable());
        writer.write("%s.append(rootElement);", typeContext.getVariableNames().getBuilderVariable());
        writer.write("%s.append(\">\");", typeContext.getVariableNames().getBuilderVariable());
        writer.write("xml = %s.toString();", typeContext.getVariableNames().getBuilderVariable());
        writer.outdent();
        writer.write("}");
        writer.write("return xml;");
        writer.outdent();
        writer.write("}");
    }


    protected void writeSinglePlain(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public String toPlainXml(%s %s) {", typeContext.getType().getParameterizedQualifiedSourceName(),
                typeContext.getVariableNames().getInstanceVariable());
        writer.indent();
        writer.write("String xml = null;");
        writer.write("if (model != null) {");
        writer.indent();
        writer.write("StringBuilder %s = new StringBuilder();", typeContext.getVariableNames().getBuilderVariable());

        // This creates all FieldHandler / FieldContexts and calls handleField()
        // in a loop
        handleProperties(writer);

        writer.write("xml = %s.toString();", typeContext.getVariableNames().getBuilderVariable());
        writer.outdent();
        writer.write("}");
        writer.write("return xml;");
        writer.outdent();
        writer.write("}");
    }


    // --------------------------------------------------------- helper methods

    private String buildRootElement(JClassType type)
    {
        String name = type.getSimpleSourceName();
        String varName = name.substring(0, 1).toLowerCase() + name.substring(1);
        return varName;
    }
}
