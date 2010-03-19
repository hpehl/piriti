package name.pehl.piriti.gxt.rebind.xml.fieldhandler;

import name.pehl.piriti.gxt.rebind.AssignmentHelper;
import name.pehl.piriti.rebind.FieldContext;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.fieldhandler.FieldHandler;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * Simple {@link FieldHandler} implementation for strings.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class StringFieldHandler extends name.pehl.piriti.rebind.xml.fieldhandler.StringFieldHandler
{
    @Override
    public void writeAssignment(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        AssignmentHelper.writeAssignement(writer, fieldContext);
    }
}
