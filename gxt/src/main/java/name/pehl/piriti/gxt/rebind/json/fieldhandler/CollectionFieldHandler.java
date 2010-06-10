package name.pehl.piriti.gxt.rebind.json.fieldhandler;

import name.pehl.piriti.gxt.rebind.CodeGeneration;
import name.pehl.piriti.gxt.rebind.ModelReaderConstants;
import name.pehl.piriti.rebind.FieldContext;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.fieldhandler.FieldHandler;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * {@link FieldHandler} for collections.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class CollectionFieldHandler extends name.pehl.piriti.rebind.json.fieldhandler.CollectionFieldHandler implements
        ModelReaderConstants
{
    @Override
    public void writeDeclaration(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        writer.write("%s<%s> %s = null;", fieldContext.getFieldType().getQualifiedSourceName(), getTypeVariable(
                fieldContext).getParameterizedQualifiedSourceName(), fieldContext.getValueVariable());
    }


    @Override
    public void writeAssignment(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        CodeGeneration.writeAssignement(writer, fieldContext);
    }


    @Override
    protected JClassType getTypeVariable(FieldContext fieldContext)
    {
        JClassType typeVariable = null;
        Class<?> typeVariableClass = fieldContext.getMetadata(TYPE_VARIABLE);
        if (typeVariableClass != null)
        {
            typeVariable = fieldContext.getTypeOracle().findType(typeVariableClass.getName());
        }
        return typeVariable;
    }
}
