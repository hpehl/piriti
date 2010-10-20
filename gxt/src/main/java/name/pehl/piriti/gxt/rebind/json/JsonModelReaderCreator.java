package name.pehl.piriti.gxt.rebind.json;

import java.util.HashMap;
import java.util.Map;

import name.pehl.piriti.gxt.client.json.JsonField;
import name.pehl.piriti.gxt.client.json.JsonFields;
import name.pehl.piriti.gxt.client.json.JsonModelReader;
import name.pehl.piriti.gxt.rebind.ModelReaderConstants;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.json.JsonReaderCreator;
import name.pehl.piriti.rebind.propertyhandler.AssignmentPolicy;
import name.pehl.piriti.rebind.propertyhandler.AssignmentType;
import name.pehl.piriti.rebind.propertyhandler.FieldContext;
import name.pehl.piriti.rebind.propertyhandler.FieldHandler;
import name.pehl.piriti.rebind.propertyhandler.FieldHandlerRegistry;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * Creator for GXT {@linkplain JsonModelReader}s.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 137 $
 */
public class JsonModelReaderCreator extends JsonReaderCreator implements ModelReaderConstants
{
    // --------------------------------------------------------- initialization

    public JsonModelReaderCreator(GeneratorContext context, JClassType interfaceType, String implName,
            String readerClassname, TreeLogger logger) throws UnableToCompleteException
    {
        super(context, interfaceType, implName, readerClassname, logger);
    }


    @Override
    protected FieldHandlerRegistry setupFieldHandlerRegistry()
    {
        return new JsonModelReaderFieldHandlerRegistry();
    }


    // --------------------------------------------------------- create methods

    @Override
    protected void createImports(IndentedWriter writer) throws UnableToCompleteException
    {
        super.createImports(writer);
        writer.write("import com.extjs.gxt.ui.client.data.*;");
    }


    @Override
    protected void handleFields(IndentedWriter writer) throws UnableToCompleteException
    {
        int counter = 0;
        JsonField[] fields = findFieldAnnotations();
        for (int i = 0; i < fields.length; i++)
        {
            JsonField jsonField = fields[i];
            writer.newline();
            JClassType fieldType = getFieldType(jsonField);
            String jsonPath = calculateJsonPath(jsonField);
            FieldContext fieldContext = new FieldContext(context.getTypeOracle(), handlerRegistry, modelType,
                    fieldType, jsonField.name(), jsonPath, jsonField.format(), false, AssignmentType.MAPPING,
                    AssignmentPolicy.GXT, "jsonObject", "value" + counter, "jsonBuilder");
            fieldContext.addMetadata(TYPE_VARIABLE, jsonField.typeVariable());
            FieldHandler fieldHandler = handlerRegistry.findFieldHandler(fieldContext);
            if (fieldHandler != null && fieldHandler.isValid(writer, fieldContext))
            {
                writer.newline();
                handleField(writer, fieldHandler, fieldContext, (i < fields.length - 1));
                counter++;
            }
        }
    }


    private JsonField[] findFieldAnnotations()
    {
        Map<String, JsonField> fields = new HashMap<String, JsonField>();

        // Step 1: Add all JsonField annotations from the interfaceType
        JsonFields interfaceTypeFields = interfaceType.getAnnotation(JsonFields.class);
        if (interfaceTypeFields != null)
        {
            JsonField[] annotations = interfaceTypeFields.value();
            for (JsonField annotation : annotations)
            {
                fields.put(annotation.name(), annotation);
            }
        }

        // Step 2: Add all JsonField annotations from the modelType. If
        // there's already an entry from step 1, it will be overwritten!
        collectModelTypeFields(modelType, fields);

        return fields.values().toArray(new JsonField[] {});
    }


    private void collectModelTypeFields(JClassType type, Map<String, JsonField> fields)
    {
        // Superclass first please!
        if (type == null)
        {
            return;
        }
        collectModelTypeFields(type.getSuperclass(), fields);

        JsonFields modelTypeFields = type.getAnnotation(JsonFields.class);
        if (modelTypeFields != null)
        {
            JsonField[] modelTypeFieldsValue = modelTypeFields.value();
            if (modelTypeFieldsValue != null)
            {
                for (JsonField annotation : modelTypeFieldsValue)
                {
                    fields.put(annotation.name(), annotation);
                }
            }
        }
    }


    private JClassType getFieldType(JsonField jsonField) throws UnableToCompleteException
    {
        JClassType fieldType = null;
        if (jsonField.array())
        {
            JClassType componentType = context.getTypeOracle().findType(jsonField.type().getName());
            fieldType = context.getTypeOracle().getArrayType(componentType);
        }
        else
        {
            fieldType = context.getTypeOracle().findType(jsonField.type().getName());
        }
        if (fieldType == null)
        {
            die("Cannot find type {0}", jsonField.type().getName());
        }
        return fieldType;
    }


    private String calculateJsonPath(JsonField jsonField)
    {
        String jsonPath = jsonField.path();
        if (jsonPath == null || jsonPath.length() == 0)
        {
            jsonPath = jsonField.name();
        }
        return jsonPath;
    }
}
