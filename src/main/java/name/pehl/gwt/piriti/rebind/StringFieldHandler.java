package name.pehl.gwt.piriti.rebind;

public class StringFieldHandler extends AbstractFieldHandler
{
    @Override
    public void write(IndentedWriter writer, FieldContext fieldContext)
    {
        writeComment(writer, fieldContext);
        writer.write("String %s = XPathUtils.getValue(%s, \"%s\");", fieldContext.getValueVariable(), fieldContext
                .getSourceVariable(), fieldContext.getXpath());
        writeAssignment(writer, fieldContext);
    }
}
