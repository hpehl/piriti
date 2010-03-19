package name.pehl.piriti.gxt.rebind.json;

import name.pehl.piriti.gxt.client.json.JsonField;
import name.pehl.piriti.gxt.client.json.JsonModel;
import name.pehl.piriti.gxt.rebind.ModelReaderConstants;
import name.pehl.piriti.rebind.FieldContext;
import name.pehl.piriti.rebind.FieldHandlerRegistry;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.fieldhandler.FieldHandler;
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
    protected void processMappings(IndentedWriter writer, String jsonVariable) throws UnableToCompleteException
    {
        JsonModel jsonModel = modelType.getAnnotation(JsonModel.class);
        if (jsonModel != null)
        {
            JsonField[] fields = jsonModel.value();
            if (fields != null && fields.length != 0)
            {
                int counter = 0;
                for (JsonField jsonField : fields)
                {
                    writer.newline();
                    JClassType fieldType = getFieldType(jsonField);
                    String jsonPath = calculateJsonPath(jsonField);
                    FieldContext fieldContext = new FieldContext(context.getTypeOracle(), handlerRegistry, modelType,
                            fieldType, jsonField.property(), jsonPath, jsonField.format(), jsonVariable, "value"
                                    + counter);
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
                    // TODO What to do if no handler was found?
                }
            }
        }
    }


    private JClassType getFieldType(JsonField jsonField) throws UnableToCompleteException
    {
        JClassType fieldType = context.getTypeOracle().findType(jsonField.type().getName());
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
