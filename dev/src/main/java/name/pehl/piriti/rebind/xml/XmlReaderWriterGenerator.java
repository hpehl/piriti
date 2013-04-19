package name.pehl.piriti.rebind.xml;

import name.pehl.piriti.rebind.AbstractReaderWriterGenerator;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlReaderWriter;
import name.pehl.piriti.xml.client.XmlWriter;

public class XmlReaderWriterGenerator extends AbstractReaderWriterGenerator
{
    @Override
    protected String getInterfaceClassName()
    {
        return XmlReaderWriter.class.getName();
    }

    @Override
    protected Class<?> getReaderClass()
    {
        return XmlReader.class;
    }

    @Override
    protected Class<?> getWriterClass()
    {
        return XmlWriter.class;
    }

    @Override
    protected String getFormat()
    {
        return "Xml";
    }
}
