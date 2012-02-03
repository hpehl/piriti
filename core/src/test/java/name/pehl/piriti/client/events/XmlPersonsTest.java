package name.pehl.piriti.client.events;

import name.pehl.totoe.xml.client.Element;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 133 $
 */
public class XmlPersonsTest extends AbstractPersonsTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        CountHandler<Element> handler = new CountHandler<Element>();
        Person.XML_READER.addModelReadHandler(handler);

        String xml = PersonResources.INSTANCE.personsXml().getText();
        Person.XML_READER.readList(xml);
        assertEquals(PersonResources.COUNT, handler.readCount);
    }


    // ------------------------------------------------------------ write tests

    public void _testWrite()
    {
        CountHandler<Element> handler = new CountHandler<Element>();
        Person.XML_WRITER.addModelWriteHandler(handler);

        Person.XML_WRITER.toXml(persons());
        assertEquals(PersonResources.COUNT, handler.writeCount);
    }
}
