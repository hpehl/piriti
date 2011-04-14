package name.pehl.piriti.rebind.json.propertyhandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.TypeUtils;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerLookup;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JType;

/**
 * {@link PropertyHandler} for collections.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class CollectionPropertyHandler extends AbstractJsonPropertyHandler
{
    protected static Map<String, String> interfaceToImplementation = new HashMap<String, String>();
    static
    {
        interfaceToImplementation.put(Collection.class.getName(), ArrayList.class.getName());
        interfaceToImplementation.put(List.class.getName(), ArrayList.class.getName());
        interfaceToImplementation.put(Set.class.getName(), HashSet.class.getName());
        interfaceToImplementation.put(SortedSet.class.getName(), TreeSet.class.getName());
    }


    public CollectionPropertyHandler(TreeLogger logger)
    {
        super(logger);
    }


    @Override
    public boolean isValid(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        JType elementType = getElementType(propertyContext);
        if (elementType == null)
        {
            skipProperty(writer, propertyContext, "No type parameter found");
            return false;
        }
        if (elementType.isArray() != null)
        {
            skipProperty(writer, propertyContext, "Collections of arrays are not supported");
            return false;
        }
        if (TypeUtils.isCollection(elementType) || TypeUtils.isMap(elementType))
        {
            skipProperty(writer, propertyContext, "Collections of collections / maps are not supported");
            return false;
        }
        return true;
    }


    protected JType getElementType(PropertyContext propertyContext) throws UnableToCompleteException
    {
        return TypeUtils.getTypeVariable(propertyContext.getType());
    }


    /**
     * TODO Javadoc
     * 
     * @param writer
     * @param propertyContext
     * @param propertyHandlerLookup
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.xml.propertyhandler.ConverterPropertyHandler#readInput(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public void readInput(IndentedWriter writer, PropertyContext propertyContext,
            PropertyHandlerLookup propertyHandlerLookup) throws UnableToCompleteException
    {
        // The nested property context is created *without* a path. The nested
        // property handler must take care of this!
        JType elementType = getElementType(propertyContext);
        PropertyContext nestedPropertyContext = propertyContext.createNested(elementType, COLLECTION_ELEMENT_PATH);
        PropertyHandler nestedHandler = propertyHandlerLookup.lookup(nestedPropertyContext);
        if (!nestedHandler.isValid(writer, nestedPropertyContext))
        {
            warn("No PropertyHandler found for element type %s in %s", elementType, propertyContext);
            return;
        }

        getOrSelectJson(writer, propertyContext);
        writer.write("if (%s != null) {", jsonValueVariable);
        writer.indent();
        writer.write("JSONArray jsonArray = %s.isArray();", jsonValueVariable);
        writer.write("if (jsonArray != null) {");
        writer.indent();
        writer.write("int size = jsonArray.size();");
        String collectionImplementation = interfaceToImplementation.get(propertyContext.getType().getErasedType()
                .getQualifiedSourceName());
        if (collectionImplementation == null)
        {
            // the field type is already an implementation
            collectionImplementation = propertyContext.getType().getQualifiedSourceName();
        }
        writer.write("%s = new %s<%s>();", propertyContext.getVariableNames().getValueVariable(),
                collectionImplementation, elementType.getQualifiedSourceName());
        writer.write("for (int i = 0; i < size; i++) {");
        writer.indent();
        writer.write("JSONValue %s = jsonArray.get(i);", nestedPropertyContext.getVariableNames().getInputVariable());
        writer.write("if (%1$s != null && %1$s.isNull() == null) {", nestedPropertyContext.getVariableNames()
                .getInputVariable());
        writer.indent();
        nestedHandler.log(writer, nestedPropertyContext);
        nestedHandler.declare(writer, nestedPropertyContext);
        nestedHandler.readInput(writer, nestedPropertyContext, propertyHandlerLookup);
        writer.write("if (%s != null) {", nestedPropertyContext.getVariableNames().getValueVariable());
        writer.indent();
        writer.write("%s.add(%s);", propertyContext.getVariableNames().getValueVariable(), nestedPropertyContext
                .getVariableNames().getValueVariable());
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
    }


    @Override
    public void writeValue(IndentedWriter writer, PropertyContext propertyContext,
            PropertyHandlerLookup propertyHandlerLookup) throws UnableToCompleteException
    {
        // The nested property context is created *without* a path. The nested
        // property handler must take care of this!
        JType elementType = getElementType(propertyContext);
        PropertyContext nestedPropertyContext = propertyContext.createNested(elementType, null);
        PropertyHandler nestedHandler = propertyHandlerLookup.lookup(nestedPropertyContext);
        if (!nestedHandler.isValid(writer, nestedPropertyContext))
        {
            warn("No PropertyHandler found for element type %s in %s", elementType, propertyContext);
            return;
        }

        writer.write("if (%s == null) {", propertyContext.getVariableNames().getValueVariable());
        writer.indent();
        writer.write("%s.append(\"null\");", propertyContext.getVariableNames().getBuilderVariable());
        writer.outdent();
        writer.write("}");
        writer.write("else {");
        writer.indent();
        // Iterate over values
        writer.write("%s.append(\"[\");", propertyContext.getVariableNames().getBuilderVariable());
        writer.write("for (Iterator<%s> iter = %s.iterator(); iter.hasNext(); ) {",
                elementType.getQualifiedSourceName(), propertyContext.getVariableNames().getValueVariable());
        writer.indent();

        nestedHandler.log(writer, nestedPropertyContext);
        nestedHandler.declare(writer, nestedPropertyContext);
        // Replace nestedHandler.readField(writer, nestedFieldContext) with
        writer.write("%s = iter.next();", nestedPropertyContext.getVariableNames().getValueVariable());
        // No nestedHandler.markupStart(writer, nestedFieldContext); since we're
        // in an JSON array
        nestedHandler.writeValue(writer, nestedPropertyContext, propertyHandlerLookup);
        // No nestedHandler.markupEnd(writer, nestedFieldContext); since we're
        // in an JSON array

        writer.write("if (iter.hasNext()) {", propertyContext.getVariableNames().getValueVariable());
        writer.indent();
        writer.write("%s.append(\",\");", propertyContext.getVariableNames().getBuilderVariable());
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
        writer.write("%s.append(\"]\");", propertyContext.getVariableNames().getBuilderVariable());
        writer.outdent();
        writer.write("}");
    }
}
