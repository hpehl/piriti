package name.pehl.piriti.rebind.xml.propertyhandler;

import name.pehl.piriti.commons.client.WhitespaceHandling;
import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.TypeUtils;
import name.pehl.piriti.rebind.propertyhandler.AbstractCollectionPropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.AbstractDefaultPropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerRegistry;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JType;

/**
 * @author $Author$
 * @version $Date$ $Revision: 421
 *          $
 */
public class IdRefPropertyHandler extends AbstractDefaultPropertyHandler
{
    private static final String NESTED_TYPE = "nestedType";


    public IdRefPropertyHandler(TreeLogger logger)
    {
        super(logger);
    }


    /**
     * Returns <code>true</code> if the field type is a class or interface and
     * if there's a public static field of type {@link #getReaderClassname()} in
     * the field type or if the field type is an array or collection of such a
     * type, <code>false</code> otherwise.
     * 
     * @param writer
     * @param propertyContext
     * @return
     * @see name.pehl.piriti.rebind.propertyhandler.AbstractPropertyHandler#isValid(name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        JType type = null;
        if (propertyContext.isArray())
        {
            type = propertyContext.getArrayType().getComponentType();
        }
        else if (TypeUtils.isCollection(propertyContext.getType()))
        {
            type = TypeUtils.getTypeVariable(propertyContext.getType());
        }
        else
        {
            type = propertyContext.getType();
        }

        JClassType classOrInterface = type.isClassOrInterface();
        if (classOrInterface == null)
        {
            CodeGeneration.skipProperty(writer, propertyContext, "Type is no class or interface");
            return false;
        }
        propertyContext.addMetadata(NESTED_TYPE, classOrInterface);
        return true;
    }


    @Override
    public void readInput(IndentedWriter writer, PropertyContext propertyContext,
            PropertyHandlerRegistry propertyHandlerRegistry) throws UnableToCompleteException
    {
        JClassType nestedType = propertyContext.getMetadata(NESTED_TYPE);
        String readerVariable = startReader(writer, propertyContext, "xmlRegistry", nestedType);

        String references = propertyContext.getVariableNames().newVariableName("References");
        writer.write("String[] %s = %s.selectValues(\"%s\", %s);", references, propertyContext.getVariableNames()
                .getInputVariable(), propertyContext.getPath(),
                propertyContext.getWhitespaceHandling() == WhitespaceHandling.REMOVE);
        writer.write("if (%s.length == 1) {", references);
        writer.indent();
        // If there's only one value it is expected that this value contains
        // the references seperated with space
        writer.write("%1$s = %1$s[0].split(\" \");", references);
        writer.outdent();
        writer.write("}");
        writer.write("if (%s.length != 0) {", references);
        writer.indent();
        if (propertyContext.isArray() || TypeUtils.isCollection(propertyContext.getType()))
        {
            String collectionVariable = null;
            if (propertyContext.isArray())
            {
                collectionVariable = propertyContext.getVariableNames().newVariableName("AsList");
                writer.write("List<%1$s> %2$s = new ArrayList<%1$s>();",
                        nestedType.getParameterizedQualifiedSourceName(), collectionVariable);
            }
            else
            {
                collectionVariable = propertyContext.getVariableNames().getValueVariable();
                String collectionImplementation = AbstractCollectionPropertyHandler.interfaceToImplementation
                        .get(propertyContext.getType().getErasedType().getQualifiedSourceName());
                if (collectionImplementation == null)
                {
                    // the field type is already an implementation
                    collectionImplementation = propertyContext.getType().getParameterizedQualifiedSourceName();
                }
                writer.write("%s = new %s<%s>();", propertyContext.getVariableNames().getValueVariable(),
                        collectionImplementation, nestedType.getQualifiedSourceName());
            }
            writer.write("for (String reference : %s) {", references);
            writer.indent();
            writer.write("%s referenceInstance = %s.idRef(reference);",
                    nestedType.getParameterizedQualifiedSourceName(), readerVariable);
            writer.write("if (referenceInstance != null) {");
            writer.indent();
            writer.write("%s.add(referenceInstance);", collectionVariable);
            writer.outdent();
            writer.write("}");
            writer.outdent();
            writer.write("}");
            if (propertyContext.isArray())
            {
                writer.write("%s = new %s[%s.size()];", propertyContext.getVariableNames().getValueVariable(),
                        nestedType.getQualifiedSourceName(), collectionVariable);
                writer.write("int index = 0;");
                writer.write("for(%s currentValue : %s) {", nestedType.getQualifiedSourceName(), collectionVariable);
                writer.indent();
                writer.write("%s[index] = currentValue;", propertyContext.getVariableNames().getValueVariable());
                writer.write("index++;");
                writer.outdent();
                writer.write("}");
            }
        }
        else
        {
            writer.write("%s = %s.idRef(%s[0]);", propertyContext.getVariableNames().getValueVariable(),
                    readerVariable, references);
        }
        writer.outdent();
        writer.write("}");

        endReaderWriter(writer);
    }


    /**
     * @param writer
     * @param propertyContext
     * @see name.pehl.piriti.rebind.propertyhandler.AbstractPropertyHandler#readInputAsString(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.PropertyContext)
     */
    @Override
    protected void readInputAsString(IndentedWriter writer, PropertyContext propertyContext)
    {
        // TODO Implement me!
    }


    @Override
    public void markupStart(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        writer.write("// markupStart() NYI");
    }


    @Override
    public void writeValue(IndentedWriter writer, PropertyContext propertyContext,
            PropertyHandlerRegistry propertyHandlerRegistry) throws UnableToCompleteException
    {
        writer.write("// writeValue() NYI");
    }


    @Override
    public void markupEnd(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        writer.write("// markupEnd() NYI");
    }


    @Override
    protected String getReaderClassname()
    {
        return XmlReader.class.getName();
    }


    @Override
    protected String getWriterClassname()
    {
        return XmlWriter.class.getName();
    }
}
