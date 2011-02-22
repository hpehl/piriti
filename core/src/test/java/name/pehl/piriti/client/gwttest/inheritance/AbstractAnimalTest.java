package name.pehl.piriti.client.gwttest.inheritance;

import name.pehl.piriti.client.gwttest.AbstractPiritiTest;

/**
 * @author $Author$
 * @version $Date$ $Revision: 295
 *          $
 */
public abstract class AbstractAnimalTest extends AbstractPiritiTest
{
    static final String NAME_PREFIX = "My name is ";


    protected void assertBird(Bird bird)
    {
        assertNotNull(bird);
        assertName("Kea", bird.name);
        assertEquals(2, bird.legs);
        assertEquals(.66, bird.intelligence, .01);
        assertEquals(100, bird.wingspan);
    }


    protected void assertInsect(Insect insect)
    {
        assertNotNull(insect);
        assertName("Fruit Fly", insect.name);
        assertEquals(6, insect.legs);
        assertEquals(.001, insect.intelligence, .001);
        assertTrue(insect.flies);
    }


    protected void assertCat(Cat cat)
    {
        assertNotNull(cat);
        assertName("Snowball", cat.name);
        assertEquals(4, cat.legs);
        assertEquals(.4, cat.intelligence, .01);
        assertEquals(Gender.FEMALE, cat.gender);
        assertEquals("dark grey", cat.color);
    }


    protected void assertDog(Dog dog)
    {
        assertNotNull(dog);
        assertName("Rantanplan", dog.name);
        assertEquals(4, dog.legs);
        assertEquals(.15, dog.intelligence, .01);
        assertEquals(Gender.MALE, dog.gender);
        assertEquals(Size.NORMAL, dog.size);
    }


    protected void assertName(String expected, String actual)
    {
        assertEquals(NAME_PREFIX + expected, actual);
    }
}
