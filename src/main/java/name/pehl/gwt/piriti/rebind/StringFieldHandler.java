package name.pehl.gwt.piriti.rebind;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * Simple {@link FieldHandler} implementation for strings.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class StringFieldHandler extends DefaultFieldHandler
{
    /**
     * TODO Javadoc
     * 
     * @param writer
     * @param fieldContext
     * @throws UnableToCompleteException
     * @see name.pehl.gwt.piriti.rebind.FieldHandler#writeConverterCode(name.pehl.gwt.piriti.rebind.IndentedWriter,
     *      name.pehl.gwt.piriti.rebind.FieldContext)
     */
    @Override
    public void writeConverterCode(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        writer.write("%s = XPathUtils.getValue(%s, \"%s\");", fieldContext.getValueVariable(), fieldContext
                .getXmlVariable(), fieldContext.getXpath());
    }
}
