package name.pehl.gwt.piriti.rebind;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JParameterizedType;

/**
 * {@link FieldHandler} for collections. TODO Implement me!
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class CollectionFieldHandler extends AbstractFieldHandler
{
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
    public boolean isValid(IndentedWriter writer, FieldContext fieldContext)
    {
        if (!fieldContext.isCollection())
        {
            skipField(writer, fieldContext, "Type is no collection");
            return false;
        }
        JParameterizedType parameterizedType = fieldContext.getType().isParameterized();
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


    @Override
    public void write(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        writeComment(writer, fieldContext);
        writer.write("// Not yet implemented!");
        writeDeclaration(writer, fieldContext);
    }
}
