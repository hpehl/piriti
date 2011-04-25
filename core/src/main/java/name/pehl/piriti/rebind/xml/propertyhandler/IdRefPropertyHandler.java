package name.pehl.piriti.rebind.xml.propertyhandler;

import java.util.logging.Level;

import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.TypeUtils;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerLookup;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JType;

/**
 * @author $Author$
 * @version $Date$ $Revision: 421
 *          $
 */
public class IdRefPropertyHandler extends AbstractXmlPropertyHandler
{
    protected JType referenceType;


    public IdRefPropertyHandler(TreeLogger logger)
    {
        super(logger);
    }


    /**
     * Returns <code>true</code> if the reference type is a class or interface,
     * <code>false</code> otherwise.
     * 
     * @param writer
     * @param propertyContext
     * @return
     * @see name.pehl.piriti.rebind.propertyhandler.AbstractPropertyHandler#isValid(name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        if (propertyContext.isArray())
        {
            referenceType = propertyContext.getArrayType().getComponentType();
        }
        else if (TypeUtils.isCollection(propertyContext.getType()))
        {
            referenceType = TypeUtils.getTypeVariable(propertyContext.getType());
        }
        else
        {
            referenceType = propertyContext.getType();
        }

        JClassType classOrInterface = referenceType.isClassOrInterface();
        if (classOrInterface == null)
        {
            skipProperty(writer, propertyContext, "Reference type is no class or interface");
            return false;
        }
        CodeGeneration.readerWriterInitialization(writer, classOrInterface);
        return true;
    }


    @Override
    public void readInput(IndentedWriter writer, PropertyContext propertyContext,
            PropertyHandlerLookup propertyHandlerLookup) throws UnableToCompleteException
    {
        String readerVariable = propertyContext.getVariableNames().newVariableName("Reader");
        writer.write("XmlReader<%s> %s = xmlRegistry.getReader(%s.class);",
                referenceType.getParameterizedQualifiedSourceName(), readerVariable,
                referenceType.getQualifiedSourceName());
        writer.write("if (%s != null) {", readerVariable);
        writer.indent();
        String references = propertyContext.getVariableNames().newVariableName("References");
        writer.write("String[] %s = %s.selectValues(\"%s\", WhitespaceHandling.%s);", references, propertyContext
                .getVariableNames().getInputVariable(), propertyContext.getPath(), propertyContext
                .getWhitespaceHandling());
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
                        referenceType.getParameterizedQualifiedSourceName(), collectionVariable);
            }
            else
            {
                collectionVariable = propertyContext.getVariableNames().getValueVariable();
                String collectionImplementation = CodeGeneration.collectionImplementationFor(propertyContext.getType()
                        .getErasedType().getQualifiedSourceName());
                writer.write("%s = new %s<%s>();", propertyContext.getVariableNames().getValueVariable(),
                        collectionImplementation, referenceType.getQualifiedSourceName());
            }
            writer.write("for (String reference : %s) {", references);
            writer.indent();
            writer.write("%s referenceInstance = %s.idRef(reference);",
                    referenceType.getParameterizedQualifiedSourceName(), readerVariable);
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
                        referenceType.getQualifiedSourceName(), collectionVariable);
                writer.write("int index = 0;");
                writer.write("for(%s currentValue : %s) {", referenceType.getQualifiedSourceName(), collectionVariable);
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
        writer.outdent();
        writer.write("}");
    }


    @Override
    public void writeValue(IndentedWriter writer, PropertyContext propertyContext,
            PropertyHandlerLookup propertyHandlerRegistry) throws UnableToCompleteException
    {
        CodeGeneration.log(writer, Level.WARNING, "writeValue() NYI");
    }
}
