package name.pehl.piriti.rebind.propertyhandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.TypeUtils;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * Abstract {@link PropertyHandler} for collections.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
 */
public abstract class AbstractCollectionPropertyHandler extends AbstractPropertyHandler
{
    public static Map<String, String> interfaceToImplementation = new HashMap<String, String>();
    static
    {
        interfaceToImplementation.put(Collection.class.getName(), ArrayList.class.getName());
        interfaceToImplementation.put(List.class.getName(), ArrayList.class.getName());
        interfaceToImplementation.put(Set.class.getName(), HashSet.class.getName());
        interfaceToImplementation.put(SortedSet.class.getName(), TreeSet.class.getName());
    }


    public AbstractCollectionPropertyHandler(TreeLogger logger)
    {
        super(logger);
    }


    /**
     * Returns <code>false</code> if the properties type is no collection, if
     * the collection has no type arguments or if the type argument of the
     * collection is an array, collection or map, <code>true</code> otherwise.
     * 
     * @param writer
     * @param propertyContext
     * @return
     * @see name.pehl.piriti.rebind.propertyhandler.AbstractPropertyHandler#isValid(name.pehl.piriti.rebind.propertyhandler.PropertyContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        if (!propertyContext.isCollection())
        {
            CodeGeneration.skipProperty(writer, propertyContext, "Type is no collection");
            return false;
        }
        JClassType parameterType = getTypeVariable(propertyContext);
        if (parameterType != null)
        {
            if (parameterType.isArray() != null || TypeUtils.isCollection(parameterType)
                    || TypeUtils.isMap(parameterType))
            {
                CodeGeneration.skipProperty(writer, propertyContext,
                        "Nested arrays / collections / maps are not supported");
                return false;
            }
        }
        else
        {
            // collections and maps without type arguments are not
            // supported!
            CodeGeneration.skipProperty(writer, propertyContext, "Collection has no / invalid type argument");
            return false;
        }
        // Initialize the parameter type to make sure the relevant reader
        // is in the registry (ugly - but it works)
        CodeGeneration.readerWriterInitialization(writer, parameterType);
        return true;
    }


    protected JClassType getTypeVariable(PropertyContext propertyContext)
    {
        return TypeUtils.getTypeVariable(propertyContext.getType());
    }
}
