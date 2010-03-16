package name.pehl.piriti.rebind.xml.fieldhandler;

import name.pehl.piriti.client.xml.XmlReader;
import name.pehl.piriti.rebind.FieldContext;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.fieldhandler.AbstractRegistryFieldHandler;
import name.pehl.piriti.rebind.fieldhandler.FieldHandler;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;

/**
 * {@link FieldHandler} implementation for types with an own {@link XmlReader}.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class XmlRegistryFieldHandler extends AbstractRegistryFieldHandler
{
    /**
     * TODO Javadoc
     * 
     * @param writer
     * @param fieldContext
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.xml.fieldhandler.ConverterFieldHandler#writeConverterCode(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.FieldContext)
     */
    @Override
    public void writeConverterCode(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        JClassType classType = fieldContext.getClassOrInterfaceType();
        JField xmlRegistryField = findRegistryMember(fieldContext.getClassOrInterfaceType());
        writer.write("XmlReader<%1$s> %2$sReader = xmlRegistry.get(%1$s.class);", classType.getQualifiedSourceName(),
                fieldContext.getValueVariable());
        writer.write("if (%sReader != null) {", fieldContext.getValueVariable());
        writer.indent();
        writer.write("Element nestedElement = XPathUtils.getElement(%s, \"%s\");", fieldContext.getInputVariable(),
                fieldContext.getPath());
        writer.write("if (nestedElement != null) {");
        writer.indent();
        writer.write("%s = %s.%s.read(nestedElement);", fieldContext.getValueVariable(), classType
                .getQualifiedSourceName(), xmlRegistryField.getName());
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
    }


    @Override
    protected String getReaderClassname()
    {
        return XmlReader.class.getName();
    }
}
