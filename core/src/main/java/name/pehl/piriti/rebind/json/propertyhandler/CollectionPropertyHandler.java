package name.pehl.piriti.rebind.json.propertyhandler;

import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.propertyhandler.AbstractCollectionPropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.VariableNames;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * {@link PropertyHandler} for collections.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class CollectionPropertyHandler extends AbstractCollectionPropertyHandler
{
    /**
     * TODO Javadoc
     * 
     * @param writer
     * @param propertyContext
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.xml.propertyhandler.ConverterPropertyHandler#readInput(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public void readInput(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        JClassType parameterType = getTypeVariable(propertyContext);
        String nestedJsonValueVariable = propertyContext.getVariableNames().newVariableName("NestedJsonValue");
        String nestedValueVariable = propertyContext.getVariableNames().newVariableName("NestedValue");
        // The field context is created *without* a path. The nested field
        // handler must take care of this!
        VariableNames variableNames = new VariableNames(nestedJsonValueVariable, nestedValueVariable, propertyContext
                .getVariableNames().getBuilderVariable());
        PropertyContext nestedFieldContext = new PropertyContext(propertyContext.getTypeOracle(),
                propertyContext.getHandlerRegistry(), propertyContext.getReaderOrWriter(), propertyContext.getClazz(),
                parameterType, propertyContext.getName(), null, propertyContext.getFormat(), false,
                propertyContext.getConverter(), null, propertyContext.getGetter(), propertyContext.getSetter(),
                variableNames);
        PropertyHandler nestedHandler = propertyContext.getHandlerRegistry().findPropertyHandler(nestedFieldContext);
        if (!nestedHandler.isValid(writer, nestedFieldContext))
        {
            return;
        }

        // If there's a path then get the JSON value using this path,
        // otherwise it is expected that the JSON value is the inputVariable
        // itself (e.g. an array of strings has no path information for the
        // array elements)
        String jsonValue = propertyContext.getVariableNames().newVariableName("AsJsonValue");
        if (propertyContext.getPath() != null)
        {
            writer.write("JSONValue %s = %s.get(\"%s\");", jsonValue, propertyContext.getVariableNames()
                    .getInputVariable(), propertyContext.getPath());
        }
        else
        {
            writer.write("JSONValue %s = %s;", jsonValue, propertyContext.getVariableNames().getInputVariable());
        }
        writer.write("if (%s != null) {", jsonValue);
        writer.indent();
        writer.write("JSONArray jsonArray = %s.isArray();", jsonValue);
        writer.write("if (jsonArray != null) {");
        writer.indent();
        writer.write("int size = jsonArray.size();");
        String collectionImplementation = interfaceToImplementation.get(propertyContext.getType().getErasedType()
                .getQualifiedSourceName());
        if (collectionImplementation == null)
        {
            // the field type is already an implementation
            collectionImplementation = propertyContext.getType().getParameterizedQualifiedSourceName();
        }
        writer.write("%s = new %s<%s>();", propertyContext.getVariableNames().getValueVariable(),
                collectionImplementation, parameterType.getQualifiedSourceName());
        writer.write("for (int i = 0; i < size; i++) {");
        writer.indent();
        writer.write("JSONValue %s = jsonArray.get(i);", nestedJsonValueVariable);
        writer.write("if (%1$s != null && %1$s.isNull() == null) {", nestedJsonValueVariable);
        writer.indent();
        nestedHandler.comment(writer, nestedFieldContext);
        nestedHandler.declare(writer, nestedFieldContext);
        nestedHandler.readInput(writer, nestedFieldContext);
        writer.write("if (%s != null) {", nestedFieldContext.getVariableNames().getValueVariable());
        writer.indent();
        writer.write("%s.add(%s);", propertyContext.getVariableNames().getValueVariable(), nestedFieldContext
                .getVariableNames().getValueVariable());
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
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
        JClassType parameterType = getTypeVariable(propertyContext);
        String nestedJsonValueVariable = propertyContext.getVariableNames().newVariableName("NestedJsonValue");
        String nestedValueVariable = propertyContext.getVariableNames().newVariableName("NestedValue");
        // The field context is created *without* a path. The nested field
        // handler must take care of this!
        VariableNames variableNames = new VariableNames(nestedJsonValueVariable, nestedValueVariable, propertyContext
                .getVariableNames().getBuilderVariable());
        PropertyContext nestedFieldContext = new PropertyContext(propertyContext.getTypeOracle(),
                propertyContext.getHandlerRegistry(), propertyContext.getReaderOrWriter(), propertyContext.getClazz(),
                parameterType, propertyContext.getName(), null, propertyContext.getFormat(), false,
                propertyContext.getConverter(), null, propertyContext.getGetter(), propertyContext.getSetter(),
                variableNames);
        PropertyHandler nestedHandler = propertyContext.getHandlerRegistry().findPropertyHandler(nestedFieldContext);
        if (!nestedHandler.isValid(writer, nestedFieldContext))
        {
            return;
        }

        writer.write("if (%s == null) {", propertyContext.getVariableNames().getValueVariable());
        writer.indent();
        writer.write("%s.append(\"null\");", propertyContext.getVariableNames().getBuilderVariable());
        writer.outdent();
        writer.write("}");
        writer.write("else {");
        writer.indent();
        // Iterate over values
        writer.write("%s.append(\"[\");", propertyContext.getVariableNames().getBuilderVariable());
        writer.write("for (Iterator<%s> iter = %s.iterator(); iter.hasNext(); ) {",
                parameterType.getQualifiedSourceName(), propertyContext.getVariableNames().getValueVariable());
        writer.indent();

        nestedHandler.comment(writer, nestedFieldContext);
        nestedHandler.declare(writer, nestedFieldContext);
        // Replace nestedHandler.readField(writer, nestedFieldContext) with
        writer.write("%s = iter.next();", nestedFieldContext.getVariableNames().getValueVariable());
        // No nestedHandler.markupStart(writer, nestedFieldContext); since we're
        // in an JSON array
        nestedHandler.writeValue(writer, nestedFieldContext);
        // No nestedHandler.markupEnd(writer, nestedFieldContext); since we're
        // in an JSON array

        writer.write("if (iter.hasNext()) {", propertyContext.getVariableNames().getValueVariable());
        writer.indent();
        writer.write("%s.append(\",\");", propertyContext.getVariableNames().getBuilderVariable());
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.write("%s.append(\"]\");", propertyContext.getVariableNames().getBuilderVariable());
        writer.outdent();
        writer.write("}");
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
