package name.pehl.piriti.rebind.json.fieldhandler;

import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.fieldhandler.AbstractCollectionFieldHandler;
import name.pehl.piriti.rebind.fieldhandler.AssignmentPolicy;
import name.pehl.piriti.rebind.fieldhandler.AssignmentType;
import name.pehl.piriti.rebind.fieldhandler.FieldContext;
import name.pehl.piriti.rebind.fieldhandler.FieldHandler;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * {@link FieldHandler} for collections.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class CollectionFieldHandler extends AbstractCollectionFieldHandler
{
    /**
     * TODO Javadoc
     * 
     * @param writer
     * @param fieldContext
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.xml.fieldhandler.ConverterFieldHandler#readInput(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.fieldhandler.FieldContext)
     */
    @Override
    public void readInput(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        JClassType parameterType = getTypeVariable(fieldContext);
        String nestedJsonValueVariable = fieldContext.newVariableName("NestedJsonValue");
        String nestedValueVariable = fieldContext.newVariableName("NestedValue");
        // The field context is created *without* a path. The nested field
        // handler must take care of this!
        FieldContext nestedFieldContext = new FieldContext(fieldContext.getTypeOracle(),
                fieldContext.getHandlerRegistry(), fieldContext.getModelType(), parameterType,
                fieldContext.getFieldName(), null, fieldContext.getFormat(), false, AssignmentType.MAPPING,
                AssignmentPolicy.FIELD_ONLY, nestedJsonValueVariable, nestedValueVariable,
                fieldContext.getBuilderVariable());
        FieldHandler nestedHandler = fieldContext.getHandlerRegistry().findFieldHandler(nestedFieldContext);
        if (!nestedHandler.isValid(writer, nestedFieldContext))
        {
            return;
        }

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
        writer.write("JSONArray jsonArray = %s.isArray();", jsonValue);
        writer.write("if (jsonArray != null) {");
        writer.indent();
        writer.write("int size = jsonArray.size();");
        String collectionImplementation = interfaceToImplementation.get(fieldContext.getFieldType().getErasedType()
                .getQualifiedSourceName());
        if (collectionImplementation == null)
        {
            // the field type is already an implementation
            collectionImplementation = fieldContext.getFieldType().getParameterizedQualifiedSourceName();
        }
        writer.write("%s = new %s<%s>();", fieldContext.getValueVariable(), collectionImplementation,
                parameterType.getQualifiedSourceName());
        writer.write("for (int i = 0; i < size; i++) {");
        writer.indent();
        writer.write("JSONValue %s = jsonArray.get(i);", nestedJsonValueVariable);
        writer.write("if (%1$s != null && %1$s.isNull() == null) {", nestedJsonValueVariable);
        writer.indent();
        nestedHandler.comment(writer, nestedFieldContext);
        nestedHandler.declare(writer, nestedFieldContext);
        nestedHandler.readInput(writer, nestedFieldContext);
        writer.write("if (%s != null) {", nestedFieldContext.getValueVariable());
        writer.indent();
        writer.write("%s.add(%s);", fieldContext.getValueVariable(), nestedFieldContext.getValueVariable());
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
    public void markupStart(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        CodeGeneration.appendJsonKey(writer, fieldContext);
    }


    @Override
    public void writeValue(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        JClassType parameterType = getTypeVariable(fieldContext);
        String nestedJsonValueVariable = fieldContext.newVariableName("NestedJsonValue");
        String nestedValueVariable = fieldContext.newVariableName("NestedValue");
        // The field context is created *without* a path. The nested field
        // handler must take care of this!
        FieldContext nestedFieldContext = new FieldContext(fieldContext.getTypeOracle(),
                fieldContext.getHandlerRegistry(), fieldContext.getModelType(), parameterType,
                fieldContext.getFieldName(), null, fieldContext.getFormat(), false, AssignmentType.MAPPING,
                AssignmentPolicy.FIELD_ONLY, nestedJsonValueVariable, nestedValueVariable,
                fieldContext.getBuilderVariable());
        FieldHandler nestedHandler = fieldContext.getHandlerRegistry().findFieldHandler(nestedFieldContext);
        if (!nestedHandler.isValid(writer, nestedFieldContext))
        {
            return;
        }

        writer.write("if (%s == null) {", fieldContext.getValueVariable());
        writer.indent();
        writer.write("%s.append(\"null\");", fieldContext.getBuilderVariable());
        writer.outdent();
        writer.write("}");
        writer.write("else {");
        writer.indent();
        // Iterate over values
        writer.write("%s.append(\"[\");", fieldContext.getBuilderVariable());
        writer.write("for (Iterator<%s> iter = %s.iterator(); iter.hasNext(); ) {",
                parameterType.getQualifiedSourceName(), fieldContext.getValueVariable());
        writer.indent();

        nestedHandler.comment(writer, nestedFieldContext);
        nestedHandler.declare(writer, nestedFieldContext);
        // Replace nestedHandler.readField(writer, nestedFieldContext) with
        writer.write("%s = iter.next();", nestedFieldContext.getValueVariable());
        // No nestedHandler.markupStart(writer, nestedFieldContext); since we're
        // in an JSON array
        nestedHandler.writeValue(writer, nestedFieldContext);
        // No nestedHandler.markupEnd(writer, nestedFieldContext); since we're
        // in an JSON array

        writer.write("if (iter.hasNext()) {", fieldContext.getValueVariable());
        writer.indent();
        writer.write("%s.append(\",\");", fieldContext.getBuilderVariable());
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.write("%s.append(\"]\");", fieldContext.getBuilderVariable());
        writer.outdent();
        writer.write("}");
    }


    /**
     * Empty!
     * 
     * @param writer
     * @param fieldContext
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.fieldhandler.FieldHandler#markupEnd(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.fieldhandler.FieldContext)
     */
    @Override
    public void markupEnd(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
    }
}
