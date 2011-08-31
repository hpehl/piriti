package name.pehl.piriti.rebind.xml;

import java.util.Iterator;
import java.util.logging.Level;

import name.pehl.piriti.rebind.AbstractReaderCreator;
import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.VariableNames;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerLookup;
import name.pehl.piriti.rebind.xml.propertyhandler.ArrayPropertyHandler;
import name.pehl.piriti.rebind.xml.propertyhandler.CollectionPropertyHandler;
import name.pehl.piriti.rebind.xml.propertyhandler.DefaultPropertyHandler;
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
public class XmlReaderCreator extends AbstractReaderCreator
{
    // --------------------------------------------------------- initialization

    public XmlReaderCreator(GeneratorContext generatorContext, JClassType rwType, String implName, String rwClassname,
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
    public void createReaderMethods(IndentedWriter writer) throws UnableToCompleteException
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

        readList(writer);
        writer.newline();

        readSingleFromString(writer);
        writer.newline();

        readSingleFromDocument(writer);
        writer.newline();

        readSingle(writer);
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


    @Override
    protected void readList(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("private List<%s> internalReadList(List<Element> elements) {", typeContext.getType()
                .getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("List<%s> models = null;", typeContext.getType().getParameterizedQualifiedSourceName());
        writer.write("List<InstanceContextHolder<%s, Element>> instanceContextHolders = null;", typeContext.getType()
                .getParameterizedQualifiedSourceName());
        writer.write("if (!elements.isEmpty()) {");
        writer.indent();
        writer.write("models = new ArrayList<%s>();", typeContext.getType().getParameterizedQualifiedSourceName());
        writer.write("instanceContextHolders = new ArrayList<InstanceContextHolder<%s, Element>>();", typeContext
                .getType().getParameterizedQualifiedSourceName());

        CodeGeneration.log(writer, Level.FINE, "First iteration over elements to create models and process IDs");
        writer.write("for (Element element : elements) {");
        writer.indent();
        writer.write("%s %s = readIds(element);", typeContext.getType().getParameterizedQualifiedSourceName(),
                typeContext.getVariableNames().getInstanceVariable());
        writer.write("if (%s != null) {", typeContext.getVariableNames().getInstanceVariable());
        writer.indent();
        writer.write("models.add(%s);", typeContext.getVariableNames().getInstanceVariable());
        writer.write("instanceContextHolders.add(new InstanceContextHolder<%s, Element>(%s, element));", typeContext
                .getType().getParameterizedQualifiedSourceName(), typeContext.getVariableNames().getInstanceVariable());
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");

        CodeGeneration.log(writer, Level.FINE, "Second iteration over generated models to map properties and IDREFs");
        writer.write("for (InstanceContextHolder<%s, Element> ich : instanceContextHolders) {", typeContext.getType()
                .getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("%s %s = ich.getInstance();", typeContext.getType().getParameterizedQualifiedSourceName(),
                typeContext.getVariableNames().getInstanceVariable());
        writer.write("readProperties(ich.getContext(), %s);", typeContext.getVariableNames().getInstanceVariable());
        writer.write("readIdRefs(ich.getContext(), %s);", typeContext.getVariableNames().getInstanceVariable());
        writer.write("ReadModelEvent.fire(this, %s);", typeContext.getVariableNames().getInstanceVariable());
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.write("return models;");
        writer.outdent();
        writer.write("}");
    }


    // ---------------------------------------------------- read single methods

    protected void readSingleFromString(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public %s read(String xml) {", typeContext.getType().getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("return read(new XmlParser().parse(xml));");
        writer.outdent();
        writer.write("}");
    }


    protected void readSingleFromDocument(IndentedWriter writer) throws UnableToCompleteException
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


    @Override
    protected void readSingle(IndentedWriter writer) throws UnableToCompleteException
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
        writer.write("ReadModelEvent.fire(this, %s);", typeContext.getVariableNames().getInstanceVariable());
        writer.write("return %s;", typeContext.getVariableNames().getInstanceVariable());
        writer.outdent();
        writer.write("}");
    }


    // -------------------------------------------------------------------- ids

    /**
     * TODO Is this method still necessary?
     * 
     * @see name.pehl.piriti.rebind.AbstractReaderCreator#handleIdsInNestedTypes(name.pehl.piriti.rebind.IndentedWriter)
     */
    @Override
    protected void handleIdsInNestedTypes(IndentedWriter writer) throws UnableToCompleteException
    {
        for (Iterator<PropertyContext> iter = typeContext.getProperties().iterator(); iter.hasNext();)
        {
            PropertyContext propertyContext = iter.next();
            PropertyHandler propertyHandler = propertyHandlerLookup.lookup(propertyContext);
            if (idable(writer, propertyHandler, propertyContext))
            {
                writer.newline();
                handleProperty(writer, propertyHandler, propertyContext, iter.hasNext());
            }
        }
    }


    private boolean idable(IndentedWriter writer, PropertyHandler propertyHandler, PropertyContext propertyContext)
            throws UnableToCompleteException
    {
        boolean idable = (propertyHandler instanceof DefaultPropertyHandler
                || propertyHandler instanceof ArrayPropertyHandler || propertyHandler instanceof CollectionPropertyHandler)
                && propertyHandler.isValid(writer, propertyContext);
        return idable;
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
}
