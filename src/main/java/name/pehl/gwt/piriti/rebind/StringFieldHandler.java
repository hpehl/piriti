package name.pehl.gwt.piriti.rebind;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * Simple {@link FieldHandler} implementation for strings.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class StringFieldHandler extends AbstractFieldHandler
{
    /**
     * Returns always <code>true</code>.
     * 
     * @param writer
     * @param fieldContext
     * @return always <code>true</code>
     * @see name.pehl.gwt.piriti.rebind.AbstractFieldHandler#isValid(name.pehl.gwt.piriti.rebind.FieldContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, FieldContext fieldContext)
    {
        return true;
    }


    @Override
    public void write(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        writeComment(writer, fieldContext);
        writer.write("String %s = XPathUtils.getValue(%s, \"%s\");", fieldContext.getValueVariable(), fieldContext
                .getSourceVariable(), fieldContext.getXpath());
        writeAssignment(writer, fieldContext);
    }
}
