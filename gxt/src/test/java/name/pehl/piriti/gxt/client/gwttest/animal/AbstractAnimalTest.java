package name.pehl.piriti.gxt.client.gwttest.animal;

import name.pehl.piriti.client.gwttest.animal.Gender;
import name.pehl.piriti.client.gwttest.animal.Size;
import name.pehl.piriti.gxt.client.gwttest.AbstractPiritiGxtTest;

/**
 * @author $Author$
 * @version $Date$ $Revision: 295
 *          $
 */
public abstract class AbstractAnimalTest extends AbstractPiritiGxtTest
{
    protected void assertBird(Bird bird)
    {
        assertNotNull(bird);
        assertEquals("Kea", bird.get("name"));
        assertEquals(2, bird.get("legs"));
        assertEquals(.66, (Double) bird.get("intelligence"), .01);
        assertEquals(100, bird.get("wingspan"));
    }


    protected void assertInsect(Insect insect)
    {
        assertNotNull(insect);
        assertEquals("Fruit Fly", insect.get("name"));
        assertEquals(6, insect.get("legs"));
        assertEquals(.001, (Double) insect.get("intelligence"), .001);
        assertTrue((Boolean) insect.get("flies"));
    }


    protected void assertCat(Cat cat)
    {
        assertNotNull(cat);
        assertEquals("Snowball", cat.get("name"));
        assertEquals(4, cat.get("legs"));
        assertEquals(.4, (Double) cat.get("intelligence"), .01);
        assertEquals(Gender.FEMALE, cat.get("gender"));
        assertEquals("dark grey", cat.get("color"));
    }


    protected void assertDog(Dog dog)
    {
        assertNotNull(dog);
        assertEquals("Rantanplan", dog.get("name"));
        assertEquals(4, dog.get("legs"));
        assertEquals(.15, (Double) dog.get("intelligence"), .01);
        assertEquals(Gender.MALE, dog.get("gender"));
        assertEquals(Size.NORMAL, dog.get("size"));
    }
}
