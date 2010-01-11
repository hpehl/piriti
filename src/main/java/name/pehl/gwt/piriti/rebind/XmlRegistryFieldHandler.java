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
public class XmlRegistryFieldHandler extends AbstractFieldHandler
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
    public boolean isValid(IndentedWriter writer, FieldContext fieldContext)
    {
        if (!fieldContext.isClassOrInterface())
        {
            skipField(writer, fieldContext, "Type is no class or interface");
            return false;
        }
        JField xmlRegistryField = findXmlRegistryMember(fieldContext.getClassOrInterfaceType());
        if (xmlRegistryField == null)
        {
            skipField(writer, fieldContext, String.format(
                    "No public static field of type XmlReader<%1$s> found in %1$s", fieldContext.getType()
                            .getQualifiedSourceName()));
            return false;
        }
        return true;
    }


    @Override
    public void write(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        JClassType classType = fieldContext.getClassOrInterfaceType();
        JField xmlRegistryField = findXmlRegistryMember(classType);
        writeComment(writer, fieldContext);
        writeDeclaration(writer, fieldContext);
        writer.write("XmlReader<%1$s> %2$sReader = xmlRegistry.get(%1$s.class);", classType.getQualifiedSourceName(),
                fieldContext.getValueVariable());
        writer.write("if (%sReader != null) {", fieldContext.getValueVariable());
        writer.indent();
        writer.write("Element nestedElement = XPathUtils.getElement(%s, \"%s\");", fieldContext.getSourceVariable(),
                fieldContext.getXpath());
        writer.write("if (nestedElement != null) {");
        writer.indent();
        writer.write("%s = %s.%s.readSingle(nestedElement);", fieldContext.getValueVariable(), classType
                .getQualifiedSourceName(), xmlRegistryField.getName());
        writeAssignment(writer, fieldContext);
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
