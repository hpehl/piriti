package name.pehl.piriti.client.types;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 1454 $
 */
public class XmlDatesTest extends AbstractDatesTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String json = DatesResources.INSTANCE.datesJson().getText();
        Dates dates = Dates.JSON_READER.read(json);
        assertDates(dates);
    }


    // ------------------------------------------------------------ write tests

    public void testWrite()
    {
        Dates empty = new Dates();
        String json = Dates.JSON_WRITER.toJson(empty);
        assertEquals(DatesResources.INSTANCE.datesCompactJson().getText(), json);
    }
}
