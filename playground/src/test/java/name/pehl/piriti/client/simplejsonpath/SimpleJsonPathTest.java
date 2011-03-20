package name.pehl.piriti.client.simplejsonpath;

import name.pehl.piriti.client.AbstractPlaygroundTest;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 1305 $
 */
public class SimpleJsonPathTest extends AbstractPlaygroundTest
{
    public void testReadPerson()
    {
        String json = SimpleJsonPathResources.INSTANCE.personJson().getText();
        Person person = Person.READER.read(json);

        assertEquals("Hans Dampf", person.name);
        assertEquals("Hirbeldirbel 1", person.street);
        assertEquals("Equalizerhofen", person.city);
    }
}
