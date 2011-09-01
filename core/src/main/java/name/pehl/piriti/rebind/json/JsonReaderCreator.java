package name.pehl.piriti.rebind.json;

import java.util.logging.Level;

import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.rebind.AbstractReaderCreator;
import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.VariableNames;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerLookup;

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
public class JsonReaderCreator extends AbstractReaderCreator
{
    // --------------------------------------------------------- initialization

    public JsonReaderCreator(GeneratorContext generatorContext, JClassType rwType, String implName, String rwClassname,
            TreeLogger logger) throws UnableToCompleteException
    {
        super(generatorContext, rwType, implName, rwClassname, logger);
    }


    @Override
    protected VariableNames setupVariableNames()
    {
        return JsonUtils.newVariableNames();
    }


    @Override
    protected PropertyHandlerLookup setupPropertyHandlerLookup()
    {
        return JsonUtils.newPropertyHandlerLookup(logger);
    }


    // --------------------------------------------------------- create methods

    @Override
    protected void createImports(IndentedWriter writer) throws UnableToCompleteException
    {
        super.createImports(writer);
        JsonUtils.createImports(writer);
    }


    @Override
    protected void createConstructorBody(IndentedWriter writer) throws UnableToCompleteException
    {
        // Order is important!
        JsonUtils.createConstructorBody(writer);
        super.createConstructorBody(writer);
    }


