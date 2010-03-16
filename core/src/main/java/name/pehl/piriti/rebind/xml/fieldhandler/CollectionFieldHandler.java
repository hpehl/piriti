package name.pehl.piriti.rebind.xml.fieldhandler;

import name.pehl.piriti.rebind.FieldContext;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.TypeUtils;
import name.pehl.piriti.rebind.fieldhandler.AbstractCollectionFieldHandler;
import name.pehl.piriti.rebind.fieldhandler.FieldHandler;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JParameterizedType;

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
     * @see name.pehl.piriti.rebind.xml.fieldhandler.ConverterFieldHandler#writeConverterCode(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.FieldContext)
     */
    @Override
    public void writeConverterCode(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        JParameterizedType parameterizedType = fieldContext.getFieldType().isParameterized();
        JClassType typeArgument = parameterizedType.getTypeArgs()[0];
        String nestedElementVariable = fieldContext.getValueVariable() + "NestedElement";
        String nestedElementsVariable = fieldContext.getValueVariable() + "NestedElements";
        String nestedValueVariable = fieldContext.getValueVariable() + "NestedValue";
        String nestedXpath = ".";
        if (typeArgument.isPrimitive() != null || TypeUtils.isBasicType(typeArgument) || typeArgument.isEnum() != null)
        {
            nestedXpath += "/text()";
        }

        FieldContext nestedFieldContext = new FieldContext(fieldContext.getTypeOracle(), fieldContext
                .getHandlerRegistry(), fieldContext.getModelType(), typeArgument, fieldContext.getFieldName(),
                nestedXpath, fieldContext.getFormat(), nestedElementVariable, nestedValueVariable);
        FieldHandler nestedHandler = fieldContext.getHandlerRegistry().findFieldHandler(nestedFieldContext);

        writer.write("List<Element> %s = XPathUtils.getElements(%s, \"%s\");", nestedElementsVariable, fieldContext
                .getInputVariable(), fieldContext.getPath());
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
