package name.pehl.piriti.gxt.client.gwttest.inheritance;

import name.pehl.piriti.client.gwttest.inheritance.AnimalResources;

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

    // NYI
}
