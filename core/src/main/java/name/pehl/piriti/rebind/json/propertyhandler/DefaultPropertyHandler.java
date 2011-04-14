package name.pehl.piriti.rebind.json.propertyhandler;

import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerLookup;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * {@link PropertyHandler} implementation for types with an own
 * {@link JsonReader}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class DefaultPropertyHandler extends AbstractJsonPropertyHandler
{
    public DefaultPropertyHandler(TreeLogger logger)
    {
        super(logger);
    }


    /**
     * Returns <code>true</code> if the properties type is a class or interface,
     * <code>false</code> otherwise.
     * 
     * @param writer
     * @param propertyContext
     * @return
     * @see name.pehl.piriti.rebind.propertyhandler.AbstractPropertyHandler#isValid(name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        if (!propertyContext.isClassOrInterface())
        {
            skipProperty(writer, propertyContext, "Type is no class or interface");
            return false;
        }
        CodeGeneration.readerWriterInitialization(writer, propertyContext.getClassOrInterfaceType());
        return true;
    }


    @Override
    public void readInput(IndentedWriter writer, PropertyContext propertyContext,
            PropertyHandlerLookup propertyHandlerLookup) throws UnableToCompleteException
    {
        String readerVariable = propertyContext.getVariableNames().newVariableName("Reader");
        writer.write("JsonReader<%s> %s = jsonRegistry.getReader(%s.class);", propertyContext.getType()
                .getParameterizedQualifiedSourceName(), readerVariable, propertyContext.getType()
                .getQualifiedSourceName());
        writer.write("if (%s != null) {", readerVariable);
        writer.indent();
        getOrSelectJson(writer, propertyContext);
        writer.write("if (%s != null) {", jsonValueVariable);
        writer.indent();
        writer.write("if (%s.isNull() == null) {", jsonValueVariable);
        writer.indent();
        String jsonObject = propertyContext.getVariableNames().newVariableName("jsonObject");
        writer.write("JSONObject %s = %s.isObject();", jsonObject, jsonValueVariable);
        writer.write("if (%s != null) {", jsonObject);
        writer.indent();
        writer.write("%s = %s.read(%s);", propertyContext.getVariableNames().getValueVariable(), readerVariable,
                jsonObject);
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.write("else {");
        writer.indent();
        super.readInput(writer, propertyContext, propertyHandlerLookup);
        writer.outdent();
        writer.write("}");
    }


    @Override
    public void writeValue(IndentedWriter writer, PropertyContext propertyContext,
            PropertyHandlerLookup propertyHandlerLookup) throws UnableToCompleteException
    {
        writer.write("if (%s == null) {", propertyContext.getVariableNames().getValueVariable());
        writer.indent();
        writer.write("%s.append(\"null\");", propertyContext.getVariableNames().getBuilderVariable());
        writer.outdent();
        writer.write("}");
        writer.write("else {");
        writer.indent();
        String writerVariable = propertyContext.getVariableNames().newVariableName("Writer");
        writer.write("JsonWriter<%s> %s = jsonRegistry.getWriter(%s.class);", propertyContext.getType()
                .getParameterizedQualifiedSourceName(), writerVariable, propertyContext.getType()
                .getQualifiedSourceName());
        writer.write("if (%s != null) {", writerVariable);
        writer.indent();
        writer.write("%s.append(%s.toJson(%s));", propertyContext.getVariableNames().getBuilderVariable(),
                writerVariable, propertyContext.getVariableNames().getValueVariable());
        writer.outdent();
        writer.write("}");
        writer.write("else {");
        writer.indent();
        super.writeValue(writer, propertyContext, propertyHandlerLookup);
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
    }
}
