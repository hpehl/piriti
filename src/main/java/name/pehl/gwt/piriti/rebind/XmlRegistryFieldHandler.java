package name.pehl.gwt.piriti.rebind;

import name.pehl.gwt.piriti.client.xml.XmlReader;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;

/**
 * {@link FieldHandler} implementation for types with an own XmlReader.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class XmlRegistryFieldHandler extends DefaultFieldHandler
{
    /**
     * Returns <code>true</code> if the field type is a class or interface and
     * if there's a public static field of type XmlReader in the field type,
     * <code>false</code> otherwise.
     * 
     * @param writer
     * @param fieldContext
     * @return
     * @see name.pehl.gwt.piriti.rebind.AbstractFieldHandler#isValid(name.pehl.gwt.piriti.rebind.FieldContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        if (!fieldContext.isClassOrInterface())
        {
            skipField(writer, fieldContext, "Type is no class or interface");
            return false;
        }
        if (fieldContext.getModelType().equals(fieldContext.getFieldType()))
        {
            skipField(writer, fieldContext, "Field type must not be the same as the model type");
            return false;
        }
        JField xmlRegistryField = findXmlRegistryMember(fieldContext.getClassOrInterfaceType());
        if (xmlRegistryField == null)
        {
            skipField(writer, fieldContext, String.format(
                    "No public static field of type XmlReader<%1$s> found in %1$s", fieldContext.getFieldType()
                            .getQualifiedSourceName()));
            return false;
        }
        return true;
    }


    /**
     * TODO Javadoc
     * 
     * @param writer
     * @param fieldContext
     * @throws UnableToCompleteException
     * @see name.pehl.gwt.piriti.rebind.DefaultFieldHandler#writeConverterCode(name.pehl.gwt.piriti.rebind.IndentedWriter,
     *      name.pehl.gwt.piriti.rebind.FieldContext)
     */
    @Override
    public void writeConverterCode(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        JClassType classType = fieldContext.getClassOrInterfaceType();
        JField xmlRegistryField = findXmlRegistryMember(classType);
        writer.write("XmlReader<%1$s> %2$sReader = xmlRegistry.get(%1$s.class);", classType.getQualifiedSourceName(),
                fieldContext.getValueVariable());
        writer.write("if (%sReader != null) {", fieldContext.getValueVariable());
        writer.indent();
        writer.write("Element nestedElement = XPathUtils.getElement(%s, \"%s\");", fieldContext.getXmlVariable(),
                fieldContext.getXpath());
        writer.write("if (nestedElement != null) {");
        writer.indent();
        writer.write("%s = %s.%s.readSingle(nestedElement);", fieldContext.getValueVariable(), classType
                .getQualifiedSourceName(), xmlRegistryField.getName());
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
    }


    private JField findXmlRegistryMember(JClassType type)
    {
        JField[] fields = type.getFields();
        if (fields != null)
        {
            for (JField field : fields)
            {
                if (field.isStatic() && field.isPublic())
                {
                    JClassType fieldType = field.getType().isClassOrInterface();
                    if (fieldType != null)
                    {
                        JClassType[] interfaces = fieldType.getImplementedInterfaces();
                        if (interfaces != null)
                        {
                            for (JClassType interfaze : interfaces)
                            {
                                if (XmlReader.class.getName().equals(interfaze.getQualifiedSourceName()))
                                {
                                    return field;
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
