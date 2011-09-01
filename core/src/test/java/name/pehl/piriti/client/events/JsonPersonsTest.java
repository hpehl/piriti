package name.pehl.piriti.client.events;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 131 $
 */
public class JsonPersonsTest extends AbstractPersonsTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        CountHandler handler = new CountHandler();
        Person.JSON_READER.addModelReadHandler(handler);

        String json = PersonResources.INSTANCE.personsJson().getText();
        Person.JSON_READER.readList(json);
        assertEquals(PersonResources.COUNT, handler.readCount);
    }


    // ------------------------------------------------------------ write tests

    public void testWrite()
    {
        CountHandler handler = new CountHandler();
        Person.JSON_WRITER.addModelWriteHandler(handler);

        Person.JSON_WRITER.toJson(persons(), "persons");
        assertEquals(PersonResources.COUNT, handler.writeCount);
    }
}
