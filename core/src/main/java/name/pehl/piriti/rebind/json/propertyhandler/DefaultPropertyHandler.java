package name.pehl.piriti.rebind.json.propertyhandler;

import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;

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
    protected void readInputUsingReader(IndentedWriter writer, PropertyContext propertyContext)
    {
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
    }


    @Override
    protected void writeValueUsingWriter(IndentedWriter writer, PropertyContext propertyContext)
    {
        writer.write("%s.append(%s.toJson(%s));", propertyContext.getVariableNames().getBuilderVariable(),
                writerVariable, propertyContext.getVariableNames().getValueVariable());
    }
}
