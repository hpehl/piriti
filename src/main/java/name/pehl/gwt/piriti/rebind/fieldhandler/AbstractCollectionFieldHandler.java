package name.pehl.gwt.piriti.rebind.fieldhandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import name.pehl.gwt.piriti.rebind.FieldContext;
import name.pehl.gwt.piriti.rebind.IndentedWriter;
import name.pehl.gwt.piriti.rebind.TypeUtils;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JParameterizedType;

/**
 * Abstract {@link FieldHandler} for collections.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public abstract class AbstractCollectionFieldHandler extends AbstractFieldHandler
{
    protected static Map<String, String> interfaceToImplementation = new HashMap<String, String>();
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
     * @see name.pehl.gwt.piriti.rebind.fieldhandler.AbstractFieldHandler#isValid(name.pehl.gwt.piriti.rebind.FieldContext)
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
                if (typeArg.isArray() != null || TypeUtils.isCollection(typeArg) || TypeUtils.isMap(typeArg))
                {
                    skipField(writer, fieldContext, "Nested arrays / collections / maps are not supported");
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
}
