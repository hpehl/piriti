package name.pehl.piriti.rebind.xml;

import java.util.HashMap;
import java.util.Map;

import name.pehl.piriti.client.xml.XmlField;
import name.pehl.piriti.client.xml.XmlFields;
import name.pehl.piriti.client.xml.XmlId;
import name.pehl.piriti.client.xml.XmlIdRef;
import name.pehl.piriti.client.xml.XmlReader;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.fieldhandler.AssignmentPolicy;
import name.pehl.piriti.rebind.fieldhandler.AssignmentType;
import name.pehl.piriti.rebind.fieldhandler.FieldAnnotation;
import name.pehl.piriti.rebind.fieldhandler.FieldContext;
import name.pehl.piriti.rebind.fieldhandler.FieldHandler;
import name.pehl.piriti.rebind.xml.fieldhandler.ArrayFieldHandler;
import name.pehl.piriti.rebind.xml.fieldhandler.CollectionFieldHandler;
import name.pehl.piriti.rebind.xml.fieldhandler.XmlRegistryFieldHandler;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;

/**
 * Class which generates the code necessary to map the annotated fields.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class XmlReaderCreator extends AbstractXmlCreator
{
    // ----------------------------------------------------------- constructors

    public XmlReaderCreator(GeneratorContext context, JClassType interfaceType, String implName,
            String readerClassname, TreeLogger logger) throws UnableToCompleteException
    {
        super(context, interfaceType, implName, readerClassname, logger);
    }


    @Override
    protected void createMemberVariables(IndentedWriter writer) throws UnableToCompleteException
    {
        super.createMemberVariables(writer);
        writer.write("private Map<String,%s> idMap;", modelType.getQualifiedSourceName());
    }


    @Override
    protected void createConstructorBody(IndentedWriter writer)
    {
        super.createConstructorBody(writer);
        writer.write("this.idMap = new HashMap<String,%s>();", modelType.getQualifiedSourceName());
    }


    @Override
    protected void createMethods(IndentedWriter writer) throws UnableToCompleteException
    {
        readListFromDocument(writer);
        writer.newline();

        readListFromDocumentUsingXpath(writer);
        writer.newline();

        readListFromElement(writer);
        writer.newline();

        readListFromElementUsingXpath(writer);
        writer.newline();

        readFromDocument(writer);
        writer.newline();

        readFromElement(writer);
        writer.newline();

        internalReadList(writer);
        writer.newline();

        readIds(writer);
        writer.newline();

        readFields(writer);
        writer.newline();

        readIdRefs(writer);
        writer.newline();

        helperMethods(writer);
        writer.newline();
    }


    // ------------------------------------------------------ read list methods

    protected void readListFromDocument(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public List<%s> readList(Document document) {", modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("return internalReadList(filterElements(document.getRoot().getChildren()));");
        writer.outdent();
        writer.write("}");
    }


    protected void readListFromDocumentUsingXpath(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public List<%s> readList(Document document, String xpath) {",
                modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("return internalReadList(filterElements(document.selectNodes(xpath)));");
        writer.outdent();
        writer.write("}");
    }


    protected void readListFromElement(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public List<%s> readList(Element element) {", modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("return internalReadList(filterElements(element.getChildren()));");
        writer.outdent();
        writer.write("}");
    }


    protected void readListFromElementUsingXpath(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public List<%s> readList(Element element, String xpath) {",
                modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("return internalReadList(filterElements(element.selectNodes(xpath)));");
        writer.outdent();
        writer.write("}");
    }


    // ---------------------------------------------------- read single methods

    protected void readFromDocument(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public %s read(Document document) {", modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("return read(document.getRoot());");
        writer.outdent();
        writer.write("}");

    }


    protected void readFromElement(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public %s read(Element element) {", modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("%s model = readIds(element);", modelType.getParameterizedQualifiedSourceName());
        writer.write("readFields(element, model);");
        writer.write("readIdRefs(element, model);");
        writer.write("return model;");
        writer.outdent();
        writer.write("}");
    }


    // --------------------------------------------------------- helper methods

    protected void internalReadList(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("private List<%s> internalReadList(List<Element> elements) {",
                modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("List<%1$s> models = new ArrayList<%1$s>();", modelType.getParameterizedQualifiedSourceName());
        writer.write("if (!elements.isEmpty()) {");
        writer.indent();
        writer.write("for (Element element : elements) {");
        writer.indent();
        writer.write("%s model = readIds(element);", modelType.getParameterizedQualifiedSourceName());
        writer.write("models.add(model);");
        writer.outdent();
        writer.write("}");
        writer.write("int index = 0;");
        writer.write("for (Element element : elements) {");
        writer.indent();
        writer.write("%s model = models.get(index++);", modelType.getParameterizedQualifiedSourceName());
        writer.write("readFields(element, model);");
        writer.write("readIdRefs(element, model);");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.write("return models;");
        writer.outdent();
        writer.write("}");
    }


    protected void readIds(IndentedWriter writer) throws UnableToCompleteException
    {
        boolean validIdField = false;
        FieldHandler handler = null;
        FieldContext fieldContext = checkForIdField();
        if (fieldContext != null)
        {
            handler = handlerRegistry.findFieldHandler(fieldContext);
            validIdField = handler != null && handler.isValid(writer, fieldContext);
        }

        writer.write("private %s readIds(Element element) {", modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("if (element != null) {");
        writer.indent();
        if (validIdField)
        {
            handler.writeComment(writer, fieldContext);
            handler.writeDeclaration(writer, fieldContext);
            handler.writeConverterCode(writer, fieldContext);
            writer.write("%s model = this.idRef(%s);", modelType.getParameterizedQualifiedSourceName(),
                    fieldContext.getValueVariable());
            writer.write("if (model == null) {");
            writer.indent();
            writer.write("model = new %s();", modelType.getParameterizedQualifiedSourceName());
            handler.writeAssignment(writer, fieldContext);
            writer.outdent();
            writer.write("}");
            handleIdsInNestedModels(writer);
            writer.write("return model;");
        }
        else
        {
            writer.write("%1$s model = new %1$s();", modelType.getParameterizedQualifiedSourceName());
            handleIdsInNestedModels(writer);
            writer.write("return model;");
        }
        writer.outdent();
        writer.write("}");
        writer.write("return null;");
        writer.outdent();
        writer.write("}");
    }


    protected void readFields(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("private %1$s readFields(Element element, %1$s model) {",
                modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("if (element != null) {");
        writer.indent();

        handleFields(writer);

        writer.outdent();
        writer.write("}");
        writer.write("return model;");
        writer.outdent();
        writer.write("}");
    }


    protected void readIdRefs(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("private %1$s readIdRefs(Element element, %1$s model) {",
                modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("if (element != null) {");
        writer.indent();

        handleIdRefs(writer);

        writer.outdent();
        writer.write("}");
        writer.write("return model;");
        writer.outdent();
        writer.write("}");
    }


    protected void helperMethods(IndentedWriter writer)
    {
        writer.write("private List<Element> filterElements(List<Node> nodes) {");
        writer.indent();
        writer.write("List<Element> elements = new ArrayList<Element>();");
        writer.write("for (Node node : nodes) {");
        writer.indent();
        writer.write("if (node instanceof Element) {");
        writer.indent();
        writer.write("elements.add((Element) node);");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.write("return elements;");
        writer.outdent();
        writer.write("}");
    }


    /**
     * Tries to find information about an mapped id. First the {@code modelType}
     * is scanned for an {@link XmlId} annotation. If there's no such annotation
     * the {@link XmlReader} is asked whether it contains an {@link XmlId}
     * annotation inside an {@link XmlFields} annotation. If one of them was
     * found the corresponding {@link FieldContext} is created and returned.
     * <p>
     * If more than one {@link XmlId} annotation was found in the
     * {@code modelType} an {@link UnableToCompleteException} is thrown.
     * <p>
     * If no id mapping was found (neither in the {@code modelType} nor inside
     * the {@link XmlFields} annotation) <code>null</code> is returned.
     * 
     * @return the {@link FieldContext} for the id mapping or <code>null</code>
     *         if no id mapping is present.
     * @throws UnableToCompleteException
     *             if more than one {@link XmlId} annotation was found in the
     *             {@code modelType}
     */
    protected FieldContext checkForIdField() throws UnableToCompleteException
    {
        FieldContext fieldContext = null;

        // First look for an XmlId annotation in the modelType
        JField[] fields = findAnnotatedFields(modelType, XmlId.class);
        if (fields.length != 0)
        {
            JField field = fields[0];
            if (fields.length == 1)
            {
                XmlId xmlId = field.getAnnotation(XmlId.class);
                fieldContext = new FieldContext(context.getTypeOracle(), handlerRegistry, modelType, field.getType(),
                        field.getName(), xmlId.value(), null, xmlId.stripWsnl(), AssignmentType.ID,
                        AssignmentPolicy.FIELD_ONLY, "element", "idValue");
            }
            else
            {
                die("There are %d @XmlId annotations in %s or its superclasses, but only one is allowed!",
                        fields.length, modelType.getQualifiedSourceName());
            }
        }
        else
        {
            // Fall back to the interfaceType
            XmlFields xmlFields = interfaceType.getAnnotation(XmlFields.class);
            if (xmlFields != null)
            {
                XmlId xmlId = xmlFields.id();
                if (!XmlFields.NO_ID.equals(xmlId.value()))
                {
                    JField field = modelType.getField(xmlId.name());
                    if (field != null)
                    {
                        fieldContext = new FieldContext(context.getTypeOracle(), handlerRegistry, modelType,
                                field.getType(), field.getName(), xmlId.value(), null, xmlId.stripWsnl(),
                                AssignmentType.ID, AssignmentPolicy.SETTER_FIRST, "element", "idValue");
                    }
                    else
                    {
                        die("Id field \"" + xmlId.value() + "\" cannot be found in \""
                                + modelType.getQualifiedSourceName() + "\"");
                    }
                }
            }
        }
        return fieldContext;
    }


    protected void handleIdsInNestedModels(IndentedWriter writer) throws UnableToCompleteException
    {
        int counter = 0;
        Map<String, FieldAnnotation<XmlField>> fields = findFieldAnnotations();
        for (FieldAnnotation<XmlField> fieldAnnotation : fields.values())
        {
            String xpath = calculateXpath(fieldAnnotation.field, fieldAnnotation.annotation.value());
            FieldContext fieldContext = new FieldContext(context.getTypeOracle(), handlerRegistry, modelType,
                    fieldAnnotation.field.getType(), fieldAnnotation.field.getName(), xpath,
                    fieldAnnotation.annotation.format(), fieldAnnotation.annotation.stripWsnl(),
                    AssignmentType.MAPPING, fieldAnnotation.assignmentPolicy, "element", "nestedValue" + counter);
            FieldHandler fieldHandler = handlerRegistry.findFieldHandler(fieldContext);
            if ((fieldHandler instanceof XmlRegistryFieldHandler || fieldHandler instanceof ArrayFieldHandler || fieldHandler instanceof CollectionFieldHandler)
                    && fieldHandler.isValid(writer, fieldContext))
            {
                writer.newline();
                handleField(writer, fieldHandler, fieldContext);
                counter++;
            }
        }
    }


    @Override
    protected void handleField(IndentedWriter writer, FieldHandler fieldHandler, FieldContext fieldContext)
            throws UnableToCompleteException
    {
        fieldHandler.writeComment(writer, fieldContext);
        fieldHandler.writeDeclaration(writer, fieldContext);
        fieldHandler.writeConverterCode(writer, fieldContext);
        fieldHandler.writeAssignment(writer, fieldContext);
    }


    protected void handleIdRefs(IndentedWriter writer) throws UnableToCompleteException
    {
        int counter = 0;
        Map<String, FieldAnnotation<XmlIdRef>> fields = findReferenceAnnotations();
        for (FieldAnnotation<XmlIdRef> fieldAnnotation : fields.values())
        {
            String xpath = calculateXpath(fieldAnnotation.field, fieldAnnotation.annotation.value());
            FieldContext fieldContext = new FieldContext(context.getTypeOracle(), handlerRegistry, modelType,
                    fieldAnnotation.field.getType(), fieldAnnotation.field.getName(), xpath, null,
                    fieldAnnotation.annotation.stripWsnl(), AssignmentType.IDREF, fieldAnnotation.assignmentPolicy,
                    "element", "idRefValue" + counter);
            FieldHandler fieldHandler = handlerRegistry.findFieldHandler(fieldContext);
            if (fieldHandler != null && fieldHandler.isValid(writer, fieldContext))
            {
                writer.newline();
                handleField(writer, fieldHandler, fieldContext);
                counter++;
            }
        }
    }


    /**
     * TODO Documentation
     * 
     * @return
     */
    private Map<String, FieldAnnotation<XmlIdRef>> findReferenceAnnotations()
    {
        Map<String, FieldAnnotation<XmlIdRef>> fields = new HashMap<String, FieldAnnotation<XmlIdRef>>();

        // Step 1: Add all XmlField annotations in the XmlFields annotation
        // from the interfaceType
        XmlFields interfaceTypeFields = interfaceType.getAnnotation(XmlFields.class);
        if (interfaceTypeFields != null)
        {
            XmlIdRef[] annotations = interfaceTypeFields.references();
            for (XmlIdRef annotation : annotations)
            {
                JField field = modelType.getField(annotation.name());
                if (field != null)
                {
                    fields.put(field.getName(), new FieldAnnotation<XmlIdRef>(field, annotation,
                            AssignmentPolicy.SETTER_FIRST));
                }
                // TODO Is it an error if field == null?
            }
        }

        // Step 2: Add all XmlField annotations of the modelType fields. If
        // there's already an entry for the field from step 1, it will be
        // overwritten!
        JField[] modelTypeFields = findAnnotatedFields(modelType, XmlIdRef.class);
        for (JField field : modelTypeFields)
        {
            XmlIdRef annotation = field.getAnnotation(XmlIdRef.class);
            fields.put(field.getName(), new FieldAnnotation<XmlIdRef>(field, annotation, AssignmentPolicy.FIELD_ONLY));
        }
        return fields;
    }
}
