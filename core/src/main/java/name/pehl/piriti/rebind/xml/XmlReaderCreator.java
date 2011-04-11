package name.pehl.piriti.rebind.xml;

import java.util.Iterator;

import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerRegistry;
import name.pehl.piriti.rebind.xml.propertyhandler.ArrayPropertyHandler;
import name.pehl.piriti.rebind.xml.propertyhandler.CollectionPropertyHandler;
import name.pehl.piriti.rebind.xml.propertyhandler.XmlRegistryPropertyHandler;
import name.pehl.piriti.xml.client.XmlReader;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * Creator for {@linkplain XmlReader}s.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class XmlReaderCreator extends AbstractXmlCreator
{
    // --------------------------------------------------------- initialization

    public XmlReaderCreator(GeneratorContext generatorContext, JClassType rwType, String implName, String rwClassname,
            TreeLogger logger) throws UnableToCompleteException
    {
        super(generatorContext, rwType, implName, rwClassname, logger);
    }


    @Override
    protected PropertyHandlerRegistry setupPropertyHandlerRegistry()
    {
        return new XmlPropertyHandlerRegistry(logger);
    }


    // --------------------------------------------------------- create methods

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

        CodeGeneration.idRef(writer, typeContext.getType());
        writer.newline();

        helperMethods(writer);
    }


    // ------------------------------------------------------ read list methods

    protected void readListFromString(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public List<%s> readList(String xml) {", typeContext.getType()
                .getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("return readList(new XmlParser().parse(xml));");
        writer.outdent();
        writer.write("}");
    }


    protected void readListFromDocument(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public List<%s> readList(Document document) {", typeContext.getType()
                .getParameterizedQualifiedSourceName());
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
        writer.write("public List<%s> readList(Document document, String xpath) {", typeContext.getType()
                .getParameterizedQualifiedSourceName());
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
        writer.write("public List<%s> readList(Element element) {", typeContext.getType()
                .getParameterizedQualifiedSourceName());
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
        writer.write("public List<%s> readList(Element element, String xpath) {", typeContext.getType()
                .getParameterizedQualifiedSourceName());
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
        writer.write("public %s read(String xml) {", typeContext.getType().getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("return read(new XmlParser().parse(xml));");
        writer.outdent();
        writer.write("}");
    }


    protected void readFromDocument(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public %s read(Document document) {", typeContext.getType().getParameterizedQualifiedSourceName());
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
        writer.write("public %s read(Element %s) {", typeContext.getType().getParameterizedQualifiedSourceName(),
                typeContext.getVariableNames().getInputVariable());
        writer.indent();
        writer.write("if (%s == null) {", typeContext.getVariableNames().getInputVariable());
        writer.indent();
        writer.write("return null;");
        writer.outdent();
        writer.write("}");
        writer.write("%s %s = readIds(%s);", typeContext.getType().getParameterizedQualifiedSourceName(), typeContext
                .getVariableNames().getInstanceVariable(), typeContext.getVariableNames().getInputVariable());
        writer.write("readProperties(%s, %s);", typeContext.getVariableNames().getInputVariable(), typeContext
                .getVariableNames().getInstanceVariable());
        writer.write("readIdRefs(%s, %s);", typeContext.getVariableNames().getInputVariable(), typeContext
                .getVariableNames().getInstanceVariable());
        writer.write("return %s;", typeContext.getVariableNames().getInstanceVariable());
        writer.outdent();
        writer.write("}");
    }


    // --------------------------------------------------------- helper methods

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


    protected void internalReadList(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("private List<%s> internalReadList(List<Element> elements) {", typeContext.getType()
                .getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("List<%s> models = null;", typeContext.getType().getParameterizedQualifiedSourceName());
        writer.write("if (!elements.isEmpty()) {");
        writer.indent();
        writer.write("models = new ArrayList<%s>();", typeContext.getType().getParameterizedQualifiedSourceName());
        writer.write("for (Element element : elements) {");
        writer.indent();
        writer.write("%s %s = readIds(element);", typeContext.getType().getParameterizedQualifiedSourceName(),
                typeContext.getVariableNames().getInstanceVariable());
        writer.write("models.add(%s);", typeContext.getVariableNames().getInstanceVariable());
        writer.outdent();
        writer.write("}");
        writer.write("int index = 0;");
        writer.write("for (Element element : elements) {");
        writer.indent();
        writer.write("%s %s = models.get(index++);", typeContext.getType().getParameterizedQualifiedSourceName(),
                typeContext.getVariableNames().getInstanceVariable());
        writer.write("readProperties(element, %s);", typeContext.getVariableNames().getInstanceVariable());
        writer.write("readIdRefs(element, %s);", typeContext.getVariableNames().getInstanceVariable());
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.write("return models;");
        writer.outdent();
        writer.write("}");
    }


    protected void readProperties(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("private %1$s readProperties(Element %2$s, %1$s %3$s) {", typeContext.getType()
                .getParameterizedQualifiedSourceName(), typeContext.getVariableNames().getInputVariable(), typeContext
                .getVariableNames().getInstanceVariable());
        writer.indent();
        writer.write("if (%s != null) {", typeContext.getVariableNames().getInputVariable());
        writer.indent();
        handleProperties(writer);
        writer.outdent();
        writer.write("}");
        writer.write("return %s;", typeContext.getVariableNames().getInstanceVariable());
        writer.outdent();
        writer.write("}");
    }


    @Override
    protected void handleProperty(IndentedWriter writer, PropertyHandler fieldHandler, PropertyContext fieldContext,
            boolean hasNext) throws UnableToCompleteException
    {
        fieldHandler.comment(writer, fieldContext);
        fieldHandler.declare(writer, fieldContext);
        fieldHandler.readInput(writer, fieldContext, propertyHandlerRegistry);
        fieldHandler.assign(writer, fieldContext);
    }


    // ------------------------------------------------------- ids / references

    protected void readIds(IndentedWriter writer) throws UnableToCompleteException
    {
        boolean validIdField = false;
        PropertyHandler handler = null;
        PropertyContext propertyContext = typeContext.getId();
        if (propertyContext != null)
        {
            handler = propertyHandlerRegistry.findPropertyHandler(propertyContext);
            validIdField = handler != null && handler.isValid(writer, propertyContext);
        }

        writer.write("private %s readIds(Element %s) {", typeContext.getType().getParameterizedQualifiedSourceName(),
                typeContext.getVariableNames().getInputVariable());
        writer.indent();
        writer.write("if (%s != null) {", typeContext.getVariableNames().getInputVariable());
        writer.indent();
        if (validIdField)
        {
            handler.comment(writer, propertyContext);
            handler.declare(writer, propertyContext);
            handler.readInput(writer, propertyContext, propertyHandlerRegistry);
            writer.write("%s %s = this.idRef(%s);", typeContext.getType().getParameterizedQualifiedSourceName(),
                    typeContext.getVariableNames().getInstanceVariable(), propertyContext.getVariableNames()
                            .getValueVariable());
            writer.write("if (%s == null) {", typeContext.getVariableNames().getInstanceVariable());
            writer.indent();
            // TODO Use InstanceCreator<T, C> if specified
            writer.write("%s = new %s();", typeContext.getVariableNames().getInstanceVariable(), typeContext.getType()
                    .getParameterizedQualifiedSourceName());
            handler.assign(writer, propertyContext);
            writer.outdent();
            writer.write("}");
        }
        else
        {
            // TODO Use InstanceCreator<T, C> if specified
            writer.write("%1$s %2$s = new %1$s();", typeContext.getType().getParameterizedQualifiedSourceName(),
                    typeContext.getVariableNames().getInstanceVariable());
        }
        handleIdsInNestedModels(writer);
        writer.write("return %s;", typeContext.getVariableNames().getInstanceVariable());
        writer.outdent();
        writer.write("}");
        writer.write("return null;");
        writer.outdent();
        writer.write("}");
    }


    protected void handleIdsInNestedModels(IndentedWriter writer) throws UnableToCompleteException
    {
        int counter = 0;
        for (Iterator<PropertyContext> iter = typeContext.getProperties().iterator(); iter.hasNext();)
        {
            // TODO Change VariableNames to
            // new VariableNames("element", "nestedValue" + counter,
            // "xmlBuilder");
            PropertyContext propertyContext = iter.next();
            PropertyHandler propertyHandler = propertyHandlerRegistry.findPropertyHandler(propertyContext);
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
        for (Iterator<PropertyContext> iter = typeContext.getReferences().iterator(); iter.hasNext();)
        {
            // TODO Change VariableNames to
            // new VariableNames("element", "idRefValue" + counter,
            // "xmlBuilder");
            PropertyContext propertyContext = iter.next();
            PropertyHandler fieldHandler = propertyHandlerRegistry.findPropertyHandler(propertyContext);
            if (fieldHandler != null && fieldHandler.isValid(writer, propertyContext))
            {
                writer.newline();
                handleProperty(writer, fieldHandler, propertyContext, iter.hasNext());
                counter++;
            }
        }
    }


    protected void readIdRefs(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("private %1$s readIdRefs(Element %2$s, %1$s %3$s) {", typeContext.getType()
                .getParameterizedQualifiedSourceName(), typeContext.getVariableNames().getInputVariable(), typeContext
                .getVariableNames().getInstanceVariable());
        writer.indent();
        writer.write("if (%s != null) {", typeContext.getVariableNames().getInputVariable());
        writer.indent();
        handleIdRefs(writer);
        writer.outdent();
        writer.write("}");
        writer.write("return %s;", typeContext.getVariableNames().getInstanceVariable());
        writer.outdent();
        writer.write("}");
    }
}
