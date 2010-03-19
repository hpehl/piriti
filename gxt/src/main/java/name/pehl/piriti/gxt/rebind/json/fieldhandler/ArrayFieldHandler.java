package name.pehl.piriti.gxt.rebind.json.fieldhandler;

import name.pehl.piriti.gxt.rebind.AssignmentHelper;
import name.pehl.piriti.rebind.FieldContext;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.fieldhandler.FieldHandler;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * {@link FieldHandler} for arrays.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class ArrayFieldHandler extends name.pehl.piriti.rebind.json.fieldhandler.ArrayFieldHandler
{
    @Override
    public void writeAssignment(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        AssignmentHelper.writeAssignement(writer, fieldContext);
    }
}
