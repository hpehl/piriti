package name.pehl.gwt.piriti.rebind;

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
