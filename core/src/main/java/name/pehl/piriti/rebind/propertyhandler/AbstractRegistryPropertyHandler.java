package name.pehl.piriti.rebind.propertyhandler;

import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * Abstract {@link PropertyHandler} implementation for types with an own reader.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
 */
public abstract class AbstractRegistryPropertyHandler extends AbstractPropertyHandler
{
    /**
     * Returns <code>true</code> if the properties type is a class or interface,
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
        if (!propertyContext.isClassOrInterface())
        {
            CodeGeneration.skipField(writer, propertyContext, "Type is no class or interface");
            return false;
        }
        CodeGeneration.readerWriterInitialization(writer, propertyContext.getClassOrInterfaceType());
        return true;
    }


    protected abstract String getReaderClassname();


    protected abstract String getWriterClassname();


    /**
     * Generates code for the reader lookup and starts the if statement
     * <code>if (reader != null) {</code>.
     * 
     * @param writer
     * @param propertyContext
     * @return the variable name of the reader
     */
    protected String startReader(IndentedWriter writer, PropertyContext propertyContext, String registryName,
            JClassType type)
    {
        // Cast because subclasses might use a subtype of getReaderClassname()
        String readerVariable = propertyContext.getVariableNames().newVariableName("Reader");
        writer.write("%1$s<%2$s> %3$s = (%1$s)this.%4$s.getReader(%2$s.class);", getReaderClassname(),
                type.getQualifiedSourceName(), readerVariable, registryName);
        writer.write("if (%s != null) {", readerVariable);
        writer.indent();
        return readerVariable;
    }


    /**
     * Generates code for the writer lookup and starts the if statement
     * <code>if (writer != null) {</code>.
     * 
     * @param writer
     * @param propertyContext
     * @return the variable name of the reader
     */
    protected String startWriter(IndentedWriter writer, PropertyContext propertyContext, String registryName,
            JClassType type)
    {
        // Cast because subclasses might use a subtype of getReaderClassname()
        String writerVariable = propertyContext.getVariableNames().newVariableName("Writer");
        writer.write("%1$s<%2$s> %3$s = (%1$s)this.%4$s.getWriter(%2$s.class);", getWriterClassname(),
                type.getQualifiedSourceName(), writerVariable, registryName);
        writer.write("if (%s != null) {", writerVariable);
        writer.indent();
        return writerVariable;
    }


    /**
     * Closes the if statement started by
     * {@link #startReader(IndentedWriter, PropertyContext)} or
     * {@link #startWriter(IndentedWriter, PropertyContext, String, JClassType)}
     * .
     * 
     * @param writer
     */
    protected void endReaderWriter(IndentedWriter writer)
    {
        writer.outdent();
        writer.write("}");
    }
}
