package name.pehl.piriti.rebind;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

/**
 * Abstract Generator for reader implementations. The generator delegates to an
 * {@link AbstractReaderCreator} instance which is responsible for generating
 * the code.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 136 $
 */
public abstract class AbstractReaderGenerator extends Generator
{
    @Override
    public String generate(TreeLogger logger, GeneratorContext context, String typeName)
            throws UnableToCompleteException
    {
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

        AbstractReaderCreator creator = createCreator(context, interfaceType, implName, logger);
        creator.create();

        return packageName + "." + implName;
    }


    protected abstract AbstractReaderCreator createCreator(GeneratorContext context, JClassType interfaceType,
            String implName, TreeLogger logger) throws UnableToCompleteException;
}
