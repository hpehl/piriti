package name.pehl.piriti.rebind.xml.fieldhandler;

import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.fieldhandler.FieldContext;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * @author $Author$
 * @version $Date$ $Revision: 638
 *          $
 */
public class IdFieldHandler extends StringFieldHandler
{
    @Override
    public void assign(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        super.assign(writer, fieldContext);
        writer.write("if (%s != null) {", fieldContext.getValueVariable());
        writer.indent();
        writer.write("this.idMap.put(%s, model);", fieldContext.getValueVariable());
        writer.outdent();
        writer.write("}");
    }
}
