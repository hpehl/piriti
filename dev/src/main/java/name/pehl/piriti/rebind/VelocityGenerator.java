package name.pehl.piriti.rebind;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

/**
 * Abstract Generator which delegates to a {@link VelocityCreator}. The
 * {@link VelocityCreator} is responsible for generating the code.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 745 $
 */
public abstract class VelocityGenerator extends Generator
{
    @Override
    public String generate(TreeLogger treeLogger, GeneratorContext context, String typeName)
            throws UnableToCompleteException
    {
        Logger.get().setup(treeLogger);
        GeneratorContextHolder.get().setup(context);

        JClassType interfaceType;
        TypeOracle oracle = context.getTypeOracle();
        try
        {
            interfaceType = oracle.getType(typeName);
        }
        catch (NotFoundException e)
        {
            throw new RuntimeException(e);
        }

        String implName = interfaceType.getName().replace('.', '_') + "Impl";
        String packageName = interfaceType.getPackage().getName();

        VelocityCreator creator = createCreator(interfaceType, implName);
        creator.createCode();

        return packageName + "." + implName;
    }


    protected abstract VelocityCreator createCreator(JClassType interfaceType, String implName)
            throws UnableToCompleteException;
}
