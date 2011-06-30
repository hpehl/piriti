package name.pehl.piriti.client.generics;

import java.util.ArrayList;
import java.util.Date;

import name.pehl.piriti.client.AbstractPiritiTest;

/**
 * Tests for group discussion
 * https://groups.google.com/d/topic/piriti/g1PdfE6AiCc/discussion
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class JsonGenericsTest extends AbstractPiritiTest
{
    // @SuppressWarnings({"rawtypes", "unchecked"})
    public void testWriteGenerics()
    {
        Generics generics = new Generics();
        generics.s = "s";
        generics.o = 42;
        // generics.t = "t";

        generics.slist = new ArrayList<String>();
        generics.slist.add("a");
        generics.slist.add("b");
        generics.slist.add("c");

        generics.olist = new ArrayList<Object>();
        generics.olist.add("x");
        generics.olist.add(new Date());
        generics.olist.add(42);

        // generics.tlist = new ArrayList<Object>();
        // generics.tlist.add("x");
        // generics.tlist.add(new Date());
        // generics.tlist.add(42);

        String json = Generics.GW.toJson(generics);
        assertNotNull(json);
    }
}
