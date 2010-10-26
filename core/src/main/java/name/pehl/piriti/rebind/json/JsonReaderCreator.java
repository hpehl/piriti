package name.pehl.piriti.rebind.json;

import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.propertyhandler.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerRegistry;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * Creator for {@linkplain JsonReader}s.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 137 $
 */
public class JsonReaderCreator extends AbstractJsonCreator
{
    // --------------------------------------------------------- initialization

    public JsonReaderCreator(GeneratorContext context, JClassType interfaceType, String implName,
            String readerClassname, TreeLogger logger) throws UnableToCompleteException
    {
        super(context, interfaceType, implName, readerClassname, logger);
    }


    @Override
    protected PropertyHandlerRegistry setupFieldHandlerRegistry()
    {
        return new JsonReaderPropertyHandlerRegistry();
    }


    // --------------------------------------------------------- create methods

    @Override
    protected void createMemberVariables(IndentedWriter writer) throws UnableToCompleteException
    {
        super.createMemberVariables(writer);
        writer.write("private Map<String,%s> idMap;", modelType.getQualifiedSourceName());
    }


    @Override
    protected void createConstructorBody(IndentedWriter writer)
    {
        super.createConstructorBody(writer);
        writer.write("this.idMap = new HashMap<String,%s>();", modelType.getQualifiedSourceName());
    }


    @Override
    protected void createMethods(IndentedWriter writer) throws UnableToCompleteException
    {
        readListFromStringUsingFirstKey(writer);
        writer.newline();

        readListFromStringUsingNamedKey(writer);
        writer.newline();

        readListFromJsonObjectUsingFirstKey(writer);
        writer.newline();

        readListFromJsonObjectUsingNamedKey(writer);
        writer.newline();

        readListFromJsonArray(writer);
        writer.newline();

        readFromString(writer);
        writer.newline();

        readFromJsonObject(writer);
        writer.newline();

        internalRead(writer);
        writer.newline();

        CodeGeneration.idRef(writer, modelType);
    }


    // ------------------------------------------------------ read list methods

    protected void readListFromStringUsingFirstKey(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public List<%s> readList(String jsonString) {", modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("List<%s> models = null;", modelType.getParameterizedQualifiedSourceName());
        writer.write("if (jsonString != null && jsonString.trim().length() != 0) {");
        writer.indent();
        writer.write("JSONObject jsonObject = new JsonParser().parse(jsonString);");
        writer.write("if (jsonObject != null) {");
        writer.indent();
        writer.write("Set<String> keys = jsonObject.keySet();");
        writer.write("if (keys != null && !keys.isEmpty()) {");
        writer.indent();
        writer.write("String arrayKey = keys.iterator().next();");
        writer.write("JSONValue jsonValue = jsonObject.get(arrayKey);");
        writer.write("if (jsonValue != null) {");
        writer.indent();
        writer.write("JSONArray jsonArray = jsonValue.isArray();");
        writer.write("if (jsonArray != null) {");
        writer.indent();
        writer.write("models = new ArrayList<%s>();", modelType.getParameterizedQualifiedSourceName());
        writer.write("models = readList(jsonArray);");
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


    protected void readListFromStringUsingNamedKey(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public List<%s> readList(String jsonString, String arrayKey) {",
                modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("List<%s> models = null;", modelType.getParameterizedQualifiedSourceName());
        writer.write("if (jsonString != null && jsonString.trim().length() != 0) {");
        writer.indent();
        writer.write("JSONObject jsonObject = new JsonParser().parse(jsonString);");
        writer.write("if (jsonObject != null) {");
        writer.indent();
        writer.write("if (arrayKey != null) {");
        writer.indent();
        writer.write("JSONValue jsonValue = jsonObject.get(arrayKey);");
        writer.write("if (jsonValue != null) {");
        writer.indent();
        writer.write("JSONArray jsonArray = jsonValue.isArray();");
        writer.write("if (jsonArray != null) {");
        writer.indent();
        writer.write("models = new ArrayList<%s>();", modelType.getParameterizedQualifiedSourceName());
        writer.write("models = readList(jsonArray);");
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


    protected void readListFromJsonObjectUsingFirstKey(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public List<%s> readList(JSONObject jsonObject) {",
                modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("List<%s> models = null;", modelType.getParameterizedQualifiedSourceName());
        writer.write("if (jsonObject != null) {");
        writer.indent();
        writer.write("Set<String> keys = jsonObject.keySet();");
        writer.write("if (keys != null && !keys.isEmpty()) {");
        writer.indent();
        writer.write("String arrayKey = keys.iterator().next();");
        writer.write("JSONValue jsonValue = jsonObject.get(arrayKey);");
        writer.write("if (jsonValue != null) {");
        writer.indent();
        writer.write("JSONArray jsonArray = jsonValue.isArray();");
        writer.write("if (jsonArray != null) {");
        writer.indent();
        writer.write("models = new ArrayList<%s>();", modelType.getParameterizedQualifiedSourceName());
        writer.write("models = readList(jsonArray);");
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


    protected void readListFromJsonObjectUsingNamedKey(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public List<%s> readList(JSONObject jsonObject, String arrayKey) {",
                modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("List<%s> models = null;", modelType.getParameterizedQualifiedSourceName());
        writer.write("if (jsonObject != null) {");
        writer.indent();
        writer.write("if (arrayKey != null) {");
        writer.indent();
        writer.write("JSONValue jsonValue = jsonObject.get(arrayKey);");
        writer.write("if (jsonValue != null) {");
        writer.indent();
        writer.write("JSONArray jsonArray = jsonValue.isArray();");
        writer.write("if (jsonArray != null) {");
        writer.indent();
        writer.write("models = new ArrayList<%s>();", modelType.getParameterizedQualifiedSourceName());
        writer.write("models = readList(jsonArray);");
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


    protected void readListFromJsonArray(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public List<%s> readList(JSONArray jsonArray) {", modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("List<%s> models = null;", modelType.getParameterizedQualifiedSourceName());
        writer.write("if (jsonArray != null) {");
        writer.indent();
        writer.write("models = new ArrayList<%s>();", modelType.getParameterizedQualifiedSourceName());
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


    // ---------------------------------------------------- read single methods

    protected void readFromString(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public %s read(String jsonString) {", modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("%s model = null;", modelType.getParameterizedQualifiedSourceName());
        writer.write("if (jsonString != null && jsonString.trim().length() != 0) {");
        writer.indent();
        writer.write("JSONObject jsonObject = new JsonParser().parse(jsonString);");
        writer.write("if (jsonObject != null) {");
        writer.indent();
        writer.write("model = internalRead(jsonObject);");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.write("return model;");
        writer.outdent();
        writer.write("}");
    }


    protected void readFromJsonObject(IndentedWriter writer) throws UnableToCompleteException
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


    // --------------------------------------------------------- helper methods

    protected void internalRead(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("private %s internalRead(JSONObject jsonObject) {",
                modelType.getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("%1$s model = new %1$s();", modelType.getParameterizedQualifiedSourceName());
        handleProperties(writer);
        writer.write("return model;");
        writer.outdent();
        writer.write("}");
    }


    // ---------------------------------------------------- overwritten methods

    @Override
    protected void handleProperty(IndentedWriter writer, PropertyHandler fieldHandler, PropertyContext fieldContext,
            boolean hasNext) throws UnableToCompleteException
    {
        fieldHandler.comment(writer, fieldContext);
        fieldHandler.declare(writer, fieldContext);
        fieldHandler.readInput(writer, fieldContext);
        fieldHandler.assign(writer, fieldContext);
    }
}
