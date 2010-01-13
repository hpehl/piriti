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
public class ArrayFieldHandler extends DefaultFieldHandler
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
    public boolean isValid(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
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


    /**
     * TODO Javadoc
     * 
     * @param writer
     * @param fieldContext
     * @throws UnableToCompleteException
     * @see name.pehl.gwt.piriti.rebind.DefaultFieldHandler#writeConverterCode(name.pehl.gwt.piriti.rebind.IndentedWriter,
     *      name.pehl.gwt.piriti.rebind.FieldContext)
     */
    @Override
    public void writeConverterCode(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        JArrayType arrayType = fieldContext.getArrayType();
        JType componentType = arrayType.getComponentType();
        String valueVariableAsList = fieldContext.getValueVariable() + "AsList";
        String nestedElementVariable = fieldContext.getValueVariable() + "NestedElement";
        String nestedElementsVariable = fieldContext.getValueVariable() + "NestedElements";
        String nestedValueVariable = fieldContext.getValueVariable() + "NestedValue";
        FieldContext nestedFieldContext = new FieldContext(fieldContext.getTypeOracle(), fieldContext
                .getHandlerRegistry(), fieldContext.getModelType(), componentType, nestedValueVariable, ".",
                fieldContext.getFormat(), nestedElementVariable, nestedValueVariable);
        FieldHandler nestedHandler = fieldContext.getHandlerRegistry().findFieldHandler(nestedFieldContext);

        writer.write("List<Element> %s = XPathUtils.getElements(%s, \"%s\");", nestedElementsVariable, fieldContext
                .getXmlVariable(), fieldContext.getXpath());
        writer.write("if (%1$s != null && !%1$s.isEmpty()) {", nestedElementsVariable);
        writer.indent();
        writer.write("List<%1$s> %2$s = new ArrayList<%1$s>();", componentType.getParameterizedQualifiedSourceName(),
                valueVariableAsList);
        writer.write("for (Element %s : %s) {", nestedElementVariable, nestedElementsVariable);
        writer.indent();
        nestedHandler.writeComment(writer, nestedFieldContext);
        nestedHandler.writeDeclaration(writer, nestedFieldContext);
        nestedHandler.writeConverterCode(writer, nestedFieldContext);
        writer.write("if (%s != null) {", nestedFieldContext.getValueVariable());
        writer.indent();
        writer.write("%s.add(%s);", valueVariableAsList, nestedFieldContext.getValueVariable());
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.write("if (!%s.isEmpty()) {", valueVariableAsList);
        writer.indent();
        writer.write("%s = %s.toArray(new %s{});", fieldContext.getValueVariable(), valueVariableAsList, fieldContext
                .getFieldType().getParameterizedQualifiedSourceName());
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
    }
}