    @Override
    public void createReaderMethods(IndentedWriter writer) throws UnableToCompleteException
    {
        readListFromStringUsingFirstKey(writer);
        writer.newline();

        readListFromStringUsingNamedKey(writer);
        writer.newline();

        readListFromJsonObjectUsingFirstKey(writer);
        writer.newline();

        readListFromJsonObjectUsingNamedKey(writer);
        writer.newline();

        readList(writer);
        writer.newline();

        readSingleFromString(writer);
        writer.newline();

        readSingleFromJsonObject(writer);
        writer.newline();

        readSingle(writer);
        writer.newline();
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
        writer.write("JSONObject jsonObject = JSONParser.parseStrict(jsonString).isObject();");
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
        writer.write("JSONObject jsonObject = JSONParser.parseStrict(jsonString).isObject();");
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


    @Override
    protected void readList(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public List<%s> readList(JSONArray jsonArray) {", typeContext.getType()
                .getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("List<%s> models = null;", typeContext.getType().getParameterizedQualifiedSourceName());
        writer.write("List<InstanceContextHolder<%s, JSONObject>> instanceContextHolders = null;", typeContext
                .getType().getParameterizedQualifiedSourceName());
        writer.write("if (jsonArray != null) {");
        writer.indent();
        writer.write("models = new ArrayList<%s>();", typeContext.getType().getParameterizedQualifiedSourceName());
        writer.write("instanceContextHolders = new ArrayList<InstanceContextHolder<%s, JSONObject>>();", typeContext
                .getType().getParameterizedQualifiedSourceName());

        CodeGeneration.log(writer, Level.FINE, "First iteration over JSON array to create models and process IDs");
        writer.write("int size = jsonArray.size();");
        writer.write("for (int i = 0; i < size; i++) {");
        writer.indent();
        writer.write("JSONValue currentJsonValue = jsonArray.get(i);");
        writer.write("if (currentJsonValue != null) {");
        writer.indent();
        writer.write("JSONObject currentJsonObject = currentJsonValue.isObject();");
        writer.write("if (currentJsonObject != null) {");
        writer.indent();
        writer.write("%s %s = readIds(currentJsonObject);",
                typeContext.getType().getParameterizedQualifiedSourceName(), typeContext.getVariableNames()
                        .getInstanceVariable());
        writer.write("if (%s != null) {", typeContext.getVariableNames().getInstanceVariable());
        writer.indent();
        writer.write("models.add(%s);", typeContext.getVariableNames().getInstanceVariable());
        writer.write("instanceContextHolders.add(new InstanceContextHolder<%s, JSONObject>(%s, currentJsonObject));",
                typeContext.getType().getParameterizedQualifiedSourceName(), typeContext.getVariableNames()
                        .getInstanceVariable());
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");

        CodeGeneration.log(writer, Level.FINE, "Second iteration over generated models to map properties and IDREFs");
        writer.write("for (InstanceContextHolder<%s, JSONObject> ich : instanceContextHolders) {", typeContext
                .getType().getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("%s %s = ich.getInstance();", typeContext.getType().getParameterizedQualifiedSourceName(),
                typeContext.getVariableNames().getInstanceVariable());
        writer.write("readProperties(ich.getContext(), %s);", typeContext.getVariableNames().getInstanceVariable());
        writer.write("readIdRefs(ich.getContext(), %s);", typeContext.getVariableNames().getInstanceVariable());
        writer.write("ModelReadEvent.fire(this, %s);", typeContext.getVariableNames().getInstanceVariable());
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.write("return models;");
        writer.outdent();
        writer.write("}");
    }


    // ---------------------------------------------------- read single methods

    protected void readSingleFromString(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public %s read(String jsonString) {", typeContext.getType().getParameterizedQualifiedSourceName());
        writer.indent();
        writer.write("%s %s = null;", typeContext.getType().getParameterizedQualifiedSourceName(), typeContext
                .getVariableNames().getInstanceVariable());
        writer.write("if (jsonString != null && jsonString.trim().length() != 0) {");
        writer.indent();
        writer.write("JSONObject jsonObject = JSONParser.parseStrict(jsonString).isObject();");
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


    protected void readSingleFromJsonObject(IndentedWriter writer) throws UnableToCompleteException
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


    @Override
    protected void readSingle(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("private %s internalRead(JSONObject %s) {", typeContext.getType()
                .getParameterizedQualifiedSourceName(), typeContext.getVariableNames().getInputVariable());
        writer.indent();
        writer.write("if (%s == null) {", typeContext.getVariableNames().getInputVariable());
        writer.indent();
        writer.write("return null;");
        writer.outdent();
        writer.write("}");
        writer.write("%s %s = readIds(%s);", typeContext.getType().getParameterizedQualifiedSourceName(), typeContext
                .getVariableNames().getInstanceVariable(), typeContext.getVariableNames().getInputVariable());
        writer.write("readProperties(%s, %s);", typeContext.getVariableNames().getInputVariable(), typeContext
                .getVariableNames().getInstanceVariable());
        writer.write("readIdRefs(%s, %s);", typeContext.getVariableNames().getInputVariable(), typeContext
                .getVariableNames().getInstanceVariable());
        writer.write("ModelReadEvent.fire(this, %s);", typeContext.getVariableNames().getInstanceVariable());
        writer.write("return %s;", typeContext.getVariableNames().getInstanceVariable());
        writer.outdent();
        writer.write("}");
    }


    // -------------------------------------------------------------------- ids

    /**
     * TODO Is this method still necessary?
     * 
     * @see name.pehl.piriti.rebind.AbstractReaderCreator#handleIdsInNestedTypes(name.pehl.piriti.rebind.IndentedWriter)
     */
    @Override
    protected void handleIdsInNestedTypes(IndentedWriter writer) throws UnableToCompleteException
    {
        // TODO Currently this causes Stackoverflow! Why?
        // for (Iterator<PropertyContext> iter =
        // typeContext.getProperties().iterator(); iter.hasNext();)
        // {
        // PropertyContext propertyContext = iter.next();
        // PropertyHandler propertyHandler =
        // propertyHandlerLookup.lookup(propertyContext);
        // if ((propertyHandler instanceof DefaultPropertyHandler
        // || propertyHandler instanceof ArrayPropertyHandler || propertyHandler
        // instanceof CollectionPropertyHandler)
        // && propertyHandler.isValid(writer, propertyContext))
        // {
        // writer.newline();
        // handleProperty(writer, propertyHandler, propertyContext,
        // iter.hasNext());
        // }
        // }
    }
}
