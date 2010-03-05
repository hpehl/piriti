package name.pehl.gwt.piriti.rebind.xml;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

/**
 * GWT Generator for XmlReaders. The generator delegates to
 * {@link XmlReaderCreator} which is responsible for generating the
 * implementation.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class XmlReaderGenerator extends Generator
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
        XmlReaderCreator xmlReaderwriter = new XmlReaderCreator(context, interfaceType, implName, logger);
        xmlReaderwriter.create();
        return packageName + "." + implName;
    }
}
