package name.pehl.piriti.rebind.xml.fieldhandler;

import name.pehl.piriti.client.xml.XmlReader;
import name.pehl.piriti.rebind.FieldContext;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.TypeUtils;
import name.pehl.piriti.rebind.fieldhandler.AbstractCollectionFieldHandler;
import name.pehl.piriti.rebind.fieldhandler.AbstractRegistryFieldHandler;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.core.ext.typeinfo.JType;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public class IdRefFieldHandler extends AbstractRegistryFieldHandler
{
    /**
     * Returns <code>true</code> if the field type is a class or interface and
     * if there's a public static field of type {@link #getReaderClassname()} in
     * the field type or if the field type is an array or collection of such a
     * type, <code>false</code> otherwise.
     * 
     * @param writer
     * @param fieldContext
     * @return
     * @see name.pehl.piriti.rebind.fieldhandler.AbstractFieldHandler#isValid(name.pehl.piriti.rebind.FieldContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        JType type = null;
        if (fieldContext.isArray())
        {
            type = fieldContext.getArrayType().getComponentType();
        }
        else if (TypeUtils.isCollection(fieldContext.getFieldType()))
        {
            type = TypeUtils.getTypeVariable(fieldContext.getFieldType());
        }
        else
        {
            type = fieldContext.getFieldType();
        }

        JClassType classOrInterface = type.isClassOrInterface();
        if (classOrInterface == null)
        {
            skipField(writer, fieldContext, "Type is no class or interface");
            return false;
        }
        JField xmlReaderField = findReaderMember(classOrInterface);
        if (xmlReaderField == null)
        {
            skipField(writer, fieldContext, String.format("No public static field of type %1$s<%2$s> found in %2$s",
                    getReaderClassname(), type.getQualifiedSourceName()));
            return false;
        }
        fieldContext.addMetadata("fqXmlReader", classOrInterface.getQualifiedSourceName() + "."
                + xmlReaderField.getName());
        return true;
    }


    @Override
    public void writeConverterCode(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        String fqXmlReader = fieldContext.getMetadata("fqXmlReader");
        String references = fieldContext.newVariableName("References");
        writer.write("String[] %s = XPathUtils.getValues(%s, \"%s\");", references, fieldContext.getInputVariable(),
                fieldContext.getPath());
        writer.write("if (%1$s != null && %1$s.length != 0) {", references);
        writer.indent();
        writer.write("if (%s.length == 1) {", references);
        writer.indent();
        // If theres only one value it is expected that this value contains
        // the references seperated with space
        writer.write("%1$s = %1$s[0].split(\" \");", references);
        writer.outdent();
        writer.write("}");
        if (fieldContext.isArray())
        {
        }
        else if (TypeUtils.isCollection(fieldContext.getFieldType()))
        {
            JClassType typeVariable = TypeUtils.getTypeVariable(fieldContext.getFieldType());
            String collectionImplementation = AbstractCollectionFieldHandler.interfaceToImplementation.get(fieldContext
                    .getFieldType().getErasedType().getQualifiedSourceName());
            if (collectionImplementation == null)
            {
                // the field type is already an implementation
                collectionImplementation = fieldContext.getFieldType().getParameterizedQualifiedSourceName();
            }
            writer.write("%s = new %s<%s>();", fieldContext.getValueVariable(), collectionImplementation, typeVariable
                    .getQualifiedSourceName());
            writer.write("for (String reference : %s) {", references);
            writer.indent();
            writer.write("%s referenceInstance = %s.idRef(reference);", typeVariable
                    .getParameterizedQualifiedSourceName(), fqXmlReader);
            writer.write("if (referenceInstance != null) {");
            writer.indent();
            writer.write("%s.add(referenceInstance);", fieldContext.getValueVariable());
            writer.outdent();
            writer.write("}");
            writer.outdent();
            writer.write("}");
        }
        else
        {
        }
        writer.outdent();
        writer.write("}");
    }


    @Override
    protected String getReaderClassname()
    {
        return XmlReader.class.getName();
    }
}
