package name.pehl.piriti.client.types;

import name.pehl.piriti.client.AbstractPiritiTest;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 1454 $
 */
public class XmlArraysTest extends AbstractPiritiTest
{
    // ------------------------------------------------------------- read tests

    public void testRead()
    {
        String xml = ArraysResources.INSTANCE.arraysXml().getText();
        Arrays arrays = Arrays.XML_READER.read(xml);
        assertNotNull(arrays);
        assertArrayEquals(new String[] {"a", "b", "c"}, arrays.strings);
        assertArrayEquals(new String[] {"a", "null", "b", "c"}, arrays.mixed);
    }


    // ------------------------------------------------------------ write tests

    public void testWrite()
    {
        Arrays arrays = new Arrays();
        arrays.strings = new String[] {"a", "b", "c"};
        arrays.mixed = new String[] {null, "a", "", "null", null, "", null, "b", "c", null};
        String xml = Arrays.XML_WRITER.toXml(arrays);
        assertEquals(ArraysResources.INSTANCE.arraysCompactXml().getText(), xml);
    }
}
