package name.pehl.piriti.rebind;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

/**
 * Abstract Generator for writer implementations. The generator delegates to an
 * {@link AbstractWriterCreator} instance which is responsible for generating
 * the code.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public abstract class AbstractWriterGenerator extends Generator
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

        AbstractWriterCreator creator = createCreator(context, interfaceType, implName, logger);
        creator.create();

        return packageName + "." + implName;
    }


    protected abstract AbstractWriterCreator createCreator(GeneratorContext context, JClassType interfaceType,
            String implName, TreeLogger logger) throws UnableToCompleteException;
}
