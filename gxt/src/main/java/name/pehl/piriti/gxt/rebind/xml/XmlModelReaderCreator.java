package name.pehl.piriti.gxt.rebind.xml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import name.pehl.piriti.gxt.client.xml.XmlField;
import name.pehl.piriti.gxt.client.xml.XmlModel;
import name.pehl.piriti.gxt.rebind.ModelReaderConstants;
import name.pehl.piriti.rebind.AssignmentType;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.TypeUtils;
import name.pehl.piriti.rebind.fieldhandler.FieldContext;
import name.pehl.piriti.rebind.fieldhandler.FieldHandler;
import name.pehl.piriti.rebind.fieldhandler.FieldHandlerRegistry;
import name.pehl.piriti.rebind.xml.XmlReaderCreator;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * Class which generates the code necessary to map the annotated fields.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class XmlModelReaderCreator extends XmlReaderCreator implements ModelReaderConstants
{
    public XmlModelReaderCreator(GeneratorContext context, JClassType interfaceType, String implName,
            String readerClassname, TreeLogger logger) throws UnableToCompleteException
    {
        super(context, interfaceType, implName, readerClassname, logger);
    }


    @Override
    protected FieldHandlerRegistry setupFieldHandlerRegistry()
    {
        return new XmlModelFieldHandlerRegistry();
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
        XmlField[] fields = getAllFields(modelType);
        for (XmlField xmlField : fields)
        {
            writer.newline();
            JClassType fieldType = getFieldType(xmlField);
            String xpath = calculateXpath(fieldType, xmlField);
            FieldContext fieldContext = new FieldContext(context.getTypeOracle(), handlerRegistry, modelType,
                    fieldType, xmlField.property(), xpath, xmlField.format(), xmlField.stripWsnl(),
                    AssignmentType.MAPPING, "element", "value" + counter);
            fieldContext.addMetadata(TYPE_VARIABLE, xmlField.typeVariable());
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


    private XmlField[] getAllFields(JClassType type)
    {
        List<XmlField> fields = new ArrayList<XmlField>();
        collectFields(type, fields);
        return fields.toArray(new XmlField[] {});
    }


    private void collectFields(JClassType type, List<XmlField> fields)
    {
        // Superclass first please!
        if (type == null)
        {
            return;
        }
        collectFields(type.getSuperclass(), fields);

        XmlModel model = type.getAnnotation(XmlModel.class);
        if (model != null)
        {
            XmlField[] modelFields = model.value();
            if (modelFields != null)
            {
                fields.addAll(Arrays.asList(modelFields));
            }
        }
    }


    private JClassType getFieldType(XmlField xmlField) throws UnableToCompleteException
    {
        JClassType fieldType = null;
        if (xmlField.array())
        {
            JClassType componentType = context.getTypeOracle().findType(xmlField.type().getName());
            fieldType = context.getTypeOracle().getArrayType(componentType);
        }
        else
        {
            fieldType = context.getTypeOracle().findType(xmlField.type().getName());
        }
        if (fieldType == null)
        {
            die("Cannot find type {0}", xmlField.type().getName());
        }
        return fieldType;
    }


    private String calculateXpath(JClassType fieldType, XmlField xmlField)
    {
        String xpath = xmlField.path();
        if (xpath == null || xpath.length() == 0)
        {
            xpath = xmlField.property();
            if (fieldType.isPrimitive() != null || TypeUtils.isBasicType(fieldType) || fieldType.isEnum() != null)
            {
                xpath += "/text()";
            }
        }
        return xpath;
    }
}
