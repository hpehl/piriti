package name.pehl.piriti.gxt.rebind.json.propertyhandler;

import name.pehl.piriti.gxt.rebind.ModelReaderConstants;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.propertyhandler.FieldContext;
import name.pehl.piriti.rebind.propertyhandler.FieldHandler;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * {@link FieldHandler} for collections.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class CollectionFieldHandler extends name.pehl.piriti.rebind.json.propertyhandler.CollectionFieldHandler
        implements ModelReaderConstants
{
    @Override
    public void declare(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        writer.write("%s<%s> %s = null;", fieldContext.getFieldType().getQualifiedSourceName(),
                getTypeVariable(fieldContext).getParameterizedQualifiedSourceName(), fieldContext.getValueVariable());
    }


    @Override
    protected JClassType getTypeVariable(FieldContext fieldContext)
    {
        JClassType typeVariable = null;
        Class<?> typeVariableClass = fieldContext.getMetadata(TYPE_VARIABLE);
        if (typeVariableClass != null && !Void.class.equals(typeVariableClass))
        {
            typeVariable = fieldContext.getTypeOracle().findType(typeVariableClass.getName());
        }
        return typeVariable;
    }
}
