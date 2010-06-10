package name.pehl.piriti.gxt.rebind.json.fieldhandler;

import name.pehl.piriti.gxt.rebind.CodeGeneration;
import name.pehl.piriti.rebind.FieldContext;
import name.pehl.piriti.rebind.IndentedWriter;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * @author $Author$
 * @version $Date$ $Revision: 161
 *          $
 */
public class BooleanFieldHandler extends name.pehl.piriti.rebind.json.fieldhandler.BooleanFieldHandler
{
    @Override
    public void writeAssignment(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        CodeGeneration.writeAssignement(writer, fieldContext);
    }
}
