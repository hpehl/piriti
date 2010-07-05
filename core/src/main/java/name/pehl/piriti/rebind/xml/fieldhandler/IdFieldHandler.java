package name.pehl.piriti.rebind.xml.fieldhandler;

import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.fieldhandler.FieldContext;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * @author $Author$
 * @version $Date$ $Revision$
 */
public class IdFieldHandler extends StringFieldHandler
{
    @Override
    public void writeAssignment(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        writer.write("if (%s != null) {", fieldContext.getValueVariable());
        writer.indent();
        writer.write("model.%s = %s;", fieldContext.getFieldName(), fieldContext.getValueVariable());
        writer.write("this.idMap.put(model.%s, model);", fieldContext.getFieldName());
        writer.outdent();
        writer.write("}");
    }
}
