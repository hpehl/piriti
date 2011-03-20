package name.pehl.piriti.rebind.xml;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import name.pehl.piriti.converter.client.NoopConverter;
import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.TypeUtils;
import name.pehl.piriti.rebind.propertyhandler.PropertyAnnotation;
import name.pehl.piriti.rebind.propertyhandler.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.ReferenceType;
import name.pehl.piriti.rebind.propertyhandler.VariableNames;
import name.pehl.piriti.rebind.xml.propertyhandler.ArrayPropertyHandler;
import name.pehl.piriti.rebind.xml.propertyhandler.CollectionPropertyHandler;
import name.pehl.piriti.rebind.xml.propertyhandler.XmlRegistryPropertyHandler;
import name.pehl.piriti.xml.client.Xml;
import name.pehl.piriti.xml.client.XmlId;
import name.pehl.piriti.xml.client.XmlIdRef;
import name.pehl.piriti.xml.client.XmlMappings;
import name.pehl.piriti.xml.client.XmlReader;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.core.ext.typeinfo.JType;

/**
 * Creator for {@linkplain XmlReader}s.
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


    // --------------------------------------------------------- create methods

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
        readListFromString(writer);
        writer.newline();

        readListFromDocument(writer);
        writer.newline();

        readListFromDocumentUsingXpath(writer);
        writer.newline();

        readListFromElement(writer);
        writer.newline();

        readListFromElementUsingXpath(writer);
        writer.newline();

        readFromString(writer);
        writer.newline();

        readFromDocument(writer);
        writer.newline();

        readFromElement(writer);
        writer.newline();

        internalReadList(writer);
        writer.newline();

        readIds(writer);
        writer.newline();

        readProperties(writer);
        writer.newline();

        readIdRefs(writer);
        writer.newline();

        CodeGeneration.idRef(writer, modelType);
        writer.newline();

        helperMethods(writer);
    }


    // ------------------------------------------------------ read list methods

    protected void readListFromString(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public List<%s> readList(String xml) {", modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("return readList(new XmlParser().parse(xml));");
        writer.outdent();
        writer.write("}");
    }


    protected void readListFromDocument(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public List<%s> readList(Document document) {", modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("if (document == null || document.getRoot() == null) {");
        writer.indent();
        writer.write("return null;");
        writer.outdent();
        writer.write("}");
        writer.write("return internalReadList(filterElements(document.getRoot().getChildren()));");
        writer.outdent();
        writer.write("}");
    }


    protected void readListFromDocumentUsingXpath(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public List<%s> readList(Document document, String xpath) {",
                modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("if (document == null) {");
        writer.indent();
        writer.write("return null;");
        writer.outdent();
        writer.write("}");
        writer.write("return internalReadList(filterElements(document.selectNodes(xpath)));");
        writer.outdent();
        writer.write("}");
    }


    protected void readListFromElement(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public List<%s> readList(Element element) {", modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("if (element == null) {");
        writer.indent();
        writer.write("return null;");
        writer.outdent();
        writer.write("}");
        writer.write("return internalReadList(filterElements(element.getChildren()));");
        writer.outdent();
        writer.write("}");
    }


    protected void readListFromElementUsingXpath(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public List<%s> readList(Element element, String xpath) {",
                modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("if (element == null) {");
        writer.indent();
        writer.write("return null;");
        writer.outdent();
        writer.write("}");
        writer.write("return internalReadList(filterElements(element.selectNodes(xpath)));");
        writer.outdent();
        writer.write("}");
    }


    // ---------------------------------------------------- read single methods

    protected void readFromString(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public %s read(String xml) {", modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("return read(new XmlParser().parse(xml));");
        writer.outdent();
        writer.write("}");
    }


    protected void readFromDocument(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public %s read(Document document) {", modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("if (document == null) {");
        writer.indent();
        writer.write("return null;");
        writer.outdent();
        writer.write("}");
        writer.write("return read(document.getRoot());");
        writer.outdent();
        writer.write("}");

    }


    protected void readFromElement(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public %s read(Element element) {", modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("if (element == null) {");
        writer.indent();
        writer.write("return null;");
        writer.outdent();
        writer.write("}");
        writer.write("%s model = readIds(element);", modelType.getParameterizedQualifiedSourceName());
        writer.write("readProperties(element, model);");
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
        writer.write("List<%s> models = null;", modelType.getParameterizedQualifiedSourceName());
        writer.write("if (!elements.isEmpty()) {");
        writer.indent();
        writer.write("models = new ArrayList<%s>();", modelType.getParameterizedQualifiedSourceName());
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
        writer.write("readProperties(element, model);");
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
        PropertyHandler handler = null;
        PropertyContext propertyContext = checkForIdField();
        if (propertyContext != null)
        {
            handler = handlerRegistry.findPropertyHandler(propertyContext);
            validIdField = handler != null && handler.isValid(writer, propertyContext);
        }

        writer.write("private %s readIds(Element element) {", modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("if (element != null) {");
        writer.indent();
        if (validIdField)
        {
            handler.comment(writer, propertyContext);
            handler.declare(writer, propertyContext);
            handler.readInput(writer, propertyContext);
            writer.write("%s model = this.idRef(%s);", modelType.getParameterizedQualifiedSourceName(), propertyContext
                    .getVariableNames().getValueVariable());
            writer.write("if (model == null) {");
            writer.indent();
            writer.write("model = new %s();", modelType.getParameterizedQualifiedSourceName());
            handler.assign(writer, propertyContext);
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


    protected void readProperties(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("private %1$s readProperties(Element element, %1$s model) {",
                modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("if (element != null) {");
        writer.indent();
        handleProperties(writer);
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
     * annotation inside an {@link XmlMappings} annotation. If one of them was
     * found the corresponding {@link PropertyContext} is created and returned.
     * <p>
     * If more than one {@link XmlId} annotation was found in the
     * {@code modelType} an {@link UnableToCompleteException} is thrown.
     * <p>
     * If no id mapping was found (neither in the {@code modelType} nor inside
     * the {@link XmlMappings} annotation) <code>null</code> is returned.
     * 
     * @return the {@link PropertyContext} for the id mapping or
     *         <code>null</code> if no id mapping is present.
     * @throws UnableToCompleteException
     *             if more than one {@link XmlId} annotation was found in the
     *             {@code modelType}
     */
    protected PropertyContext checkForIdField() throws UnableToCompleteException
    {
        PropertyContext propertyContext = null;

        // First look for an @XmlId annotation on fields
        JField[] fields = findAnnotatedFields(modelType, XmlId.class);
        if (fields.length == 1)
        {
            if (fields.length != 0)
            {
                JField field = fields[0];
                XmlId xmlId = field.getAnnotation(XmlId.class);
                VariableNames variableNames = new VariableNames("element", "idValue", "xmlBuilder");
                propertyContext = new PropertyContext(context.getTypeOracle(), handlerRegistry, interfaceType,
                        modelType, field.getType(), field.getName(), xmlId.value(), null, xmlId.stripWsnl(),
                        NoopConverter.class, ReferenceType.ID, xmlId.getter(), xmlId.setter(), variableNames);
            }
        }
        else if (fields.length > 1)
        {
            die("There are %d @XmlId annotations in %s or its superclasses, but only one is allowed!", fields.length,
                    modelType.getQualifiedSourceName());
        }
        else
        {
            // Fall back to the interfaceType
            XmlMappings xmlFields = interfaceType.getAnnotation(XmlMappings.class);
            if (xmlFields != null)
            {
                XmlId xmlId = xmlFields.id();
                if (!XmlMappings.NO_ID.equals(xmlId.value()))
                {
                    JField field = TypeUtils.findField(modelType, xmlId.property());
                    if (field != null)
                    {
                        VariableNames variableNames = new VariableNames("element", "idValue", "xmlBuilder");
                        propertyContext = new PropertyContext(context.getTypeOracle(), handlerRegistry, interfaceType,
                                modelType, field.getType(), field.getName(), xmlId.value(), null, xmlId.stripWsnl(),
                                NoopConverter.class, ReferenceType.ID, xmlId.getter(), xmlId.setter(), variableNames);
                    }
                    else
                    {
                        die("Cannot find id field %s in %s or its superclasses", xmlId.property(),
                                modelType.getQualifiedSourceName());
                    }
                }
            }
        }
        return propertyContext;
    }


    protected void handleIdsInNestedModels(IndentedWriter writer) throws UnableToCompleteException
    {
        int counter = 0;
        List<PropertyAnnotation<Xml>> properties = findPropertyAnnotations();
        for (Iterator<PropertyAnnotation<Xml>> iter = properties.iterator(); iter.hasNext();)
        {
            PropertyAnnotation<Xml> propertyAnnotation = iter.next();
            String xpath = calculateXpath(propertyAnnotation.getAnnotation().value(), propertyAnnotation.getProperty(),
                    propertyAnnotation.getType());
            VariableNames variableNames = new VariableNames("element", "nestedValue" + counter, "xmlBuilder");
            PropertyContext propertyContext = new PropertyContext(context.getTypeOracle(), handlerRegistry,
                    interfaceType, modelType, propertyAnnotation.getType(), propertyAnnotation.getProperty(), xpath,
                    propertyAnnotation.getAnnotation().format(), propertyAnnotation.getAnnotation().stripWsnl(),
                    propertyAnnotation.getAnnotation().converter(), null, propertyAnnotation.getAnnotation().getter(),
                    propertyAnnotation.getAnnotation().setter(), variableNames);
            PropertyHandler propertyHandler = handlerRegistry.findPropertyHandler(propertyContext);
            if ((propertyHandler instanceof XmlRegistryPropertyHandler
                    || propertyHandler instanceof ArrayPropertyHandler || propertyHandler instanceof CollectionPropertyHandler)
                    && propertyHandler.isValid(writer, propertyContext))
            {
                writer.newline();
                handleProperty(writer, propertyHandler, propertyContext, iter.hasNext());
                counter++;
            }
        }
    }


    protected void handleIdRefs(IndentedWriter writer) throws UnableToCompleteException
    {
        int counter = 0;
        Set<PropertyAnnotation<XmlIdRef>> properties = findReferenceAnnotations();
        for (Iterator<PropertyAnnotation<XmlIdRef>> iter = properties.iterator(); iter.hasNext();)
        {
            PropertyAnnotation<XmlIdRef> propertyAnnotation = iter.next();
            String xpath = calculateXpath(propertyAnnotation.getAnnotation().value(), propertyAnnotation.getProperty(),
                    propertyAnnotation.getType());
            VariableNames variableNames = new VariableNames("element", "idRefValue" + counter, "xmlBuilder");
            PropertyContext fieldContext = new PropertyContext(context.getTypeOracle(), handlerRegistry, interfaceType,
                    modelType, propertyAnnotation.getType(), propertyAnnotation.getProperty(), xpath, null,
                    propertyAnnotation.getAnnotation().stripWsnl(), NoopConverter.class, ReferenceType.IDREF,
                    propertyAnnotation.getAnnotation().getter(), propertyAnnotation.getAnnotation().setter(),
                    variableNames);
            PropertyHandler fieldHandler = handlerRegistry.findPropertyHandler(fieldContext);
            if (fieldHandler != null && fieldHandler.isValid(writer, fieldContext))
            {
                writer.newline();
                handleProperty(writer, fieldHandler, fieldContext, iter.hasNext());
                counter++;
            }
        }
    }


    private Set<PropertyAnnotation<XmlIdRef>> findReferenceAnnotations() throws UnableToCompleteException
    {
        Set<PropertyAnnotation<XmlIdRef>> properties = new HashSet<PropertyAnnotation<XmlIdRef>>();

        // Step 1: Add all @XmlIdRef annotations on fields.
        JField[] fields = findAnnotatedFields(modelType, XmlIdRef.class);
        for (JField field : fields)
        {
            XmlIdRef annotation = field.getAnnotation(XmlIdRef.class);
            properties.add(new PropertyAnnotation<XmlIdRef>(field.getName(), field.getType(), annotation, -1));
        }

        // Step 2: Add all @XmlIdRef annotations in the @XmlMappings annotation
        // from the interfaceType. If there are already annotated properties
        // from step 1, they won't be added again.
        XmlMappings interfaceTypeFields = interfaceType.getAnnotation(XmlMappings.class);
        if (interfaceTypeFields != null)
        {
            XmlIdRef[] annotations = interfaceTypeFields.references();
            for (XmlIdRef annotation : annotations)
            {
                JField field = TypeUtils.findField(modelType, annotation.property());
                if (field != null)
                {
                    properties.add(new PropertyAnnotation<XmlIdRef>(annotation.property(), field.getType(), annotation,
                            -1));
                }
                else
                {
                    die("Cannot find idref field %s in %s or its superclasses", annotation.property(),
                            modelType.getQualifiedSourceName());
                }
            }
        }

        return properties;
    }


    @Override
    protected void handleProperty(IndentedWriter writer, PropertyHandler fieldHandler, PropertyContext fieldContext,
            boolean hasNext) throws UnableToCompleteException
    {
        fieldHandler.comment(writer, fieldContext);
        fieldHandler.declare(writer, fieldContext);
        fieldHandler.readInput(writer, fieldContext);
        fieldHandler.assign(writer, fieldContext);
    }


    protected String calculateXpath(PropertyAnnotation<XmlIdRef> propertyAnnotation)
    {
        String xpath = propertyAnnotation.getAnnotation().value();
        if (xpath == null || xpath.length() == 0)
        {
            xpath = propertyAnnotation.getProperty();
            JType type = propertyAnnotation.getType();
            if (type.isPrimitive() != null || TypeUtils.isBasicType(type) || type.isEnum() != null)
            {
                xpath += "/text()";
            }
        }
        return xpath;
    }
}
