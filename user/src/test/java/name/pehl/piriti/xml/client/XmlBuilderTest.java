package name.pehl.piriti.xml.client;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

public class XmlBuilderTest
{
    // ----------------------------------------------------------- invalid root

    @Test
    public void nullElement()
    {
        XmlBuilder cut = new XmlBuilder(null);
        assertEquals("", cut.toString());
    }


    @Test
    public void emptyElement()
    {
        XmlBuilder cut = new XmlBuilder("");
        assertEquals("", cut.toString());
    }


    @Test
    public void blankElement()
    {
        XmlBuilder cut = new XmlBuilder("    ");
        assertEquals("", cut.toString());
    }


    // ---------------------------------------------------- attribute / element

    @Test
    public void simpleAttribute()
    {
        XmlBuilder cut = new XmlBuilder("a").append("@b", "42");
        assertEquals("<a b=\"42\"/>", cut.toString());
    }


    @Test
    public void nestedAttribute()
    {
        XmlBuilder cut = new XmlBuilder("a").append("b/c/d/@e", "42");
        assertEquals("<a><b><c><d e=\"42\"/></c></b></a>", cut.toString());

        cut = new XmlBuilder("a").append("//b/c/d/@e", "42");
        assertEquals("<a><b><c><d e=\"42\"/></c></b></a>", cut.toString());

        cut = new XmlBuilder("a/b").append("c/d/@e", "42");
        assertEquals("<a><b><c><d e=\"42\"/></c></b></a>", cut.toString());

        cut = new XmlBuilder("//a/b/").append("//c/d/@e", "42");
        assertEquals("<a><b><c><d e=\"42\"/></c></b></a>", cut.toString());
    }


    @Test
    public void simpleElement()
    {
        XmlBuilder cut = new XmlBuilder("a").append("b", "42");
        assertEquals("<a><b>42</b></a>", cut.toString());
    }


    @Test
    public void nestedElements()
    {
        XmlBuilder cut = new XmlBuilder("a").append("b/c/d/e", "42");
        assertEquals("<a><b><c><d><e>42</e></d></c></b></a>", cut.toString());

        cut = new XmlBuilder("a").append("//b/c/d/e/", "42");
        assertEquals("<a><b><c><d><e>42</e></d></c></b></a>", cut.toString());

        cut = new XmlBuilder("a/b/c").append("d/e", "42");
        assertEquals("<a><b><c><d><e>42</e></d></c></b></a>", cut.toString());

        cut = new XmlBuilder("//a/b/c/").append("//d/e/", "42");
        assertEquals("<a><b><c><d><e>42</e></d></c></b></a>", cut.toString());
    }


    // ---------------------------------------------------------------- mixed

    @Test
    public void mixed() throws IOException
    {
        //@formatter:off
        XmlBuilder mixed = new XmlBuilder("mixed")
            .append("@att1", "val1")
            .append("a/b/c", "123")
            .append("x/y/z/@att2", "val2")
            .append("empty")
            .append("a/nother/empty/element")
            .append(new XmlBuilder("nestedElement")
                .append("@att1", "val1")
                .append("a/b/c", "123")
                .append("x/y/z/@att2", "val2")
                .append("empty")
                .append("a/nother/empty/element")
                .append("deeply/nested", new XmlBuilder("element")
                    .append("@att1", "val1")
                    .append("a/b/c", "123")
                    .append("x/y/z/@att2", "val2")
                    .append("empty")
                    .append("a/nother/empty/element")));
        //@formatter:on
        String xml = stripSpacesAndNewLnes("mixed.xml");
        assertEquals(xml, mixed.toString());
    }


    private String stripSpacesAndNewLnes(String file) throws IOException
    {
        StringBuilder xml = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("mixed.xml")));

        String line = null;
        String newline = System.getProperty("line.separator");
        while ((line = in.readLine()) != null)
        {
            xml.append(line.trim().replaceAll(newline, ""));
        }
        in.close();
        return xml.toString();
    }
}
