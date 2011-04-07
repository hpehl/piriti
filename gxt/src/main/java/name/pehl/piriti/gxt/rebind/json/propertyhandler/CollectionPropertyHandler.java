package name.pehl.piriti.gxt.rebind.json.propertyhandler;

import name.pehl.piriti.gxt.rebind.ModelReaderConstants;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.PropertyContext;
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
    public void declare(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        writer.write("%s<%s> %s = null;", propertyContext.getType().getQualifiedSourceName(),
                getTypeVariable(propertyContext).getParameterizedQualifiedSourceName(), propertyContext
                        .getVariableNames().getValueVariable());
    }


    @Override
    protected JClassType getTypeVariable(PropertyContext propertyContext)
    {
        JClassType typeVariable = null;
        Class<?> typeVariableClass = propertyContext.getMetadata(TYPE_VARIABLE);
        if (typeVariableClass != null && !Void.class.equals(typeVariableClass))
        {
            typeVariable = propertyContext.getTypeContext().getTypeOracle().findType(typeVariableClass.getName());
        }
        return typeVariable;
    }
}
