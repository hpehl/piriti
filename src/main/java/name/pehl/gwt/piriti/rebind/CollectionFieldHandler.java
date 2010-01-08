package name.pehl.gwt.piriti.rebind;

/**
 * {@link FieldHandler} for collections. TODO Implement me!
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class CollectionFieldHandler extends AbstractFieldHandler
{
    @Override
    public void write(IndentedWriter writer, FieldContext fieldContext)
    {
        writeComment(writer, fieldContext);
        writer.write("// Not yet implemented!");
        writeDeclaration(writer, fieldContext);
    }
}
