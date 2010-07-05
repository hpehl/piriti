package name.pehl.piriti.gxt.rebind.json.fieldhandler;

import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.gxt.client.json.JsonModelReader;
import name.pehl.piriti.gxt.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.fieldhandler.FieldContext;
import name.pehl.piriti.rebind.fieldhandler.FieldHandler;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * {@link FieldHandler} implementation for types with an own {@link JsonReader}.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class JsonRegistryFieldHandler extends name.pehl.piriti.rebind.json.fieldhandler.JsonRegistryFieldHandler
{
    @Override
    public void writeAssignment(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        CodeGeneration.writeAssignement(writer, fieldContext);
    }


    @Override
    protected String getReaderClassname()
    {
        return JsonModelReader.class.getName();
    }
}
