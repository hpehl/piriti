package name.pehl.piriti.rebind.json;

import java.util.HashMap;
import java.util.Map;

import name.pehl.piriti.client.json.JsonField;
import name.pehl.piriti.client.json.JsonFields;
import name.pehl.piriti.rebind.AbstractWriterCreator;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.fieldhandler.AssignmentPolicy;
import name.pehl.piriti.rebind.fieldhandler.FieldAnnotation;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;

/**
 * Class which generates the code necessary to serialize a POJO to JSON.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class JsonWriterCreator extends AbstractWriterCreator
{
    public JsonWriterCreator(GeneratorContext context, JClassType interfaceType, String implName,
            String readerClassname, TreeLogger logger) throws UnableToCompleteException
    {
        super(context, interfaceType, implName, readerClassname, logger);
    }


    @Override
    protected void createImports(IndentedWriter writer) throws UnableToCompleteException
    {
        super.createImports(writer);
        writer.write("import com.google.gwt.json.client.*;");
        writer.write("import name.pehl.piriti.client.json.*;");
    }


    @Override
    protected void createMemberVariables(IndentedWriter writer) throws UnableToCompleteException
    {
        super.createMemberVariables(writer);
        writer.write("private JsonRegistry jsonRegistry;");
    }


    @Override
    protected void createConstructorBody(IndentedWriter writer)
    {
        super.createConstructorBody(writer);
        writer.write("this.jsonRegistry = JsonGinjector.INJECTOR.getJsonRegistry();");
        writer.write("this.jsonRegistry.register(%s.class, this);", modelType.getQualifiedSourceName());
    }


    @Override
    protected void createMethods(IndentedWriter writer) throws UnableToCompleteException
    {
        super.createMethods(writer);

        writeList(writer);
        writer.newline();

        writeSingle(writer);
        writer.newline();
    }


    protected void writeList(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public String toJson(List<%s> values, String arrayKey) {",
                modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("String json = null;");
        writer.write("if (values != null && arrayKey != null) {");
        writer.indent();
        writer.write("StringBuilder jsonBuilder = new StringBuilder();");
        writer.write("jsonBuilder.append(\"{\");");
        writer.write("jsonBuilder.append(arrayKey);");
        writer.write("jsonBuilder.append(\":[\"");
        writer.write("for (Iterator<%s> iter = values.iterator(); iter.hasNext(); ) {",
                modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("%s value = iter.next();", modelType.getParameterizedQualifiedSourceName());
        writer.write("String jsonValue = toJson(value);");
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
        writer.write("public String toJson(%s value) {", modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("String json = null;");
        writer.write("if (value != null) {");
        writer.indent();
        writer.write("StringBuilder jsonBuilder = new StringBuilder();");

        handleFields(writer);

        writer.write("json = jsonBuilder.toString();");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("return json;");
        writer.write("}");
    }


    protected void handleFields(IndentedWriter writer) throws UnableToCompleteException
    {
        int counter = 0;
        Map<String, FieldAnnotation<JsonField>> fields = findFieldAnnotations();
        for (FieldAnnotation<JsonField> fieldAnnotation : fields.values())
        {
            // String jsonPath = calculateJsonPath(fieldAnnotation.field,
            // fieldAnnotation.annotation);
            // FieldContext fieldContext = new
            // FieldContext(context.getTypeOracle(), handlerRegistry, modelType,
            // fieldAnnotation.field.getType(), fieldAnnotation.field.getName(),
            // jsonPath,
            // fieldAnnotation.annotation.format(), false,
            // AssignmentType.MAPPING,
            // fieldAnnotation.assignmentPolicy, "jsonObject", "value" +
            // counter);
            // FieldHandler handler =
            // handlerRegistry.findFieldHandler(fieldContext);
            // if (handler != null && handler.isValid(writer, fieldContext))
            // {
            // writer.newline();
            // handler.writeComment(writer, fieldContext);
            // handler.writeDeclaration(writer, fieldContext);
            // handler.writeConverterCode(writer, fieldContext);
            // handler.writeAssignment(writer, fieldContext);
            // counter++;
            // }
        }
    }


    /**
     * TODO Documentation
     * 
     * @return
     */
    private Map<String, FieldAnnotation<JsonField>> findFieldAnnotations()
    {
        Map<String, FieldAnnotation<JsonField>> fields = new HashMap<String, FieldAnnotation<JsonField>>();

        // Step 1: Add all JsonField annotations in the JsonFields annotation
        // from the interfaceType
        JsonFields interfaceTypeFields = interfaceType.getAnnotation(JsonFields.class);
        if (interfaceTypeFields != null)
        {
            JsonField[] annotations = interfaceTypeFields.value();
            for (JsonField annotation : annotations)
            {
                JField field = modelType.getField(annotation.name());
                if (field != null)
                {
                    fields.put(field.getName(), new FieldAnnotation<JsonField>(field, annotation,
                            AssignmentPolicy.SETTER_FIRST));
                }
                // TODO Is it an error if field == null?
            }
        }

        // Step 2: Add all JsonField annotations of the modelType fields. If
        // there's already an entry for the field from step 1, it will be
        // overwritten!
        JField[] modelTypeFields = findAnnotatedFields(modelType, JsonField.class);
        for (JField field : modelTypeFields)
        {
            JsonField annotation = field.getAnnotation(JsonField.class);
            fields.put(field.getName(), new FieldAnnotation<JsonField>(field, annotation, AssignmentPolicy.FIELD_ONLY));
        }
        return fields;
    }


    protected String calculateJsonPath(JField field, JsonField jsonField)
    {
        String jsonPath = jsonField.value();
        if (jsonPath == null || jsonPath.length() == 0)
        {
            jsonPath = field.getName();
        }
        return jsonPath;
    }
}
