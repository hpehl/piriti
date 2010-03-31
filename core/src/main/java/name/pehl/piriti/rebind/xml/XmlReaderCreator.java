package name.pehl.piriti.rebind.xml;

import name.pehl.piriti.client.xml.XmlField;
import name.pehl.piriti.client.xml.XmlId;
import name.pehl.piriti.client.xml.XmlIdRef;
import name.pehl.piriti.rebind.AbstractReaderCreator;
import name.pehl.piriti.rebind.AssignmentType;
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
    protected final FieldContext idFieldContext;


    public XmlReaderCreator(GeneratorContext context, JClassType interfaceType, String implName,
            String readerClassname, TreeLogger logger) throws UnableToCompleteException
    {
        super(context, interfaceType, implName, readerClassname, logger);
        this.idFieldContext = checkForIdField();
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
        super.createMethods(writer);

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

        internalReadList(writer);
        writer.newline();

        internalRead(writer);
        writer.newline();
    }


    protected void readFromDocument(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public %s read(Document document) {", modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("return read(document.getDocumentElement());");
        writer.outdent();
        writer.write("}");

    }


    protected void readFromElement(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public %s read(Element element) {", modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("if (element != null) {");
        writer.indent();
        writer.write("%1$s model = new %1$s();", modelType.getParameterizedQualifiedSourceName());
        writer.write("return internalRead(element, model);", modelType.getParameterizedQualifiedSourceName());
        writer.outdent();
        writer.write("}");
        writer.write("return null;");
        writer.outdent();
        writer.write("}");
    }


    protected void readListFromDocument(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public List<%s> readList(Document document) {", modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("return internalReadList(XPathUtils.getElements(document));");
        writer.outdent();
        writer.write("}");
    }


    protected void readListFromDocumentUsingXpath(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public List<%s> readList(Document document, String xpath) {", modelType
                .getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("return internalReadList(XPathUtils.getElements(document, xpath));");
        writer.outdent();
        writer.write("}");
    }


    protected void readListFromElement(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public List<%s> readList(Element element) {", modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("return internalReadList(XPathUtils.getElements(element));");
        writer.outdent();
        writer.write("}");
    }


    protected void readListFromElementUsingXpath(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public List<%s> readList(Element element, String xpath) {", modelType
                .getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("return internalReadList(XPathUtils.getElements(element, xpath));");
        writer.outdent();
        writer.write("}");
    }


    protected void internalReadList(IndentedWriter writer) throws UnableToCompleteException
    {
        boolean handleId = false;
        FieldHandler idFieldHandler = null;
        if (idFieldContext != null)
        {
            idFieldHandler = handlerRegistry.findFieldHandler(idFieldContext);
            handleId = idFieldHandler != null && idFieldHandler.isValid(writer, idFieldContext);
        }

        writer.write("private List<%s> internalReadList(List<Element> elements) {", modelType
                .getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("List<%1$s> models = new ArrayList<%1$s>();", modelType.getParameterizedQualifiedSourceName());
        writer.write("if (elements != null && !elements.isEmpty()) {");
        writer.indent();
        if (handleId)
        {
            // Create the model and put it in the idMap
            writer.write("for (Element element : elements) {");
            writer.indent();
            writer.write("if (element != null) {");
            writer.indent();
            writer.write("%1$s model = new %s();", modelType.getParameterizedQualifiedSourceName());
            idFieldHandler.writeComment(writer, idFieldContext);
            idFieldHandler.writeDeclaration(writer, idFieldContext);
            idFieldHandler.writeConverterCode(writer, idFieldContext);
            idFieldHandler.writeAssignment(writer, idFieldContext);
            writer.outdent();
            writer.write("}");
            writer.outdent();
            writer.write("}");
        }
        writer.write("for (Element element : elements) {");
        writer.indent();
        writer.write("if (element != null) {");
        writer.indent();
        if (handleId)
        {
            // Lookup the model from the idMap
            idFieldHandler.writeDeclaration(writer, idFieldContext);
            idFieldHandler.writeConverterCode(writer, idFieldContext);
            writer.write("%s model = this.idRef(%s);", modelType.getParameterizedQualifiedSourceName(), idFieldContext
                    .getValueVariable());
            writer.write("if (model != null) {");
            writer.indent();
            writer.write("model = internalRead(element, model);", modelType.getParameterizedQualifiedSourceName());
            writer.write("models.add(model);");
            writer.outdent();
            writer.write("}");
        }
        else
        {
            writer.write("%1$s model = new %1$s();", modelType.getParameterizedQualifiedSourceName());
            writer.write("model = internalRead(element, model);");
            writer.write("models.add(model);");
        }
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


    protected void internalRead(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("private %1$s internalRead(Element element, %1$s model) {", modelType
                .getParameterizedQualifiedSourceName());
        writer.indent();
        if (idFieldContext != null)
        {
            FieldHandler idFieldHandler = handlerRegistry.findFieldHandler(idFieldContext);
            if (idFieldHandler != null && idFieldHandler.isValid(writer, idFieldContext))
            {
                idFieldHandler.writeComment(writer, idFieldContext);
                idFieldHandler.writeDeclaration(writer, idFieldContext);
                idFieldHandler.writeConverterCode(writer, idFieldContext);
                idFieldHandler.writeAssignment(writer, idFieldContext);
            }
        }
        processMappings(writer);
        processIdRefs(writer);
        writer.write("return model;");
        writer.outdent();
        writer.write("}");
    }


    protected void processMappings(IndentedWriter writer) throws UnableToCompleteException
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
                    String xpath = calculateXpath(field, xmlField.value());
                    FieldContext fieldContext = new FieldContext(context.getTypeOracle(), handlerRegistry, modelType,
                            field.getType(), field.getName(), xpath, xmlField.format(), AssignmentType.MAPPING,
                            "element", "value" + counter);
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


    protected void processIdRefs(IndentedWriter writer) throws UnableToCompleteException
    {
        JField[] fields = modelType.getFields();
        if (fields != null && fields.length != 0)
        {
            int counter = 0;
            for (JField field : fields)
            {
                XmlIdRef xmlIdRef = field.getAnnotation(XmlIdRef.class);
                if (xmlIdRef != null)
                {
                    writer.newline();
                    String xpath = calculateXpath(field, xmlIdRef.value());
                    FieldContext fieldContext = new FieldContext(context.getTypeOracle(), handlerRegistry, modelType,
                            field.getType(), field.getName(), xpath, null, AssignmentType.IDREF, "element",
                            "idRefValue" + counter);
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
                }
            }
        }
    }


    protected String calculateXpath(JField field, String defaultValue)
    {
        String xpath = defaultValue;
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


    /**
     * Checks whether there's a field annotated with {@link XmlId} and returns
     * the relevant field context.
     * 
     * @return The field context for the field annotated with {@link XmlId} or
     *         null if there's no field is annotated with {@link XmlId}.
     * @throws UnableToCompleteException
     *             if there's more than one field annotated with {@link XmlId}.
     */
    protected FieldContext checkForIdField() throws UnableToCompleteException
    {
        FieldContext fieldContext = null;
        JField[] fields = modelType.getFields();
        if (fields != null && fields.length != 0)
        {
            int counter = 0;
            for (JField field : fields)
            {
                XmlId xmlId = field.getAnnotation(XmlId.class);
                if (xmlId != null)
                {
                    counter++;
                    fieldContext = new FieldContext(context.getTypeOracle(), handlerRegistry, modelType, field
                            .getType(), field.getName(), xmlId.value(), null, AssignmentType.ID, "element", "idValue");
                }
            }
            if (counter > 1)
            {
                die("There are %d @XmlId annotations in %s, but only one is allowed!", counter, modelType
                        .getQualifiedSourceName());
            }
        }
        return fieldContext;
    }
}
