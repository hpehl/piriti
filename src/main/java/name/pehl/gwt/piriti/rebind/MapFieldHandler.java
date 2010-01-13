package name.pehl.gwt.piriti.rebind;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JParameterizedType;

/**
 * {@link FieldHandler} for maps. TODO Implement me!
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class MapFieldHandler extends DefaultFieldHandler
{
    /**
     * Returns <code>false</code> if the field type is no map, if the map has no
     * type arguments or if one of the type arguments of the collection equals
     * the model type, <code>true</code> otherwise.
     * TODO Prevent nested maps
     * 
     * @param writer
     * @param fieldContext
     * @return
     * @see name.pehl.gwt.piriti.rebind.AbstractFieldHandler#isValid(name.pehl.gwt.piriti.rebind.FieldContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        if (!fieldContext.isMap())
        {
            skipField(writer, fieldContext, "Type is no map");
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
                    skipField(writer, fieldContext, "One of the type arguments of the map equals the model type");
                    return false;
                }
            }
        }
        else
        {
            // collections and maps without type arguments are not
            // supported!
            skipField(writer, fieldContext, "Map has no type arguments");
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
        writer.write("// Conversion not yet implemented!");
    }


    /**
     * TODO Javadoc
     * 
     * @param writer
     * @param fieldContext
     * @throws UnableToCompleteException
     * @see name.pehl.gwt.piriti.rebind.DefaultFieldHandler#writeAssignment(name.pehl.gwt.piriti.rebind.IndentedWriter,
     *      name.pehl.gwt.piriti.rebind.FieldContext)
     */
    @Override
    public void writeAssignment(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        writer.write("// Assignment not yet implemented!");
    }
}
