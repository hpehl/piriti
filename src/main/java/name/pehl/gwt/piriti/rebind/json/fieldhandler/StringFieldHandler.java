package name.pehl.gwt.piriti.rebind.json.fieldhandler;

import name.pehl.gwt.piriti.rebind.FieldContext;
import name.pehl.gwt.piriti.rebind.IndentedWriter;
import name.pehl.gwt.piriti.rebind.fieldhandler.AbstractFieldHandler;
import name.pehl.gwt.piriti.rebind.fieldhandler.FieldHandler;

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
     * @see name.pehl.gwt.piriti.rebind.fieldhandler.AbstractFieldHandler#isValid(name.pehl.gwt.piriti.rebind.FieldContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        return true;
    }


    /**
     * TODO Javadoc
     * 
     * @param writer
     * @param fieldContext
     * @throws UnableToCompleteException
     * @see name.pehl.gwt.piriti.rebind.fieldhandler.FieldHandler#writeConverterCode(name.pehl.gwt.piriti.rebind.IndentedWriter,
     *      name.pehl.gwt.piriti.rebind.FieldContext)
     */
    @Override
    public void writeConverterCode(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        writer.write("%s = XPathUtils.getValue(%s, \"%s\");", fieldContext.getValueVariable(), fieldContext
                .getInputVariable(), fieldContext.getPath());
    }
}
