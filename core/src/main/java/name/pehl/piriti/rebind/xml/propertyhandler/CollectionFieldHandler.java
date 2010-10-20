package name.pehl.piriti.rebind.xml.propertyhandler;

import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.TypeUtils;
import name.pehl.piriti.rebind.propertyhandler.AbstractCollectionPropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.AssignmentPolicy;
import name.pehl.piriti.rebind.propertyhandler.AssignmentType;
import name.pehl.piriti.rebind.propertyhandler.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * {@link PropertyHandler} for collections.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class CollectionFieldHandler extends AbstractCollectionPropertyHandler
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

        PropertyContext nestedFieldContext = new PropertyContext(fieldContext.getTypeOracle(),
                fieldContext.getHandlerRegistry(), fieldContext.getModelType(), parameterType,
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
