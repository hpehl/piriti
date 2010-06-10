package name.pehl.piriti.gxt.rebind;

import name.pehl.piriti.rebind.FieldContext;
import name.pehl.piriti.rebind.IndentedWriter;

/**
 * @author $Author$
 * @version $Date$ $Revision: 236
 *          $
 */
public final class CodeGeneration
{
    private CodeGeneration()
    {
    }


    /**
     * Writes the assignment for GXT models.
     * 
     * @param writer
     * @param fieldContext
     */
    public static void writeAssignement(IndentedWriter writer, FieldContext fieldContext)
    {
        writer.write("if (%s != null) {", fieldContext.getValueVariable());
        writer.indent();
        writer.write("model.set(\"%s\", %s);", fieldContext.getFieldName(), fieldContext.getValueVariable());
        writer.outdent();
        writer.write("}");
    }
}
