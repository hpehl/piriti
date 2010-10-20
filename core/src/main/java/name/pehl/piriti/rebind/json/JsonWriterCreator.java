package name.pehl.piriti.rebind.json;

import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.propertyhandler.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerRegistry;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * Class which generates the code necessary to serialize a POJO to JSON.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class JsonWriterCreator extends AbstractJsonCreator
{
    // --------------------------------------------------------- initialization

    public JsonWriterCreator(GeneratorContext context, JClassType interfaceType, String implName,
            String readerClassname, TreeLogger logger) throws UnableToCompleteException
    {
        super(context, interfaceType, implName, readerClassname, logger);
    }


    @Override
    protected PropertyHandlerRegistry setupFieldHandlerRegistry()
    {
        return new JsonWriterPropertyHandlerRegistry();
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
        writer.write("public String toJson(List<%s> models, String arrayKey) {",
                modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("String json = null;");
        writer.write("if (models != null && arrayKey != null) {");
        writer.indent();
        writer.write("StringBuilder jsonBuilder = new StringBuilder();");
        writer.write("jsonBuilder.append(\"{\\\"\");");
        writer.write("jsonBuilder.append(arrayKey);");
        writer.write("jsonBuilder.append(\"\\\":[\");");
        writer.write("for (Iterator<%s> iter = models.iterator(); iter.hasNext(); ) {",
                modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("%s model = iter.next();", modelType.getParameterizedQualifiedSourceName());
        writer.write("String jsonValue = toJson(model);");
        writer.write("if (jsonValue != null) {");
        writer.indent();
        writer.write("jsonBuilder.append(jsonValue);");
        writer.outdent();
        writer.write("}");
        writer.write("if (iter.hasNext()) {");
        writer.indent();
        writer.write("jsonBuilder.append(\",\");");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.write("jsonBuilder.append(\"]}\");");
        writer.write("json = jsonBuilder.toString();");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("return json;");
        writer.write("}");
    }


    protected void writeSingle(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public String toJson(%s model) {", modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("String json = null;");
        writer.write("if (model != null) {");
        writer.indent();
        writer.write("StringBuilder jsonBuilder = new StringBuilder();");
        writer.write("jsonBuilder.append(\"{\");");

        // This creates all FieldHandler / FieldContexts and calls handleField()
        // in a loop
        handleProperties(writer);

        writer.write("jsonBuilder.append(\"}\");");
        writer.write("json = jsonBuilder.toString();");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("return json;");
        writer.write("}");
    }


    // ---------------------------------------------------- overwritten methods

    @Override
    protected void handleProperty(IndentedWriter writer, PropertyHandler fieldHandler, PropertyContext fieldContext,
            boolean hasNext) throws UnableToCompleteException
    {
        fieldHandler.comment(writer, fieldContext);
        fieldHandler.declare(writer, fieldContext);
        fieldHandler.readProperty(writer, fieldContext);
        fieldHandler.markupStart(writer, fieldContext);
        fieldHandler.writeValue(writer, fieldContext);
        fieldHandler.markupEnd(writer, fieldContext);
        if (hasNext)
        {
            writer.write("%s.append(\",\");", fieldContext.getVariableNames().getBuilderVariable());
        }
    }
}
