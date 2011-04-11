package name.pehl.piriti.client.inheritance;

import name.pehl.totoe.json.client.JsonParser;

import com.google.gwt.json.client.JSONObject;

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

    public void testWriteBird()
    {
        // Roundtrip
        String jsonIn = AnimalResources.INSTANCE.birdJson().getText();
        Bird bird = Bird.JSON_READER.read(jsonIn);
        String jsonOut = Bird.JSON_WRITER.toJson(bird);

        // Test order
        assertEquals(AnimalResources.INSTANCE.birdOrderedJson().getText(), jsonOut);

        JSONObject jsonObject = new JsonParser().parse(jsonOut);
        assertNotNull(jsonObject);
        // TODO More asserts
    }


    public void testWriteInsect()
    {
        // Roundtrip
        String jsonIn = AnimalResources.INSTANCE.insectJson().getText();
        Insect insect = Insect.JSON_READER.read(jsonIn);
        String jsonOut = Insect.JSON_WRITER.toJson(insect);
        JSONObject jsonObject = new JsonParser().parse(jsonOut);
        assertNotNull(jsonObject);
        // TODO More asserts
    }


    public void testWriteCat()
    {
        // Roundtrip
        String jsonIn = AnimalResources.INSTANCE.catJson().getText();
        Cat cat = Cat.JSON_READER.read(jsonIn);
        String jsonOut = Cat.JSON_WRITER.toJson(cat);
        JSONObject jsonObject = new JsonParser().parse(jsonOut);
        assertNotNull(jsonObject);
        // TODO More asserts
    }


    public void testWriteDog()
    {
        // Roundtrip
        // String jsonIn = AnimalResources.INSTANCE.dogJson().getText();
        // Dog dog = Dog.JSON_READER.read(jsonIn);
        // String jsonOut = Dog.JSON_WRITER.toJson(dog);
        // JSONObject jsonObject = new JsonParser().parse(jsonOut);
        // assertNotNull(jsonObject);
        // TODO More asserts
    }
}
