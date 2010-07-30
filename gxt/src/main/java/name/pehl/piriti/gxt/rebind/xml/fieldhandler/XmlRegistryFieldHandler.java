package name.pehl.piriti.gxt.rebind.xml.fieldhandler;

import name.pehl.piriti.client.xml.XmlReader;
import name.pehl.piriti.gxt.client.xml.XmlModelReader;
import name.pehl.piriti.rebind.fieldhandler.FieldHandler;

/**
 * {@link FieldHandler} implementation for types with an own {@link XmlReader}.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class XmlRegistryFieldHandler extends name.pehl.piriti.rebind.xml.fieldhandler.XmlRegistryFieldHandler
{
    @Override
    protected String getReaderClassname()
    {
        return XmlModelReader.class.getName();
    }

    // TODO
    // @Override
    // protected String getWriterClassname()
    // {
    // return XmlModelWriter.class.getName();
    // }
}
