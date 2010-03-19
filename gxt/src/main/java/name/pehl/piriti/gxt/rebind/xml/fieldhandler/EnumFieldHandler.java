package name.pehl.piriti.gxt.rebind.xml.fieldhandler;

import name.pehl.piriti.gxt.rebind.AssignmentHelper;
import name.pehl.piriti.rebind.FieldContext;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.fieldhandler.FieldHandler;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * {@link FieldHandler} for enum types. This implementation reads the XML data
 * as string and tries to convert it using <code>enumType.valueOf(String)</code>
 * .
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class EnumFieldHandler extends name.pehl.piriti.rebind.xml.fieldhandler.EnumFieldHandler
{
    @Override
    public void writeAssignment(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        AssignmentHelper.writeAssignement(writer, fieldContext);
    }
}
