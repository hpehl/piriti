package name.pehl.piriti.gxt.rebind.xml;

import java.util.HashMap;
import java.util.Map;

import name.pehl.piriti.gxt.rebind.ModelReaderConstants;
import name.pehl.piriti.gxt.xml.client.Xml;
import name.pehl.piriti.gxt.xml.client.XmlMappings;
import name.pehl.piriti.property.client.NoopPropertyGetter;
import name.pehl.piriti.property.client.NoopPropertySetter;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.TypeUtils;
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
    protected void handleProperties(IndentedWriter writer) throws UnableToCompleteException
    {
        int counter = 0;
        Xml[] properties = findGxtPropertyAnnotations();
        for (int i = 0; i < properties.length; i++)
        {
            Xml xmlField = properties[i];
            writer.newline();
            JClassType fieldType = getFieldType(xmlField);
            String xpath = calculateXpath(fieldType, xmlField);
            VariableNames variableNames = new VariableNames("element", "value" + counter, "xmlBuilder");
            PropertyContext fieldContext = new PropertyContext(context.getTypeOracle(), handlerRegistry, interfaceType,
                    modelType, fieldType, xmlField.property(), xpath, xmlField.format(), xmlField.stripWsnl(),
                    xmlField.converter(), null, NoopPropertyGetter.class, NoopPropertySetter.class, variableNames);
            fieldContext.addMetadata(TYPE_VARIABLE, xmlField.typeVariable());
            PropertyHandler fieldHandler = handlerRegistry.findPropertyHandler(fieldContext);
            if (fieldHandler != null && fieldHandler.isValid(writer, fieldContext))
            {
                writer.newline();
                handleProperty(writer, fieldHandler, fieldContext, (i < properties.length - 1));
                counter++;
            }
        }
    }


    private Xml[] findGxtPropertyAnnotations()
    {
        Map<String, Xml> properties = new HashMap<String, Xml>();

        // Step 1: Add all XmlField annotations from the interfaceType
        XmlMappings interfaceTypeFields = interfaceType.getAnnotation(XmlMappings.class);
        if (interfaceTypeFields != null)
        {
            Xml[] annotations = interfaceTypeFields.value();
            for (Xml annotation : annotations)
            {
                properties.put(annotation.property(), annotation);
            }
        }

        // Step 2: Add all XmlField annotations from the modelType. If
        // there's already an entry from step 1, it will be overwritten!
        collectModelTypeFields(modelType, properties);

        return properties.values().toArray(new Xml[] {});
    }


    private void collectModelTypeFields(JClassType type, Map<String, Xml> fields)
    {
        // Superclass first please!
        if (type == null)
        {
            return;
        }
        collectModelTypeFields(type.getSuperclass(), fields);

        XmlMappings modelTypeFields = type.getAnnotation(XmlMappings.class);
        if (modelTypeFields != null)
        {
            Xml[] modelTypeFieldsValue = modelTypeFields.value();
            if (modelTypeFieldsValue != null)
            {
                for (Xml annotation : modelTypeFieldsValue)
                {
                    fields.put(annotation.property(), annotation);
                }
            }
        }
    }


    private JClassType getFieldType(Xml xmlField) throws UnableToCompleteException
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


    private String calculateXpath(JClassType fieldType, Xml xmlField)
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
