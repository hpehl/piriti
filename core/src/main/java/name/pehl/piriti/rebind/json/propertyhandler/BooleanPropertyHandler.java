package name.pehl.piriti.rebind.json.propertyhandler;

import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.TypeUtils;
import name.pehl.piriti.rebind.propertyhandler.AbstractPropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyContext;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * @author $Author$
 * @version $Date$ $Revision: 364
 *          $
 */
public class BooleanPropertyHandler extends AbstractPropertyHandler
{
    /**
     * Returns <code>true</code> if the field type is boolean or Boolean,
     * <code>false</code> otherwise.
     * 
     * @param writer
     * @param fieldContext
     * @return
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.propertyhandler.PropertyHandler#isValid(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException
    {
        if (!TypeUtils.isBoolean(fieldContext.getFieldType()))
        {
            CodeGeneration.skipField(writer, fieldContext, "Type is neither boolean nor Boolean");
        }
        return true;
    }


    /**
     * @param writer
     * @param fieldContext
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.propertyhandler.PropertyHandler#readInput(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public void readInput(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException
    {
        // If there's a path then get the JSON value using this path,
        // otherwise it is expected that the JSON value is the inputVariable
        // itself (e.g. an array of strings has no path information for the
        // array elements)
        String jsonValue = fieldContext.newVariableName("AsJsonValue");
        if (fieldContext.getPath() != null)
        {
            writer.write("JSONValue %s = %s.get(\"%s\");", jsonValue, fieldContext.getInputVariable(),
                    fieldContext.getPath());
        }
        else
        {
            writer.write("JSONValue %s = %s;", jsonValue, fieldContext.getInputVariable());
        }
        writer.write("if (%s != null) {", jsonValue);
        writer.indent();
        writer.write("if (%s.isNull() == null) {", jsonValue);
        writer.indent();
        String jsonBoolean = fieldContext.newVariableName("AsJsonBoolean");
        writer.write("JSONBoolean %s = %s.isBoolean();", jsonBoolean, jsonValue);
        writer.write("if (%s != null) {", jsonBoolean);
        writer.indent();
        writer.write("%s = %s.booleanValue();", fieldContext.getValueVariable(), jsonBoolean);
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
    }


    @Override
    public void markupStart(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException
    {
        CodeGeneration.appendJsonKey(writer, fieldContext);
    }


    @Override
    public void writeValue(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException
    {
        if (fieldContext.getFieldType().isPrimitive() == null)
        {
            // if the Boolean object is null, append false
            writer.write("if (%s == null) {", fieldContext.getValueVariable());
            writer.indent();
            writer.write("%s.append(\"false\");", fieldContext.getBuilderVariable());
            writer.outdent();
            writer.write("}");
            writer.write("else {");
            writer.indent();
            CodeGeneration.appendJsonValue(writer, fieldContext, false);
            writer.outdent();
            writer.write("}");
        }
        else
        {
            CodeGeneration.appendJsonValue(writer, fieldContext, false);
        }
    }


    /**
     * Empty!
     * 
     * @param writer
     * @param fieldContext
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.propertyhandler.PropertyHandler#markupEnd(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public void markupEnd(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException
    {
    }
}
