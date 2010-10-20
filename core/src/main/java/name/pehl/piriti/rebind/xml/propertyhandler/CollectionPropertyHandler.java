package name.pehl.piriti.rebind.xml.propertyhandler;

import static name.pehl.piriti.rebind.propertyhandler.Assignment.AssignmentPolicy.*;
import static name.pehl.piriti.rebind.propertyhandler.Assignment.AssignmentType.*;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.TypeUtils;
import name.pehl.piriti.rebind.propertyhandler.AbstractCollectionPropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.Assignment;
import name.pehl.piriti.rebind.propertyhandler.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.VariableNames;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * {@link PropertyHandler} for collections.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class CollectionPropertyHandler extends AbstractCollectionPropertyHandler
{
    /**
     * TODO Javadoc
     * 
     * @param writer
     * @param propertyContext
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.xml.propertyhandler.ConverterPropertyHandler#readInput(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public void readInput(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        JClassType parameterType = getTypeVariable(propertyContext);
        String nestedElementVariable = propertyContext.getVariableNames().newVariableName("NestedElement");
        String nestedElementsVariable = propertyContext.getVariableNames().newVariableName("NestedElements");
        String nestedValueVariable = propertyContext.getVariableNames().newVariableName("NestedValue");
        String nestedXpath = ".";
        if (parameterType.isPrimitive() != null || TypeUtils.isBasicType(parameterType)
                || parameterType.isEnum() != null)
        {
            nestedXpath += "/text()";
        }

        // TODO Implement usage of setters
        Assignment assignment = new Assignment(MAPPING, FIELD_FIRST);
        VariableNames variableNames = new VariableNames(nestedElementVariable, nestedValueVariable, propertyContext
                .getVariableNames().getBuilderVariable());
        PropertyContext nestedFieldContext = new PropertyContext(propertyContext.getTypeOracle(),
                propertyContext.getHandlerRegistry(), propertyContext.getClazz(), parameterType,
                propertyContext.getName(), nestedXpath, propertyContext.getFormat(), propertyContext.isStripWsnl(),
                assignment, variableNames);
        PropertyHandler nestedHandler = propertyContext.getHandlerRegistry().findPropertyHandler(nestedFieldContext);
        if (!nestedHandler.isValid(writer, nestedFieldContext))
        {
            return;
        }

        writer.write("List<Element> %s = filterElements(%s.selectNodes(\"%s\"));", nestedElementsVariable,
                propertyContext.getVariableNames().getInputVariable(), propertyContext.getPath());
        writer.write("if (!%1$s.isEmpty()) {", nestedElementsVariable);
        writer.indent();
        String collectionImplementation = interfaceToImplementation.get(propertyContext.getType().getErasedType()
                .getQualifiedSourceName());
        if (collectionImplementation == null)
        {
            // the field type is already an implementation
            collectionImplementation = propertyContext.getType().getParameterizedQualifiedSourceName();
        }
        writer.write("%s = new %s<%s>();", propertyContext.getVariableNames().getValueVariable(),
                collectionImplementation, parameterType.getQualifiedSourceName());
        writer.write("for (Element %s : %s) {", nestedElementVariable, nestedElementsVariable);
        writer.indent();
        nestedHandler.comment(writer, nestedFieldContext);
        nestedHandler.declare(writer, nestedFieldContext);
        nestedHandler.readInput(writer, nestedFieldContext);
        writer.write("if (%s != null) {", nestedFieldContext.getVariableNames().getValueVariable());
        writer.indent();
        writer.write("%s.add(%s);", propertyContext.getVariableNames().getValueVariable(), nestedFieldContext
                .getVariableNames().getValueVariable());
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
    }


    @Override
    public void markupStart(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        writer.write("// markupStart() NYI");
    }


    @Override
    public void writeValue(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        writer.write("// writeValue() NYI");
    }


    @Override
    public void markupEnd(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        writer.write("// markupEnd() NYI");
    }
}
