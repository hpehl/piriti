package name.pehl.piriti.gxt.client.gwttest.inheritance;

import name.pehl.piriti.client.gwttest.inheritance.AnimalResources;

/**
 * @author $Author$
 * @version $Revision$
 */
public class JsonAnimalTest extends AbstractAnimalTest
{
    // ------------------------------------------------------------- read tests

    public void testReadBird()
    {
        String json = AnimalResources.INSTANCE.birdJson().getText();
        Bird kea = Bird.JSON_READER.read(json);
        assertBird(kea);
    }


    public void testReadInsect()
    {
        String json = AnimalResources.INSTANCE.insectJson().getText();
        Insect fruitFly = Insect.JSON_READER.read(json);
        assertInsect(fruitFly);
    }


    public void testReadCat()
    {
        String json = AnimalResources.INSTANCE.catJson().getText();
        Cat snowball = Cat.JSON_READER.read(json);
        assertCat(snowball);
    }


    public void testReadDog()
    {
        String json = AnimalResources.INSTANCE.dogJson().getText();
        Dog rantanplan = Dog.JSON_READER.read(json);
        assertDog(rantanplan);
    }

    // ------------------------------------------------------------ write tests

    // NYI
}
