package name.pehl.piriti.gxt.rebind.xml;

import name.pehl.piriti.gxt.client.xml.XmlField;
import name.pehl.piriti.gxt.client.xml.XmlModel;
import name.pehl.piriti.gxt.rebind.ModelReaderConstants;
import name.pehl.piriti.rebind.AssignmentType;
import name.pehl.piriti.rebind.FieldContext;
import name.pehl.piriti.rebind.FieldHandlerRegistry;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.TypeUtils;
import name.pehl.piriti.rebind.fieldhandler.FieldHandler;
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
        XmlModel xmlModel = modelType.getAnnotation(XmlModel.class);
        if (xmlModel != null)
        {
            XmlField[] fields = xmlModel.value();
            if (fields != null && fields.length != 0)
            {
                int counter = 0;
                for (XmlField xmlField : fields)
                {
                    writer.newline();
                    JClassType fieldType = getFieldType(xmlField);
                    String xpath = calculateXpath(fieldType, xmlField);
                    FieldContext fieldContext = new FieldContext(context.getTypeOracle(), handlerRegistry, modelType,
                            fieldType, xmlField.property(), xpath, xmlField.format(), AssignmentType.MAPPING,
                            "element", "value" + counter);
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
