package name.pehl.piriti.rebind.xml.fieldhandler;

import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.TypeUtils;
import name.pehl.piriti.rebind.fieldhandler.AbstractCollectionFieldHandler;
import name.pehl.piriti.rebind.fieldhandler.AssignmentPolicy;
import name.pehl.piriti.rebind.fieldhandler.AssignmentType;
import name.pehl.piriti.rebind.fieldhandler.FieldContext;
import name.pehl.piriti.rebind.fieldhandler.FieldHandler;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * {@link FieldHandler} for collections.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class CollectionFieldHandler extends AbstractCollectionFieldHandler
{
    /**
     * TODO Javadoc
     * 
     * @param writer
     * @param fieldContext
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.xml.fieldhandler.ConverterFieldHandler#readInput(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.fieldhandler.FieldContext)
     */
    @Override
    public void readInput(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        JClassType parameterType = getTypeVariable(fieldContext);
        String nestedElementVariable = fieldContext.newVariableName("NestedElement");
        String nestedElementsVariable = fieldContext.newVariableName("NestedElements");
        String nestedValueVariable = fieldContext.newVariableName("NestedValue");
        String nestedXpath = ".";
        if (parameterType.isPrimitive() != null || TypeUtils.isBasicType(parameterType)
                || parameterType.isEnum() != null)
        {
            nestedXpath += "/text()";
        }

        FieldContext nestedFieldContext = new FieldContext(fieldContext.getTypeOracle(),
                fieldContext.getHandlerRegistry(), fieldContext.getModelType(), parameterType,
                fieldContext.getFieldName(), nestedXpath, fieldContext.getFormat(), fieldContext.isStripWsnl(),
                AssignmentType.MAPPING, AssignmentPolicy.FIELD_ONLY, nestedElementVariable, nestedValueVariable,
                fieldContext.getBuilderVariable());
        FieldHandler nestedHandler = fieldContext.getHandlerRegistry().findFieldHandler(nestedFieldContext);
        if (!nestedHandler.isValid(writer, nestedFieldContext))
        {
            return;
        }

        writer.write("List<Element> %s = filterElements(%s.selectNodes(\"%s\"));", nestedElementsVariable,
                fieldContext.getInputVariable(), fieldContext.getPath());
        writer.write("if (!%1$s.isEmpty()) {", nestedElementsVariable);
        writer.indent();
        String collectionImplementation = interfaceToImplementation.get(fieldContext.getFieldType().getErasedType()
                .getQualifiedSourceName());
        if (collectionImplementation == null)
        {
            // the field type is already an implementation
            collectionImplementation = fieldContext.getFieldType().getParameterizedQualifiedSourceName();
        }
        writer.write("%s = new %s<%s>();", fieldContext.getValueVariable(), collectionImplementation,
                parameterType.getQualifiedSourceName());
        writer.write("for (Element %s : %s) {", nestedElementVariable, nestedElementsVariable);
        writer.indent();
        nestedHandler.comment(writer, nestedFieldContext);
        nestedHandler.declare(writer, nestedFieldContext);
        nestedHandler.readInput(writer, nestedFieldContext);
        writer.write("if (%s != null) {", nestedFieldContext.getValueVariable());
        writer.indent();
        writer.write("%s.add(%s);", fieldContext.getValueVariable(), nestedFieldContext.getValueVariable());
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
    }


    @Override
    public void markupStart(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        writer.write("// markupStart() NYI");
    }


    @Override
    public void writeValue(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        writer.write("// writeValue() NYI");
    }


    @Override
    public void markupEnd(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        writer.write("// markupEnd() NYI");
    }
}
