package name.pehl.piriti.gxt.rebind.json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import name.pehl.piriti.gxt.client.json.JsonField;
import name.pehl.piriti.gxt.client.json.JsonModel;
import name.pehl.piriti.gxt.rebind.ModelReaderConstants;
import name.pehl.piriti.rebind.AssignmentType;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.fieldhandler.FieldContext;
import name.pehl.piriti.rebind.fieldhandler.FieldHandler;
import name.pehl.piriti.rebind.fieldhandler.FieldHandlerRegistry;
import name.pehl.piriti.rebind.json.JsonReaderCreator;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * Class which generates the code necessary to map the annotated fields.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 137 $
 */
public class JsonModelReaderCreator extends JsonReaderCreator implements ModelReaderConstants
{
    public JsonModelReaderCreator(GeneratorContext context, JClassType interfaceType, String implName,
            String readerClassname, TreeLogger logger) throws UnableToCompleteException
    {
        super(context, interfaceType, implName, readerClassname, logger);
    }


    @Override
    protected FieldHandlerRegistry setupFieldHandlerRegistry()
    {
        return new JsonModelFieldHandlerRegistry();
    }


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
        JsonField[] fields = getAllFields(modelType);
        for (JsonField jsonField : fields)
        {
            writer.newline();
            JClassType fieldType = getFieldType(jsonField);
            String jsonPath = calculateJsonPath(jsonField);
            FieldContext fieldContext = new FieldContext(context.getTypeOracle(), handlerRegistry, modelType,
                    fieldType, jsonField.property(), jsonPath, jsonField.format(), false, AssignmentType.MAPPING,
                    "jsonObject", "value" + counter);
            fieldContext.addMetadata(TYPE_VARIABLE, jsonField.typeVariable());
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
        }
    }


    private JsonField[] getAllFields(JClassType type)
    {
        List<JsonField> fields = new ArrayList<JsonField>();
        collectFields(type, fields);
        return fields.toArray(new JsonField[] {});
    }


    private void collectFields(JClassType type, List<JsonField> fields)
    {
        // Superclass first please!
        if (type == null)
        {
            return;
        }
        collectFields(type.getSuperclass(), fields);

        JsonModel model = type.getAnnotation(JsonModel.class);
        if (model != null)
        {
            JsonField[] modelFields = model.value();
            if (modelFields != null)
            {
                fields.addAll(Arrays.asList(modelFields));
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
            jsonPath = jsonField.property();
        }
        return jsonPath;
    }
}
