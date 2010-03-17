package name.pehl.piriti.rebind.json;

import name.pehl.piriti.client.json.JsonField;
import name.pehl.piriti.rebind.AbstractReaderCreator;
import name.pehl.piriti.rebind.FieldContext;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.fieldhandler.FieldHandler;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;

/**
 * Class which generates the code necessary to map the annotated fields.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 137 $
 */
public class JsonReaderCreator extends AbstractReaderCreator
{
    public JsonReaderCreator(GeneratorContext context, JClassType interfaceType, String implName,
            String readerClassname, TreeLogger logger) throws UnableToCompleteException
    {
        super(context, interfaceType, implName, readerClassname, logger);
        this.handlerRegistry = new JsonFieldHandlerRegistry();
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
        readFromString(writer);
        writer.newline();

        readFromJsonObject(writer);
        writer.newline();

        readListFromString(writer);
        writer.newline();

        readListFromJsonArray(writer);
        writer.newline();

        internalRead(writer);
        writer.newline();
    }


    private void readFromString(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public %s read(String jsonString) {", modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("%s model = null;", modelType.getParameterizedQualifiedSourceName());
        writer.write("if (jsonString != null && jsonString.trim().length() != 0) {");
        writer.indent();
        writer.write("JSONValue jsonValue = JSONParser.parse(jsonString);");
        writer.write("if (jsonValue != null) {");
        writer.indent();
        writer.write("JSONObject jsonObject = jsonValue.isObject();");
        writer.write("if (jsonObject != null) {");
        writer.indent();
        writer.write("model = internalRead(jsonObject);");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.write("return model;");
        writer.outdent();
        writer.write("}");
    }


    private void readFromJsonObject(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public %s read(JSONObject jsonObject) {", modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("%s model = null;", modelType.getParameterizedQualifiedSourceName());
        writer.write("if (jsonObject != null) {");
        writer.indent();
        writer.write("model = internalRead(jsonObject);");
        writer.outdent();
        writer.write("}");
        writer.write("return model;");
        writer.outdent();
        writer.write("}");
    }


    private void readListFromString(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public List<%s> readList(String jsonString) {", modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("List<%1$s> models = new ArrayList<%1$s>();", modelType.getParameterizedQualifiedSourceName());
        writer.write("if (jsonString != null && jsonString.trim().length() != 0) {");
        writer.indent();
        writer.write("JSONValue jsonValue = JSONParser.parse(jsonString);");
        writer.write("if (jsonValue != null) {");
        writer.indent();
        writer.write("JSONArray jsonArray = jsonValue.isArray();");
        writer.write("if (jsonArray != null) {");
        writer.indent();
        writer.write("int size = jsonArray.size();");
        writer.write("for (int i = 0; i < size; i++) {");
        writer.indent();
        writer.write("JSONValue currentJsonValue = jsonArray.get(i);");
        writer.write("if (currentJsonValue != null) {");
        writer.indent();
        writer.write("JSONObject currentJsonObject = currentJsonValue.isObject();");
        writer.write("if (currentJsonObject != null) {");
        writer.indent();
        writer.write("%s model = internalRead(currentJsonObject);", modelType.getParameterizedQualifiedSourceName());
        writer.write("if (model != null) {");
        writer.indent();
        writer.write("models.add(model);");
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
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.write("return models;");
        writer.outdent();
        writer.write("}");
    }


    private void readListFromJsonArray(IndentedWriter writer) throws UnableToCompleteException
    {
        writer
                .write("public List<%s> readList(JSONArray jsonArray) {", modelType
                        .getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("List<%1$s> models = new ArrayList<%1$s>();", modelType.getParameterizedQualifiedSourceName());
        writer.write("if (jsonArray != null) {");
        writer.indent();
        writer.write("int size = jsonArray.size();");
        writer.write("for (int i = 0; i < size; i++) {");
        writer.indent();
        writer.write("JSONValue currentJsonValue = jsonArray.get(i);");
        writer.write("if (currentJsonValue != null) {");
        writer.indent();
        writer.write("JSONObject currentJsonObject = currentJsonValue.isObject();");
        writer.write("if (currentJsonObject != null) {");
        writer.indent();
        writer.write("%s model = internalRead(currentJsonObject);", modelType.getParameterizedQualifiedSourceName());
        writer.write("if (model != null) {");
        writer.indent();
        writer.write("models.add(model);");
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
        writer.write("return models;");
        writer.outdent();
        writer.write("}");
    }


    private void internalRead(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("private %s internalRead(JSONObject jsonObject) {", modelType
                .getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("%1$s model = new %1$s();", modelType.getParameterizedQualifiedSourceName());
        processFields(writer, modelType.getFields(), "jsonObject");
        writer.write("return model;");
        writer.outdent();
        writer.write("}");
    }


    // ---------------------------------------------------------- field methods

    private void processFields(IndentedWriter writer, JField[] fields, String jsonVariable)
            throws UnableToCompleteException
    {
        if (fields != null && fields.length != 0)
        {
            int counter = 0;
            for (JField field : fields)
            {
                JsonField jsonField = field.getAnnotation(JsonField.class);
                if (jsonField != null)
                {
                    writer.newline();
                    String jsonPath = calculateJsonPath(field, jsonField);
                    FieldContext fieldContext = new FieldContext(context.getTypeOracle(), handlerRegistry, modelType,
                            field.getType(), field.getName(), jsonPath, jsonField.format(), jsonVariable, "value"
                                    + counter);
                    FieldHandler handler = handlerRegistry.findFieldHandler(fieldContext);
                    if (handler != null)
                    {
                        if (handler.isValid(writer, fieldContext))
                        {
                            handler.writeComment(writer, fieldContext);
                            handler.writeDeclaration(writer, fieldContext);
                            handler.writeConverterCode(writer, fieldContext);
                            handler.writeAssignment(writer, fieldContext);
                            counter++;
                        }
                    }
                    // TODO What to do if no handler was found?
                }
            }
        }
    }


    private String calculateJsonPath(JField field, JsonField jsonField)
    {
        String jsonPath = jsonField.value();
        if (jsonPath == null || jsonPath.length() == 0)
        {
            jsonPath = field.getName();
        }
        return jsonPath;
    }
}
