package name.pehl.gwt.piriti.rebind;

/**
 * {@link FieldHandler} for arrays. TODO Implement me!
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class ArrayFieldHandler extends AbstractFieldHandler
{
    @Override
    public void write(IndentedWriter writer, FieldContext fieldContext)
    {
        // assert fieldContext.isArray();
        // JArrayType arrayType = fieldContext.getArrayType();

        writeComment(writer, fieldContext);
        writer.write("// Not yet implemented!");
        writeDeclaration(writer, fieldContext);
    }
}
