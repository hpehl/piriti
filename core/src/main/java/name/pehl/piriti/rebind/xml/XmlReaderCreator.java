package name.pehl.piriti.rebind.xml;

import name.pehl.piriti.client.xml.XmlField;
import name.pehl.piriti.rebind.AbstractReaderCreator;
import name.pehl.piriti.rebind.FieldContext;
import name.pehl.piriti.rebind.FieldHandlerRegistry;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.TypeUtils;
import name.pehl.piriti.rebind.fieldhandler.FieldHandler;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.core.ext.typeinfo.JType;

/**
 * Class which generates the code necessary to map the annotated fields.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class XmlReaderCreator extends AbstractReaderCreator
{
    public XmlReaderCreator(GeneratorContext context, JClassType interfaceType, String implName,
            String readerClassname, TreeLogger logger) throws UnableToCompleteException
    {
        super(context, interfaceType, implName, readerClassname, logger);
    }


    @Override
    protected FieldHandlerRegistry setupFieldHandlerRegistry()
    {
        return new XmlFieldHandlerRegistry();
    }


    @Override
    protected void createImports(IndentedWriter writer) throws UnableToCompleteException
    {
        super.createImports(writer);
        writer.write("import com.google.gwt.xml.client.Document;");
        writer.write("import com.google.gwt.xml.client.Element;");
        writer.write("import name.pehl.piriti.client.xml.*;");
    }


    @Override
    protected void createMemberVariables(IndentedWriter writer) throws UnableToCompleteException
    {
        super.createMemberVariables(writer);
        writer.write("private XmlRegistry xmlRegistry;");
    }


    @Override
    protected void createConstructorBody(IndentedWriter writer)
    {
        super.createConstructorBody(writer);
        writer.write("this.xmlRegistry = XmlGinjector.INJECTOR.getXmlRegistry();");
        writer.write("this.xmlRegistry.register(%s.class, this);", modelType.getQualifiedSourceName());
    }


    @Override
    protected void createMethods(IndentedWriter writer) throws UnableToCompleteException
    {
        readFromDocument(writer);
        writer.newline();

        readFromElement(writer);
        writer.newline();

        readListFromDocument(writer);
        writer.newline();

        readListFromDocumentUsingXpath(writer);
        writer.newline();

        readListFromElement(writer);
        writer.newline();

        readListFromElementUsingXpath(writer);
        writer.newline();
        
        readListInternal(writer);
        writer.newline();
    }


    protected void readFromDocument(IndentedWriter writer) throws UnableToCompleteException
    {
        read(writer, "Document", "document");
    }


    protected void readFromElement(IndentedWriter writer) throws UnableToCompleteException
    {
        read(writer, "Element", "element");
    }


    protected void read(IndentedWriter writer, String xmlType, String xmlVariable) throws UnableToCompleteException
    {
        writer.write("public %s read(%s %s) {", modelType.getParameterizedQualifiedSourceName(), xmlType, xmlVariable);
        writer.indent();
        writer.write("%s model = null;", modelType.getParameterizedQualifiedSourceName());
        writer.write("if (%s != null) {", xmlVariable);
        writer.indent();
        writer.write("model = new %s();", modelType.getParameterizedQualifiedSourceName());
        processMappings(writer, xmlVariable);
        writer.outdent();
        writer.write("}");
        writer.write("return model;");
        writer.outdent();
        writer.write("}");
    }


    protected void readListFromDocument(IndentedWriter writer) throws UnableToCompleteException
    {
        readList(writer, "Document", "document");
    }


    protected void readListFromDocumentUsingXpath(IndentedWriter writer) throws UnableToCompleteException
    {
        readListUsingXpath(writer, "Document", "document");
    }


    protected void readListFromElement(IndentedWriter writer) throws UnableToCompleteException
    {
        readList(writer, "Element", "element");
    }


    protected void readListFromElementUsingXpath(IndentedWriter writer) throws UnableToCompleteException
    {
        readListUsingXpath(writer, "Element", "element");
    }


    protected void readList(IndentedWriter writer, String xmlType, String xmlVariable) throws UnableToCompleteException
    {
        writer.write("public List<%s> readList(%s %s) {", modelType.getParameterizedQualifiedSourceName(), xmlType,
                xmlVariable);
        writer.indent();
        writer.write("List<Element> elements = XPathUtils.getElements(%s);", xmlVariable);
        writer.write("return readListInternal(elements);");
        writer.outdent();
        writer.write("}");
    }


    protected void readListUsingXpath(IndentedWriter writer, String xmlType, String xmlVariable)
            throws UnableToCompleteException
    {
        writer.write("public List<%s> readList(%s %s, String xpath) {",
                modelType.getParameterizedQualifiedSourceName(), xmlType, xmlVariable);
        writer.indent();
        writer.write("List<Element> elements = XPathUtils.getElements(%s, xpath);", xmlVariable);
        writer.write("return readListInternal(elements);");
        writer.outdent();
        writer.write("}");
    }


    protected void readListInternal(IndentedWriter writer)
    {
        writer.write("private List<%s> readListInternal(List<Element> elements) {", modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("List<%1$s> models = new ArrayList<%1$s>();", modelType.getParameterizedQualifiedSourceName());
        writer.write("if (elements != null && !elements.isEmpty()) {");
        writer.indent();
        writer.write("for (Element element : elements) {");
        writer.indent();
        writer.write("%s model = read(element);", modelType.getParameterizedQualifiedSourceName());
        writer.write("if (model != null) {");
        writer.indent();
        writer.write("models.add(model);");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.write("return models;");
        writer.outdent();
        writer.write("}");
    }


    protected void processMappings(IndentedWriter writer, String xmlVariable) throws UnableToCompleteException
    {
        JField[] fields = modelType.getFields();
        if (fields != null && fields.length != 0)
        {
            int counter = 0;
            for (JField field : fields)
            {
                XmlField xmlField = field.getAnnotation(XmlField.class);
                if (xmlField != null)
                {
                    writer.newline();
                    String xpath = calculateXpath(field, xmlField);
                    FieldContext fieldContext = new FieldContext(context.getTypeOracle(), handlerRegistry, modelType,
                            field.getType(), field.getName(), xpath, xmlField.format(), xmlVariable, "value" + counter);
                    FieldHandler handler = handlerRegistry.findFieldHandler(fieldContext);
                    if (handler != null)
                    {
                        if (handler.isValid(writer, fieldContext))
                        {
                            handler.writeComment(writer, fieldContext);
                            handler.writeDeclaration(writer, fieldContext);
                            handler.writeConverterCode(writer, fieldContext);
                            handler.writeAssignment(writer, fieldContext);
                            counter++;
                        }
                    }
                    // TODO What to do if no handler was found?
                }
            }
        }
    }


    protected String calculateXpath(JField field, XmlField xmlField)
    {
        String xpath = xmlField.value();
        if (xpath == null || xpath.length() == 0)
        {
            xpath = field.getName();
            JType fieldType = field.getType();
            if (fieldType.isPrimitive() != null || TypeUtils.isBasicType(fieldType) || fieldType.isEnum() != null)
            {
                xpath += "/text()";
            }
        }
        return xpath;
    }
}
