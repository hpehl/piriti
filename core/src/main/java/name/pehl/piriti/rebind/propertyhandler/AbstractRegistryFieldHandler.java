package name.pehl.piriti.rebind.propertyhandler;

import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * Abstract {@link FieldHandler} implementation for types with an own reader.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
 */
public abstract class AbstractRegistryFieldHandler extends AbstractFieldHandler
{
    /**
     * Returns <code>true</code> if the field type is a class or interface and
     * if there's a public static field of type {@link #getReaderClassname()} in
     * the field type, <code>false</code> otherwise.
     * 
     * @param writer
     * @param fieldContext
     * @return
     * @see name.pehl.piriti.rebind.propertyhandler.AbstractFieldHandler#isValid(name.pehl.piriti.rebind.propertyhandler.FieldContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        if (!fieldContext.isClassOrInterface())
        {
            CodeGeneration.skipField(writer, fieldContext, "Type is no class or interface");
            return false;
        }
        CodeGeneration.readerInitialization(writer, fieldContext.getClassOrInterfaceType());
        return true;
    }


    protected abstract String getReaderClassname();


    protected abstract String getWriterClassname();


    /**
     * Generates code for the reader lookup and starts the if statement
     * <code>if (reader != null) {</code>.
     * 
     * @param writer
     * @param fieldContext
     * @return the variable name of the reader
     */
    protected String startReader(IndentedWriter writer, FieldContext fieldContext, String registryName, JClassType type)
    {
        // Cast because subclasses might use a subtype of getReaderClassname()
        String readerVariable = fieldContext.newVariableName("Reader");
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
     * @param fieldContext
     * @return the variable name of the reader
     */
    protected String startWriter(IndentedWriter writer, FieldContext fieldContext, String registryName, JClassType type)
    {
        // Cast because subclasses might use a subtype of getReaderClassname()
        String writerVariable = fieldContext.newVariableName("Writer");
        writer.write("%1$s<%2$s> %3$s = (%1$s)this.%4$s.getWriter(%2$s.class);", getWriterClassname(),
                type.getQualifiedSourceName(), writerVariable, registryName);
        writer.write("if (%s != null) {", writerVariable);
        writer.indent();
        return writerVariable;
    }


    /**
     * Closes the if statement started by
     * {@link #startReader(IndentedWriter, FieldContext)} or
     * {@link #startWriter(IndentedWriter, FieldContext, String, JClassType)}.
     * 
     * @param writer
     */
    protected void endReader(IndentedWriter writer)
    {
        writer.outdent();
        writer.write("}");
    }
}
