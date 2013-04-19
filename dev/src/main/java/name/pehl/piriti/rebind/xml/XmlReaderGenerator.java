package name.pehl.piriti.rebind.xml;

import java.util.ArrayList;
import java.util.List;

import name.pehl.piriti.rebind.VelocityGenerator;
import name.pehl.piriti.rebind.property.PropertyContext;
import name.pehl.piriti.rebind.type.TypeContext;
import name.pehl.piriti.rebind.type.TypeUtils;
import name.pehl.piriti.xml.client.XmlReader;

import org.apache.velocity.VelocityContext;

import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

/**
 * GWT Generator which creates a {@link xmlReaderCreator}.
 *
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
 */
public class XmlReaderGenerator extends VelocityGenerator
{
    @Override
    protected String getInterfaceName()
    {
        return XmlReader.class.getCanonicalName();
    }


    @Override
    protected String getTemplateName()
    {
        return "name/pehl/piriti/rebind/xml/reader/reader.vm";
    }
}
