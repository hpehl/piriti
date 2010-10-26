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
     * @param propertyContext
     * @return
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.propertyhandler.PropertyHandler#isValid(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        if (!TypeUtils.isBoolean(propertyContext.getType()))
        {
            CodeGeneration.skipProperty(writer, propertyContext, "Type is neither boolean nor Boolean");
        }
        return true;
    }


    /**
     * @param writer
     * @param propertyContext
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.propertyhandler.PropertyHandler#readInput(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public void readInput(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        String jsonValue = CodeGeneration.getOrSelectJson(writer, propertyContext);
        writer.write("if (%s != null) {", jsonValue);
        writer.indent();
        writer.write("if (%s.isNull() == null) {", jsonValue);
        writer.indent();
        String jsonBoolean = propertyContext.getVariableNames().newVariableName("AsJsonBoolean");
        writer.write("JSONBoolean %s = %s.isBoolean();", jsonBoolean, jsonValue);
        writer.write("if (%s != null) {", jsonBoolean);
        writer.indent();
        writer.write("%s = %s.booleanValue();", propertyContext.getVariableNames().getValueVariable(), jsonBoolean);
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
    }


    @Override
    public void markupStart(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        CodeGeneration.appendJsonKey(writer, propertyContext);
    }


    @Override
    public void writeValue(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        if (propertyContext.getType().isPrimitive() == null)
        {
            // if the Boolean object is null, append false
            writer.write("if (%s == null) {", propertyContext.getVariableNames().getValueVariable());
            writer.indent();
            writer.write("%s.append(\"false\");", propertyContext.getVariableNames().getBuilderVariable());
            writer.outdent();
            writer.write("}");
            writer.write("else {");
            writer.indent();
            CodeGeneration.appendJsonValue(writer, propertyContext, false);
            writer.outdent();
            writer.write("}");
        }
        else
        {
            CodeGeneration.appendJsonValue(writer, propertyContext, false);
        }
    }


    /**
     * Empty!
     * 
     * @param writer
     * @param propertyContext
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.propertyhandler.PropertyHandler#markupEnd(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public void markupEnd(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
    }
}
