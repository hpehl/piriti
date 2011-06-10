package name.pehl.piriti.rebind.xml.propertyhandler;

import java.util.logging.Level;

import name.pehl.piriti.rebind.CodeGeneration;
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
public class CollectionPropertyHandler extends AbstractXmlPropertyHandler
{
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


    @Override
    public void readInput(IndentedWriter writer, PropertyContext propertyContext,
            PropertyHandlerLookup propertyHandlerLookup) throws UnableToCompleteException
    {
        String nestedXpath = ".";
        JType elementType = getElementType(propertyContext);
        String nestedElementsVariable = propertyContext.getVariableNames().newVariableName("NestedElements");
        PropertyContext nestedPropertyContext = propertyContext.createNested(elementType, nestedXpath);
        PropertyHandler nestedHandler = propertyHandlerLookup.lookup(nestedPropertyContext);
        if (!nestedHandler.isValid(writer, nestedPropertyContext))
        {
            return;
        }

        writer.write("List<Element> %s = filterElements(%s.selectNodes(\"%s\"));", nestedElementsVariable,
                propertyContext.getVariableNames().getInputVariable(), propertyContext.getPathOrName());
        writer.write("if (!%1$s.isEmpty()) {", nestedElementsVariable);
        writer.indent();
        String collectionImplementation = CodeGeneration.collectionImplementationFor(propertyContext.getType()
                .getErasedType().getQualifiedSourceName());
        writer.write("%s = new %s<%s>();", propertyContext.getVariableNames().getValueVariable(),
                collectionImplementation, elementType.getQualifiedSourceName());
        writer.write("for (Element %s : %s) {", nestedPropertyContext.getVariableNames().getInputVariable(),
                nestedElementsVariable);
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
    }


    @Override
    public void writeValue(IndentedWriter writer, PropertyContext propertyContext,
            PropertyHandlerLookup propertyHandlerLookup) throws UnableToCompleteException
    {
        CodeGeneration.log(writer, Level.WARNING, "writeValue() NYI");
    }


    /**
     * Overwrites the method so that paths which match
     * {@link AbstractXmlPropertyHandler#NESTED_PATH_PATH_REGEX} are allowed.
     * 
     * @see name.pehl.piriti.rebind.xml.propertyhandler.AbstractXmlPropertyHandler#isXmlPath(java.lang.String)
     */
    @Override
    protected boolean isXmlPath(String path)
    {
        boolean xmlPath = super.isXmlPath(path);
        if (xmlPath)
        {
            if (path.matches(NESTED_PATH_PATH_REGEX))
            {
                xmlPath = false;
            }
        }
        return xmlPath;
    }
}
