package name.pehl.gwt.piriti.rebind;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JParameterizedType;

/**
 * {@link FieldHandler} for collections. TODO Implement me!
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class CollectionFieldHandler extends DefaultFieldHandler
{
    private static Map<String, String> interfaceToImplementation = new HashMap<String, String>();
    static
    {
        interfaceToImplementation.put(Collection.class.getName(), ArrayList.class.getName());
        interfaceToImplementation.put(List.class.getName(), ArrayList.class.getName());
        interfaceToImplementation.put(Set.class.getName(), HashSet.class.getName());
        interfaceToImplementation.put(SortedSet.class.getName(), TreeSet.class.getName());
    }


    /**
     * Returns <code>false</code> if the field type is no collection, if the
     * collection has no type arguments or if the type argument of the
     * collection equals the model type, <code>true</code> otherwise.
     * 
     * @param writer
     * @param fieldContext
     * @return
     * @see name.pehl.gwt.piriti.rebind.AbstractFieldHandler#isValid(name.pehl.gwt.piriti.rebind.FieldContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        if (!fieldContext.isCollection())
        {
            skipField(writer, fieldContext, "Type is no collection");
            return false;
        }
        JParameterizedType parameterizedType = fieldContext.getFieldType().isParameterized();
        if (parameterizedType != null)
        {
            JClassType[] typeArgs = parameterizedType.getTypeArgs();
            for (JClassType typeArg : typeArgs)
            {
                if (fieldContext.getModelType().equals(typeArg))
                {
                    skipField(writer, fieldContext, "Type argument of the collection equals the model type");
                    return false;
                }
                if (TypeUtils.isCollection(typeArg) || TypeUtils.isMap(typeArg))
                {
                    skipField(writer, fieldContext, "Nested collections / maps are not supported");
                    return false;
                }
            }
        }
        else
        {
            // collections and maps without type arguments are not
            // supported!
            skipField(writer, fieldContext, "Collection has no type argument");
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
        JParameterizedType parameterizedType = fieldContext.getFieldType().isParameterized();
        JClassType typeArgument = parameterizedType.getTypeArgs()[0];
        String nestedElementVariable = fieldContext.getValueVariable() + "NestedElement";
        String nestedElementsVariable = fieldContext.getValueVariable() + "NestedElements";
        String nestedValueVariable = fieldContext.getValueVariable() + "NestedValue";
        // TODO The field name is misused as xpath and the xpath is null. This
        // way the method FieldContext.adjustXpath() generates the correct
        // xpath. This works, because the fieldName "." is never used here. Only
        // nestedHandler.writeAssignment() would use it, which is not called
        // here.
        FieldContext nestedFieldContext = new FieldContext(fieldContext.getTypeOracle(), fieldContext
                .getHandlerRegistry(), fieldContext.getModelType(), typeArgument, ".", null, fieldContext.getFormat(),
                nestedElementVariable, nestedValueVariable);
        FieldHandler nestedHandler = fieldContext.getHandlerRegistry().findFieldHandler(nestedFieldContext);

        writer.write("List<Element> %s = XPathUtils.getElements(%s, \"%s\");", nestedElementsVariable, fieldContext
                .getXmlVariable(), fieldContext.getXpath());
        writer.write("if (%1$s != null && !%1$s.isEmpty()) {", nestedElementsVariable);
        writer.indent();
        String collectionImplementation = interfaceToImplementation.get(fieldContext.getFieldType().getErasedType()
                .getQualifiedSourceName());
        if (collectionImplementation == null)
        {
            // the field type is already an implementation
            collectionImplementation = fieldContext.getFieldType().getParameterizedQualifiedSourceName();
        }
        writer.write("%s = new %s<%s>();", fieldContext.getValueVariable(), collectionImplementation, typeArgument
                .getQualifiedSourceName());
        writer.write("for (Element %s : %s) {", nestedElementVariable, nestedElementsVariable);
        writer.indent();
        nestedHandler.writeComment(writer, nestedFieldContext);
        nestedHandler.writeDeclaration(writer, nestedFieldContext);
        nestedHandler.writeConverterCode(writer, nestedFieldContext);
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
}
