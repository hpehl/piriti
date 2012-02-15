package name.pehl.piriti.xml.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class XPathTest
{
    // ----------------------------------------------------------- invalid path

    @Test
    public void nullPath()
    {
        XPath cut = new XPath(null);
        assertTrue(cut.isEmpty());
        assertFalse(cut.iterator().hasNext());
        assertEquals(0, cut.size());
        assertNull(cut.first());
        assertNull(cut.allButLast());
        assertNull(cut.last());
    }


    @Test
    public void emptyPath()
    {
        XPath cut = new XPath("");
        assertTrue(cut.isEmpty());
        assertFalse(cut.iterator().hasNext());
        assertEquals(0, cut.size());
        assertNull(cut.first());
        assertNull(cut.allButLast());
        assertNull(cut.last());
    }


    @Test
    public void blankPath()
    {
        XPath cut = new XPath("    ");
        assertTrue(cut.isEmpty());
        assertFalse(cut.iterator().hasNext());
        assertEquals(0, cut.size());
        assertNull(cut.first());
        assertNull(cut.allButLast());
        assertNull(cut.last());
    }


    // ------------------------------------------------------------ simple path

    @Test
    public void simplePath()
    {
        XPath cut = new XPath("foo");
        assertFalse(cut.isEmpty());
        assertTrue(cut.iterator().hasNext());
        assertEquals(1, cut.size());
        assertEquals("foo", cut.first());
        assertEquals("foo", cut.allButLast());
        assertEquals("foo", cut.last());

        cut = new XPath("//foo/");
        assertFalse(cut.isEmpty());
        assertTrue(cut.iterator().hasNext());
        assertEquals(1, cut.size());
        assertEquals("foo", cut.first());
        assertEquals("foo", cut.allButLast());
        assertEquals("foo", cut.last());
    }


    // ------------------------------------------------------------ nested path

    @Test
    public void nestedPath()
    {
        XPath cut = new XPath("a/b/c");
        assertFalse(cut.isEmpty());
        assertTrue(cut.iterator().hasNext());
        assertEquals(3, cut.size());
        assertEquals("a", cut.first());
        assertEquals("a/b", cut.allButLast());
        assertEquals("c", cut.last());

        cut = new XPath("//a/b////c/");
        assertFalse(cut.isEmpty());
        assertTrue(cut.iterator().hasNext());
        assertEquals(3, cut.size());
        assertEquals("a", cut.first());
        assertEquals("a/b", cut.allButLast());
        assertEquals("c", cut.last());
    }
}
