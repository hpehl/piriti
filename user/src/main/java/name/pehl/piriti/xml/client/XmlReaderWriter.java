package name.pehl.piriti.xml.client;

public interface XmlReaderWriter<T>
{
    XmlReader<T> getXmlReader();

    XmlWriter<T> getXmlWriter();
}
