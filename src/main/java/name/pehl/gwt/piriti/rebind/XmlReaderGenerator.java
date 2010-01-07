package name.pehl.gwt.piriti.rebind;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

/**
 * @author $Author:$
 * @version $Revision:$
 */
public class XmlReaderGenerator extends Generator
{
    /**
     * @param logger
     * @param context
     * @param typeName
     * @return
     * @throws UnableToCompleteException
     * @see com.google.gwt.core.ext.Generator#generate(com.google.gwt.core.ext.TreeLogger,
     *      com.google.gwt.core.ext.GeneratorContext, java.lang.String)
     */
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
        XmlReaderCreator xmlReaderwriter = new XmlReaderCreator(context, interfaceType, implName, logger);
        xmlReaderwriter.create();
        return packageName + "." + implName;
    }
}
