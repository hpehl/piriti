package name.pehl.gwt.piriti.rebind;

public class DefaultFieldHandler extends AbstractFieldHandler
{
    @Override
    public void write(IndentedWriter writer, FieldContext fieldContext)
    {
        writeComment(writer, fieldContext);
        writeDeclaration(writer, fieldContext);
        writeConverterCode(writer, fieldContext);
        writeAssignment(writer, fieldContext);
    }
}
