package name.pehl.piriti.gxt.rebind.json;

import java.util.HashMap;
import java.util.Map;

import name.pehl.piriti.gxt.json.client.Json;
import name.pehl.piriti.gxt.json.client.JsonMappings;
import name.pehl.piriti.gxt.json.client.JsonModelReader;
import name.pehl.piriti.gxt.rebind.ModelReaderConstants;
import name.pehl.piriti.property.client.NoopPropertyGetter;
import name.pehl.piriti.property.client.NoopPropertySetter;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.VariableNames;
import name.pehl.piriti.rebind.json.JsonReaderCreator;
import name.pehl.piriti.rebind.propertyhandler.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerRegistry;

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
    protected PropertyHandlerRegistry setupPropertyHandlerRegistry()
    {
        return new JsonModelReaderPropertyHandlerRegistry();
    }


    // --------------------------------------------------------- create methods

    @Override
    protected void createImports(IndentedWriter writer) throws UnableToCompleteException
    {
        super.createImports(writer);
        writer.write("import com.extjs.gxt.ui.client.data.*;");
    }


    @Override
    protected void handleProperties(IndentedWriter writer) throws UnableToCompleteException
    {
        int counter = 0;
        Json[] properties = findGxtPropertyAnnotations();
        for (int i = 0; i < properties.length; i++)
        {
            Json jsonField = properties[i];
            writer.newline();
            JClassType fieldType = getFieldType(jsonField);
            String jsonPath = calculateJsonPath(jsonField);
            VariableNames variableNames = new VariableNames("jsonObject", "value" + counter, "jsonBuilder");
            PropertyContext fieldContext = new PropertyContext(generatorContext.getTypeOracle(), propertyHandlerRegistry, readerWriterType,
                    pojoType, fieldType, jsonField.property(), jsonPath, jsonField.format(), false,
                    jsonField.converter(), null, NoopPropertyGetter.class, NoopPropertySetter.class, variableNames);
            fieldContext.addMetadata(TYPE_VARIABLE, jsonField.typeVariable());
            PropertyHandler fieldHandler = propertyHandlerRegistry.findPropertyHandler(fieldContext);
            if (fieldHandler != null && fieldHandler.isValid(writer, fieldContext))
            {
                writer.newline();
                handleProperty(writer, fieldHandler, fieldContext, (i < properties.length - 1));
                counter++;
            }
        }
    }


    private Json[] findGxtPropertyAnnotations()
    {
        Map<String, Json> properties = new HashMap<String, Json>();

        // Step 1: Add all JsonField annotations from the interfaceType
        JsonMappings interfaceTypeFields = readerWriterType.getAnnotation(JsonMappings.class);
        if (interfaceTypeFields != null)
        {
            Json[] annotations = interfaceTypeFields.value();
            for (Json annotation : annotations)
            {
                properties.put(annotation.property(), annotation);
            }
        }

        // Step 2: Add all JsonField annotations from the modelType. If
        // there's already an entry from step 1, it will be overwritten!
        collectModelTypeFields(pojoType, properties);

        return properties.values().toArray(new Json[] {});
    }


    private void collectModelTypeFields(JClassType type, Map<String, Json> fields)
    {
        // Superclass first please!
        if (type == null)
        {
            return;
        }
        collectModelTypeFields(type.getSuperclass(), fields);

        JsonMappings modelTypeFields = type.getAnnotation(JsonMappings.class);
        if (modelTypeFields != null)
        {
            Json[] modelTypeFieldsValue = modelTypeFields.value();
            if (modelTypeFieldsValue != null)
            {
                for (Json annotation : modelTypeFieldsValue)
                {
                    fields.put(annotation.property(), annotation);
                }
            }
        }
    }


    private JClassType getFieldType(Json jsonField) throws UnableToCompleteException
    {
        JClassType fieldType = null;
        if (jsonField.array())
        {
            JClassType componentType = generatorContext.getTypeOracle().findType(jsonField.type().getName());
            fieldType = generatorContext.getTypeOracle().getArrayType(componentType);
        }
        else
        {
            fieldType = generatorContext.getTypeOracle().findType(jsonField.type().getName());
        }
        if (fieldType == null)
        {
            die("Cannot find type {0}", jsonField.type().getName());
        }
        return fieldType;
    }


    private String calculateJsonPath(Json jsonField)
    {
        String jsonPath = jsonField.path();
        if (jsonPath == null || jsonPath.length() == 0)
        {
            jsonPath = jsonField.property();
        }
        return jsonPath;
    }
}
