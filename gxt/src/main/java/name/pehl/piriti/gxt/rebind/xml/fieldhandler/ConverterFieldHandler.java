package name.pehl.piriti.gxt.rebind.xml.fieldhandler;

import name.pehl.piriti.client.converter.Converter;
import name.pehl.piriti.client.converter.ConverterRegistry;
import name.pehl.piriti.gxt.rebind.AssignmentHelper;
import name.pehl.piriti.rebind.FieldContext;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.fieldhandler.FieldHandler;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * {@link FieldHandler} implementation which uses a {@link Converter} from the
 * {@link ConverterRegistry}.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class ConverterFieldHandler extends name.pehl.piriti.rebind.xml.fieldhandler.ConverterFieldHandler
{
    @Override
    public void writeAssignment(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        AssignmentHelper.writeAssignement(writer, fieldContext);
    }
}
