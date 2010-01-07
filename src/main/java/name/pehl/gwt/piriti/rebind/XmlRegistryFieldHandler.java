package name.pehl.gwt.piriti.rebind;

import name.pehl.gwt.piriti.client.xml.XmlReader;

import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;

/**
 * @author $Author:$
 * @version $Revision:$
 */
public class XmlRegistryFieldHandler extends AbstractFieldHandler
{
    @Override
    public void write(IndentedWriter writer, FieldContext fieldContext)
    {
        assert fieldContext.isClassOrInterface();
        JClassType classType = fieldContext.getClassOrInterfaceType();
        
        JField xmlRegistryField = findXmlRegistryMember(classType);
        if (xmlRegistryField == null)
        {
            writer.write("// No public static field of type XmlReader<%1$s> found in %1$s", classType
                    .getQualifiedSourceName());
        }
        else
        {
            writeComment(writer, fieldContext);
            writeDeclaration(writer, fieldContext);
            writer.write("XmlReader<%1$s> %2$sReader = xmlRegistry.get(%1$s.class);", classType
                    .getQualifiedSourceName(), fieldContext.getValueVariable());
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
