package name.pehl.piriti.gxt.rebind.json.propertyhandler;

import name.pehl.piriti.gxt.rebind.ModelReaderConstants;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.propertyhandler.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * {@link PropertyHandler} for collections.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class CollectionPropertyHandler extends name.pehl.piriti.rebind.json.propertyhandler.CollectionPropertyHandler
        implements ModelReaderConstants
{
    @Override
    public void declare(IndentedWriter writer, PropertyContext fieldContext) throws UnableToCompleteException
    {
        writer.write("%s<%s> %s = null;", fieldContext.getType().getQualifiedSourceName(),
                getTypeVariable(fieldContext).getParameterizedQualifiedSourceName(), fieldContext.getVariableNames()
                        .getValueVariable());
    }


    @Override
    protected JClassType getTypeVariable(PropertyContext fieldContext)
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
