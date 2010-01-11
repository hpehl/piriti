package name.pehl.gwt.piriti.rebind;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JArrayType;
import com.google.gwt.core.ext.typeinfo.JType;

/**
 * {@link FieldHandler} for arrays. TODO Implement me!
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class ArrayFieldHandler extends AbstractFieldHandler
{
    /**
     * Returns <code>false</code> if the field type is no array or if the
     * component type of the array equals the model type, <code>true</code>
     * otherwise.
     * 
     * @param writer
     * @param fieldContext
     * @return
     * @see name.pehl.gwt.piriti.rebind.AbstractFieldHandler#isValid(name.pehl.gwt.piriti.rebind.FieldContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, FieldContext fieldContext)
    {
        if (!fieldContext.isArray())
        {
            skipField(writer, fieldContext, "Type is no array");
            return false;
        }
        if (fieldContext.getArrayType().getComponentType().equals(fieldContext.getModelType()))
        {
            skipField(writer, fieldContext, "Component type of the array equals the model type");
            return false;
        }
        return true;
    }


    @Override
    public void write(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        JArrayType arrayType = fieldContext.getArrayType();
        JType componentType = arrayType.getComponentType();
        FieldContext nestedFieldContext = new FieldContext(fieldContext.getTypeOracle(), fieldContext.getModelType(),
                fieldContext.getHandlerRegistry(), fieldContext.getSourceType(), fieldContext.getSourceVariable(),
                fieldContext.getXmlField(), null, null);

        String nestedElementVariable = fieldContext.getValueVariable() + "NestedElement";
        String nestedElementsVariable = fieldContext.getValueVariable() + "NestedElements";

        writeComment(writer, fieldContext);
        writer.write("List<Element> %s = XPathUtils.getElement(%s, \"%s\");", nestedElementsVariable, fieldContext
                .getSourceVariable(), fieldContext.getXpath());
        writer.write("if (%1$s != null && !%1$s.isEmpty()) {", nestedElementsVariable);
        writer.indent();
        writer.write("int index = 0");
        writer.write("int size = %s.size();", nestedElementsVariable);
        writer.write("for (Element %s : %s) {", nestedElementVariable, nestedElementsVariable);
        writer.indent();
        writer.write("");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");

        writer.write("// Not yet implemented!");
        writeDeclaration(writer, fieldContext);
    }
}
