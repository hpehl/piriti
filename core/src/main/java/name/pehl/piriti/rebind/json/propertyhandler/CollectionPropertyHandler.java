package name.pehl.piriti.rebind.json.propertyhandler;

import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.TypeContext;
import name.pehl.piriti.rebind.VariableNames;
import name.pehl.piriti.rebind.json.JsonPathUtils;
import name.pehl.piriti.rebind.propertyhandler.AbstractCollectionPropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerRegistry;

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
     * Returns <code>false</code> if this property context is used with a writer
     * and a JSONPath expression is used,
     * 
     * @param writer
     * @param propertyContext
     * @return
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.propertyhandler.AbstractArrayPropertyHandler#isValid(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        boolean valid = super.isValid(writer, propertyContext);
        if (valid && propertyContext.getTypeContext().isWriter() && JsonPathUtils.isJsonPath(propertyContext.getPath()))
        {
            CodeGeneration.skipProperty(writer, propertyContext,
                    "JSONPath expressions are not supported by this JsonWriter");
            return false;
        }
        return valid;
    }


    /**
     * TODO Javadoc
     * 
     * @param writer
     * @param propertyContext
     * @param propertyHandlerRegistry
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.xml.propertyhandler.ConverterPropertyHandler#readInput(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public void readInput(IndentedWriter writer, PropertyContext propertyContext,
            PropertyHandlerRegistry propertyHandlerRegistry) throws UnableToCompleteException
    {
        JClassType parameterType = getTypeVariable(propertyContext);
        String nestedJsonValueVariable = propertyContext.getVariableNames().newVariableName("NestedJsonValue");
        String nestedValueVariable = propertyContext.getVariableNames().newVariableName("NestedValue");
        // The field context is created *without* a path. The nested field
        // handler must take care of this!
        VariableNames nestedVariableNames = new VariableNames(nestedValueVariable, nestedJsonValueVariable,
                propertyContext.getVariableNames().getBuilderVariable());
        TypeContext nestedTypeContext = new TypeContext(propertyContext.getTypeContext().getTypeOracle(),
                propertyContext.getTypeContext().getRwType(), propertyContext.getTypeContext().getType(),
                nestedVariableNames);
        PropertyContext nestedPropertyContext = new PropertyContext(nestedTypeContext, parameterType,
                propertyContext.getName(), null, propertyContext.getFormat(), false, propertyContext.getConverter(),
                propertyContext.getGetter(), propertyContext.getSetter(), null, nestedVariableNames);
        PropertyHandler nestedHandler = propertyHandlerRegistry.findPropertyHandler(nestedPropertyContext);
        if (!nestedHandler.isValid(writer, nestedPropertyContext))
        {
            return;
        }

        String jsonValue = CodeGeneration.getOrSelectJson(writer, propertyContext);
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
            collectionImplementation = propertyContext.getType().getQualifiedSourceName();
        }
        writer.write("%s = new %s<%s>();", propertyContext.getVariableNames().getValueVariable(),
                collectionImplementation, parameterType.getQualifiedSourceName());
        writer.write("for (int i = 0; i < size; i++) {");
        writer.indent();
        writer.write("JSONValue %s = jsonArray.get(i);", nestedJsonValueVariable);
        writer.write("if (%1$s != null && %1$s.isNull() == null) {", nestedJsonValueVariable);
        writer.indent();
        nestedHandler.comment(writer, nestedPropertyContext);
        nestedHandler.declare(writer, nestedPropertyContext);
        nestedHandler.readInput(writer, nestedPropertyContext, propertyHandlerRegistry);
        writer.write("if (%s != null) {", nestedPropertyContext.getVariableNames().getValueVariable());
        writer.indent();
        writer.write("%s.add(%s);", propertyContext.getVariableNames().getValueVariable(), nestedPropertyContext
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
    public void writeValue(IndentedWriter writer, PropertyContext propertyContext,
            PropertyHandlerRegistry propertyHandlerRegistry) throws UnableToCompleteException
    {
        JClassType parameterType = getTypeVariable(propertyContext);
        String nestedJsonValueVariable = propertyContext.getVariableNames().newVariableName("NestedJsonValue");
        String nestedValueVariable = propertyContext.getVariableNames().newVariableName("NestedValue");
        // The field context is created *without* a path. The nested field
        // handler must take care of this!
        VariableNames nestedVariableNames = new VariableNames(nestedValueVariable, nestedJsonValueVariable,
                propertyContext.getVariableNames().getBuilderVariable());
        TypeContext nestedTypeContext = new TypeContext(propertyContext.getTypeContext().getTypeOracle(),
                propertyContext.getTypeContext().getRwType(), propertyContext.getTypeContext().getType(),
                nestedVariableNames);
        PropertyContext nestedPropertyContext = new PropertyContext(nestedTypeContext, parameterType,
                propertyContext.getName(), null, propertyContext.getFormat(), false, propertyContext.getConverter(),
                propertyContext.getGetter(), propertyContext.getSetter(), null, nestedVariableNames);
        PropertyHandler nestedHandler = propertyHandlerRegistry.findPropertyHandler(nestedPropertyContext);
        if (!nestedHandler.isValid(writer, nestedPropertyContext))
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

        nestedHandler.comment(writer, nestedPropertyContext);
        nestedHandler.declare(writer, nestedPropertyContext);
        // Replace nestedHandler.readField(writer, nestedFieldContext) with
        writer.write("%s = iter.next();", nestedPropertyContext.getVariableNames().getValueVariable());
        // No nestedHandler.markupStart(writer, nestedFieldContext); since we're
        // in an JSON array
        nestedHandler.writeValue(writer, nestedPropertyContext, propertyHandlerRegistry);
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
