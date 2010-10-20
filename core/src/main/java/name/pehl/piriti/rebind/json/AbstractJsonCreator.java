package name.pehl.piriti.rebind.json;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import name.pehl.piriti.client.json.JsonField;
import name.pehl.piriti.client.json.JsonFields;
import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.client.json.JsonWriter;
import name.pehl.piriti.rebind.AbstractCreator;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.propertyhandler.AssignmentPolicy;
import name.pehl.piriti.rebind.propertyhandler.AssignmentType;
import name.pehl.piriti.rebind.propertyhandler.FieldAnnotation;
import name.pehl.piriti.rebind.propertyhandler.FieldContext;
import name.pehl.piriti.rebind.propertyhandler.FieldHandler;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;

/**
 * Common creator for {@linkplain JsonReader}s and {@linkplain JsonWriter}s.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 137 $
 */
public abstract class AbstractJsonCreator extends AbstractCreator
{
    // ---------------------------------------------------------- constructors

    public AbstractJsonCreator(GeneratorContext context, JClassType interfaceType, String implName,
            String readerClassname, TreeLogger logger) throws UnableToCompleteException
    {
        super(context, interfaceType, implName, readerClassname, logger);
    }


    // --------------------------------------------------------- create methods

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
        writer.write("private JsonParser jsonParser;");
    }


    @Override
    protected void createConstructorBody(IndentedWriter writer)
    {
        super.createConstructorBody(writer);
        writer.write("this.jsonRegistry = PiritiGinjector.INJECTOR.getJsonRegistry();");
        writer.write("this.jsonRegistry.register(%s.class, this);", modelType.getQualifiedSourceName());
        writer.write("this.jsonParser = PiritiGinjector.INJECTOR.getJsonParser();");
    }


    // --------------------------------------------------------- helper methods

    protected void handleFields(IndentedWriter writer) throws UnableToCompleteException
    {
        int counter = 0;
        Map<String, FieldAnnotation<JsonField>> fields = findFieldAnnotations();
        for (Iterator<FieldAnnotation<JsonField>> iter = fields.values().iterator(); iter.hasNext();)
        {
            FieldAnnotation<JsonField> fieldAnnotation = iter.next();
            String jsonPath = calculateJsonPath(fieldAnnotation.field, fieldAnnotation.annotation);
            FieldContext fieldContext = new FieldContext(context.getTypeOracle(), handlerRegistry, modelType,
                    fieldAnnotation.field.getType(), fieldAnnotation.field.getName(), jsonPath,
                    fieldAnnotation.annotation.format(), false, AssignmentType.MAPPING,
                    fieldAnnotation.assignmentPolicy, "jsonObject", "value" + counter, "jsonBuilder");
            FieldHandler fieldHandler = handlerRegistry.findFieldHandler(fieldContext);
            if (fieldHandler != null && fieldHandler.isValid(writer, fieldContext))
            {
                writer.newline();
                handleField(writer, fieldHandler, fieldContext, iter.hasNext());
                counter++;
            }
        }
    }


    /**
     * Returns a map with the fields name as key and the {@link FieldAnnotation}
     * for {@link JsonField} as value.
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
                            AssignmentPolicy.PROPERTY_FIRST));
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
