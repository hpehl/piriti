package name.pehl.piriti.gxt.rebind.xml;

import static name.pehl.piriti.rebind.propertyhandler.Assignment.AssignmentPolicy.*;
import static name.pehl.piriti.rebind.propertyhandler.Assignment.AssignmentType.*;

import java.util.HashMap;
import java.util.Map;

import name.pehl.piriti.gxt.client.xml.XmlField;
import name.pehl.piriti.gxt.client.xml.XmlFields;
import name.pehl.piriti.gxt.rebind.ModelReaderConstants;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.TypeUtils;
import name.pehl.piriti.rebind.propertyhandler.Assignment;
import name.pehl.piriti.rebind.propertyhandler.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerRegistry;
import name.pehl.piriti.rebind.propertyhandler.VariableNames;
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
    protected PropertyHandlerRegistry setupFieldHandlerRegistry()
    {
        return new XmlModelPropertyHandlerRegistry();
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
        XmlField[] fields = findModelFieldAnnotations();
        for (int i = 0; i < fields.length; i++)
        {
            XmlField xmlField = fields[i];
            writer.newline();
            JClassType fieldType = getFieldType(xmlField);
            String xpath = calculateXpath(fieldType, xmlField);
            // TODO Implement usage of setters
            Assignment assignment = new Assignment(MAPPING, GXT);
            VariableNames variableNames = new VariableNames("element", "value" + counter, "xmlBuilder");
            PropertyContext fieldContext = new PropertyContext(context.getTypeOracle(), handlerRegistry, modelType,
                    fieldType, xmlField.name(), xpath, xmlField.format(), xmlField.stripWsnl(), assignment,
                    variableNames);
            fieldContext.addMetadata(TYPE_VARIABLE, xmlField.typeVariable());
            PropertyHandler fieldHandler = handlerRegistry.findPropertyHandler(fieldContext);
            if (fieldHandler != null && fieldHandler.isValid(writer, fieldContext))
            {
                writer.newline();
                handleProperty(writer, fieldHandler, fieldContext, (i < fields.length - 1));
                counter++;
            }
        }
    }


    private XmlField[] findModelFieldAnnotations()
    {
        Map<String, XmlField> fields = new HashMap<String, XmlField>();

        // Step 1: Add all XmlField annotations from the interfaceType
        XmlFields interfaceTypeFields = interfaceType.getAnnotation(XmlFields.class);
        if (interfaceTypeFields != null)
        {
            XmlField[] annotations = interfaceTypeFields.value();
            for (XmlField annotation : annotations)
            {
                fields.put(annotation.name(), annotation);
            }
        }

        // Step 2: Add all XmlField annotations from the modelType. If
        // there's already an entry from step 1, it will be overwritten!
        collectModelTypeFields(modelType, fields);

        return fields.values().toArray(new XmlField[] {});
    }


    private void collectModelTypeFields(JClassType type, Map<String, XmlField> fields)
    {
        // Superclass first please!
        if (type == null)
        {
            return;
        }
        collectModelTypeFields(type.getSuperclass(), fields);

        XmlFields modelTypeFields = type.getAnnotation(XmlFields.class);
        if (modelTypeFields != null)
        {
            XmlField[] modelTypeFieldsValue = modelTypeFields.value();
            if (modelTypeFieldsValue != null)
            {
                for (XmlField annotation : modelTypeFieldsValue)
                {
                    fields.put(annotation.name(), annotation);
                }
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
            xpath = xmlField.name();
            if (fieldType.isPrimitive() != null || TypeUtils.isBasicType(fieldType) || fieldType.isEnum() != null)
            {
                xpath += "/text()";
            }
        }
        return xpath;
    }
}
