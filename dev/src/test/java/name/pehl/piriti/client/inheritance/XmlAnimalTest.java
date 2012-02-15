package name.pehl.piriti.client.inheritance;

import name.pehl.totoe.xml.client.Document;
import name.pehl.totoe.xml.client.XmlParser;

/**
 * @author $Author$
 * @version $Revision$
 */
public class XmlAnimalTest extends AbstractAnimalTest
{
    // ------------------------------------------------------------- read tests

    public void testReadBird()
    {
        String xml = AnimalResources.INSTANCE.birdXml().getText();
        Bird kea = Bird.XML_READER.read(xml);
        assertBird(kea);
    }


    public void testReadInsect()
    {
        String xml = AnimalResources.INSTANCE.insectXml().getText();
        Insect fruitFly = Insect.XML_READER.read(xml);
        assertInsect(fruitFly);
    }


    public void testReadCat()
    {
        String xml = AnimalResources.INSTANCE.catXml().getText();
        Cat snowball = Cat.XML_READER.read(xml);
        assertCat(snowball);
    }


    public void testReadDog()
    {
        String xml = AnimalResources.INSTANCE.dogXml().getText();
        Dog rantanplan = Dog.XML_READER.read(xml);
        assertDog(rantanplan);
    }


    // ------------------------------------------------------------ write tests

    public void testWriteBird()
    {
        // Roundtrip
        String xmlIn = AnimalResources.INSTANCE.birdXml().getText();
        Bird bird = Bird.XML_READER.read(xmlIn);
        String xmlOut = Bird.XML_WRITER.toXml(bird);
        Document document = new XmlParser().parse(xmlOut);
        assertNotNull(document);
        // TODO More asserts
    }


    public void testWriteInsect()
    {
        // Roundtrip
        String xmlIn = AnimalResources.INSTANCE.insectXml().getText();
        Insect insect = Insect.XML_READER.read(xmlIn);
        String xmlOut = Insect.XML_WRITER.toXml(insect);
        Document document = new XmlParser().parse(xmlOut);
        assertNotNull(document);
        // TODO More asserts
    }


    public void testWriteCat()
    {
        // Roundtrip
        String xmlIn = AnimalResources.INSTANCE.catXml().getText();
        Cat cat = Cat.XML_READER.read(xmlIn);
        String xmlOut = Cat.XML_WRITER.toXml(cat);
        Document document = new XmlParser().parse(xmlOut);
        assertNotNull(document);
        // TODO More asserts
    }


    public void testWriteDog()
    {
        // Roundtrip
        String xmlIn = AnimalResources.INSTANCE.dogXml().getText();
        Dog dog = Dog.XML_READER.read(xmlIn);
        String xmlOut = Dog.XML_WRITER.toXml(dog);
        Document document = new XmlParser().parse(xmlOut);
        assertNotNull(document);
        // TODO More asserts
    }
}
