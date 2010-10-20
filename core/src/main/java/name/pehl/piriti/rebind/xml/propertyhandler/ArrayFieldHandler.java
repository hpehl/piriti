package name.pehl.piriti.rebind.xml.propertyhandler;

import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.TypeUtils;
import name.pehl.piriti.rebind.propertyhandler.AbstractArrayPropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.AssignmentPolicy;
import name.pehl.piriti.rebind.propertyhandler.AssignmentType;
import name.pehl.piriti.rebind.propertyhandler.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JArrayType;
import com.google.gwt.core.ext.typeinfo.JPrimitiveType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;

/**
 * {@link PropertyHandler} for arrays.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class ArrayFieldHandler extends AbstractArrayPropertyHandler
{
    /**
     * TODO Javadoc
     * 
     * @param writer
     * @param fieldContext
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.xml.propertyhandler.ConverterFieldHandler#readInput(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public void readInput(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException
    {
        JArrayType arrayType = fieldContext.getArrayType();
        JType componentType = arrayType.getComponentType();
        JPrimitiveType primitiveComponentType = componentType.isPrimitive();
        if (primitiveComponentType != null)
        {
            try
            {
                componentType = fieldContext.getTypeOracle().getType(
                        primitiveComponentType.getQualifiedBoxedSourceName());
            }
            catch (NotFoundException e)
            {
                throw new UnableToCompleteException();
            }
        }
        String valueVariableAsList = fieldContext.newVariableName("AsList");
        String nestedElementVariable = fieldContext.newVariableName("NestedElement");
        String nestedElementsVariable = fieldContext.newVariableName("NestedElements");
        String nestedValueVariable = fieldContext.newVariableName("NestedValue");
        String nestedXpath = ".";
        if (componentType.isPrimitive() != null || TypeUtils.isBasicType(componentType)
                || componentType.isEnum() != null)
        {
            nestedXpath += "/text()";
        }

        PropertyContext nestedFieldContext = new PropertyContext(fieldContext.getTypeOracle(),
                fieldContext.getHandlerRegistry(), fieldContext.getModelType(), componentType,
                fieldContext.getFieldName(), nestedXpath, fieldContext.getFormat(), fieldContext.isStripWsnl(),
                AssignmentType.MAPPING, AssignmentPolicy.FIELD_ONLY, nestedElementVariable, nestedValueVariable,
                fieldContext.getBuilderVariable());
        PropertyHandler nestedHandler = fieldContext.getHandlerRegistry().findFieldHandler(nestedFieldContext);
        if (!nestedHandler.isValid(writer, nestedFieldContext))
        {
            return;
        }
        writer.write("List<Element> %s = filterElements(%s.selectNodes(\"%s\"));", nestedElementsVariable,
                fieldContext.getInputVariable(), fieldContext.getPath());
        writer.write("if (!%1$s.isEmpty()) {", nestedElementsVariable);
        writer.indent();
        writer.write("List<%1$s> %2$s = new ArrayList<%1$s>();", componentType.getParameterizedQualifiedSourceName(),
                valueVariableAsList);
        writer.write("for (Element %s : %s) {", nestedElementVariable, nestedElementsVariable);
        writer.indent();
        nestedHandler.comment(writer, nestedFieldContext);
        nestedHandler.declare(writer, nestedFieldContext);
        nestedHandler.readInput(writer, nestedFieldContext);
        writer.write("if (%s != null) {", nestedFieldContext.getValueVariable());
        writer.indent();
        writer.write("%s.add(%s);", valueVariableAsList, nestedFieldContext.getValueVariable());
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.write("if (!%s.isEmpty()) {", valueVariableAsList);
        writer.indent();
        writer.write("int index = 0;");
        if (primitiveComponentType != null)
        {
            writer.write("%s = new %s[%s.size()];", fieldContext.getValueVariable(),
                    primitiveComponentType.getQualifiedSourceName(), valueVariableAsList);
        }
        else
        {
            writer.write("%s = new %s[%s.size()];", fieldContext.getValueVariable(),
                    componentType.getQualifiedSourceName(), valueVariableAsList);
        }
        writer.write("for(%s currentValue : %s) {", componentType.getQualifiedSourceName(), valueVariableAsList);
        writer.indent();
        writer.write("%s[index] = currentValue;", fieldContext.getValueVariable());
        writer.write("index++;");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
    }


    @Override
    public void markupStart(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException
    {
        writer.write("// markupStart() NYI");
    }


    @Override
    public void writeValue(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException
    {
        writer.write("// writeValue() NYI");
    }


    @Override
    public void markupEnd(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException
    {
        writer.write("// markupEnd() NYI");
    }
}
