package name.pehl.gwt.piriti.rebind;

/**
 * {@link FieldHandler} for maps. TODO Implement me!
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class MapFieldHandler extends AbstractFieldHandler
{
    @Override
    public void write(IndentedWriter writer, FieldContext fieldContext)
    {
        writeComment(writer, fieldContext);
        writer.write("// Not yet implemented!");
        writeDeclaration(writer, fieldContext);
    }
}
