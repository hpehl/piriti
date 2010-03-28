package name.pehl.piriti.rebind.fieldhandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import name.pehl.piriti.rebind.FieldContext;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.TypeUtils;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JParameterizedType;

/**
 * Abstract {@link FieldHandler} for collections.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
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
     * @see name.pehl.piriti.rebind.fieldhandler.AbstractFieldHandler#isValid(name.pehl.piriti.rebind.FieldContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        if (!fieldContext.isCollection())
        {
            skipField(writer, fieldContext, "Type is no collection");
            return false;
        }
        JClassType parameterType = getTypeVariable(fieldContext);
        if (parameterType != null)
        {
//            if (fieldContext.getModelType().equals(parameterType))
//            {
//                skipField(writer, fieldContext, "Type argument of the collection equals the model type");
//                return false;
//            }
            if (parameterType.isArray() != null || TypeUtils.isCollection(parameterType)
                    || TypeUtils.isMap(parameterType))
            {
                skipField(writer, fieldContext, "Nested arrays / collections / maps are not supported");
                return false;
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


    protected JClassType getTypeVariable(FieldContext fieldContext)
    {
        JClassType parameterType = null;
        JParameterizedType parameterizedType = fieldContext.getFieldType().isParameterized();
        if (parameterizedType != null)
        {
            JClassType[] typeArgs = parameterizedType.getTypeArgs();
            if (typeArgs != null && typeArgs.length > 0)
            {
                parameterType = typeArgs[0];
            }
        }
        return parameterType;
    }
}
