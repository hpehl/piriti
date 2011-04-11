package name.pehl.piriti.rebind.json;

import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;
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

    public JsonReaderCreator(GeneratorContext generatorContext, JClassType rwType, String implName, String rwClassname,
            TreeLogger logger) throws UnableToCompleteException
    {
        super(generatorContext, rwType, implName, rwClassname, logger);
    }


    @Override
    protected PropertyHandlerRegistry setupPropertyHandlerRegistry()
    {
        return new JsonReaderPropertyHandlerRegistry(logger);
    }


    // --------------------------------------------------------- create methods

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

        CodeGeneration.idRef(writer, typeContext.getType());
    }


    // ------------------------------------------------------ read list methods

    protected void readListFromStringUsingFirstKey(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public List<%s> readList(String jsonString) {", typeContext.getType()
                .getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("List<%s> models = null;", typeContext.getType().getParameterizedQualifiedSourceName());
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
        writer.write("models = new ArrayList<%s>();", typeContext.getType().getParameterizedQualifiedSourceName());
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
        writer.write("public List<%s> readList(String jsonString, String arrayKey) {", typeContext.getType()
                .getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("List<%s> models = null;", typeContext.getType().getParameterizedQualifiedSourceName());
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
        writer.write("models = new ArrayList<%s>();", typeContext.getType().getParameterizedQualifiedSourceName());
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
        writer.write("public List<%s> readList(JSONObject jsonObject) {", typeContext.getType()
                .getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("List<%s> models = null;", typeContext.getType().getParameterizedQualifiedSourceName());
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
        writer.write("models = new ArrayList<%s>();", typeContext.getType().getParameterizedQualifiedSourceName());
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
        writer.write("public List<%s> readList(JSONObject jsonObject, String arrayKey) {", typeContext.getType()
                .getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("List<%s> models = null;", typeContext.getType().getParameterizedQualifiedSourceName());
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
        writer.write("models = new ArrayList<%s>();", typeContext.getType().getParameterizedQualifiedSourceName());
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
        writer.write("public List<%s> readList(JSONArray jsonArray) {", typeContext.getType()
                .getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("List<%s> models = null;", typeContext.getType().getParameterizedQualifiedSourceName());
        writer.write("if (jsonArray != null) {");
        writer.indent();
        writer.write("models = new ArrayList<%s>();", typeContext.getType().getParameterizedQualifiedSourceName());
        writer.write("int size = jsonArray.size();");
        writer.write("for (int i = 0; i < size; i++) {");
        writer.indent();
        writer.write("JSONValue currentJsonValue = jsonArray.get(i);");
        writer.write("if (currentJsonValue != null) {");
        writer.indent();
        writer.write("JSONObject currentJsonObject = currentJsonValue.isObject();");
        writer.write("if (currentJsonObject != null) {");
        writer.indent();
        writer.write("%s %s = internalRead(currentJsonObject);", typeContext.getType()
                .getParameterizedQualifiedSourceName(), typeContext.getVariableNames().getInstanceVariable());
        writer.write("if (%s != null) {", typeContext.getVariableNames().getInstanceVariable());
        writer.indent();
        writer.write("models.add(%s);", typeContext.getVariableNames().getInstanceVariable());
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
        writer.write("public %s read(String jsonString) {", typeContext.getType().getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("%s %s = null;", typeContext.getType().getParameterizedQualifiedSourceName(), typeContext
                .getVariableNames().getInstanceVariable());
        writer.write("if (jsonString != null && jsonString.trim().length() != 0) {");
        writer.indent();
        writer.write("JSONObject jsonObject = new JsonParser().parse(jsonString);");
        writer.write("if (jsonObject != null) {");
        writer.indent();
        writer.write("%s = internalRead(jsonObject);", typeContext.getVariableNames().getInstanceVariable());
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.write("return %s;", typeContext.getVariableNames().getInstanceVariable());
        writer.outdent();
        writer.write("}");
    }


    protected void readFromJsonObject(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public %s read(JSONObject jsonObject) {", typeContext.getType()
                .getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("%s %s = null;", typeContext.getType().getParameterizedQualifiedSourceName(), typeContext
                .getVariableNames().getInstanceVariable());
        writer.write("if (jsonObject != null) {");
        writer.indent();
        writer.write("%s = internalRead(jsonObject);", typeContext.getVariableNames().getInstanceVariable());
        writer.outdent();
        writer.write("}");
        writer.write("return %s;", typeContext.getVariableNames().getInstanceVariable());
        writer.outdent();
        writer.write("}");
    }


    // --------------------------------------------------------- helper methods

    protected void internalRead(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("private %s internalRead(JSONObject %s) {", typeContext.getType()
                .getParameterizedQualifiedSourceName(), typeContext.getVariableNames().getInputVariable());
        writer.indent();
        // TODO Use InstanceCreator<T, C> if specified
        writer.write("%1$s %2$s = new %1$s();", typeContext.getType().getParameterizedQualifiedSourceName(),
                typeContext.getVariableNames().getInstanceVariable());
        handleProperties(writer);
        writer.write("return %s;", typeContext.getVariableNames().getInstanceVariable());
        writer.outdent();
        writer.write("}");
    }


    // ---------------------------------------------------- overwritten methods

    @Override
    protected void handleProperty(IndentedWriter writer, PropertyHandler propertyHandler,
            PropertyContext propertyContext, boolean hasNext) throws UnableToCompleteException
    {
        propertyHandler.comment(writer, propertyContext);
        propertyHandler.declare(writer, propertyContext);
        propertyHandler.readInput(writer, propertyContext, propertyHandlerRegistry);
        propertyHandler.assign(writer, propertyContext);
    }
}
