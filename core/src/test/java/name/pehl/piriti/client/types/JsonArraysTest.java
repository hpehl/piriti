package name.pehl.piriti.client.types;

import name.pehl.piriti.client.AbstractPiritiTest;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 1454 $
 */
public class JsonArraysTest extends AbstractPiritiTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String json = ArraysResources.INSTANCE.arraysJson().getText();
        Arrays arrays = Arrays.JSON_READER.read(json);
        assertNotNull(arrays);
        assertArrayEquals(new String[] {"a", "b", "c"}, arrays.strings);
        assertArrayEquals(new String[] {null, "a", "", "null", null, "", null, "b", "c", null}, arrays.mixed);
    }


    // ------------------------------------------------------------ write tests

    public void testWrite()
    {
        Arrays arrays = new Arrays();
        arrays.strings = new String[] {"a", "b", "c"};
        arrays.mixed = new String[] {null, "a", "", "null", null, "", null, "b", "c", null};
        String json = Arrays.JSON_WRITER.toJson(arrays);
        assertEquals(ArraysResources.INSTANCE.arraysCompactJson().getText(), json);
    }


    // --------------------------------------------------------- helper methods

    private void assertArrayEquals(String[] expected, String[] actual)
    {
        assertNotNull(actual);
        assertNotNull(expected);
        assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++)
        {
            if (expected[i] == null)
            {
                assertNull(actual[i]);
            }
            else
            {
                assertEquals(expected[i], actual[i]);
            }
        }
    }
}
