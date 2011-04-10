package name.pehl.piriti.rebind.xml;

import name.pehl.piriti.rebind.PojoTypeProcessor;
import name.pehl.piriti.rebind.TypeUtils;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.core.ext.typeinfo.JType;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class XmlPojoTypeProcessor extends PojoTypeProcessor
{
    public XmlPojoTypeProcessor(TreeLogger logger)
    {
        super(logger);
    }


    @Override
    protected String getPath(JField field)
    {
        String path = super.getPath(field);
        JType type = field.getType();
        if (type.isPrimitive() != null || TypeUtils.isBasicType(type) || type.isEnum() != null)
        {
            path += "/text()";
        }
        return path;
    }
}
