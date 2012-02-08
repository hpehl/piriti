package name.pehl.piriti.xml.client;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
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
        XmlBuilder cut = new XmlBuilder("a").append("@b", "0815");
        assertEquals("<a b=\"0815\"/>", cut.toString());
    }


    @Test
    public void simpleElement()
    {
        XmlBuilder cut = new XmlBuilder("a").append("b", "0815");
        assertEquals("<a><b>0815</b></a>", cut.toString());
    }


    // ---------------------------------------------------------------- mixed

    @Test
    @Ignore
    public void mixed()
    {
        //@formatter:off
        XmlBuilder book = new XmlBuilder("mixed")
            .append("@att1", "val1")
            .append("a/b/c", "123")
            .append("x/y/z/@att2", "val2")
            .append("empty")
            .append(new XmlBuilder("author")
                .append("firstname", "John")
                .append("surname", "Irving")
                .append(new XmlBuilder("reviews")
                    .append("review", "A hectic gaudy saga with the verve of a Marx Brothers movie.")
                    .append("review", "Rejoice! John Irving has written another book according to your world. You must read this book.")
                    .append("review", "Spellbinding, intensely human, a high-wire act of dazzling virtuosity.")));
        //@formatter:on
        assertEquals("", book.toString());
    }
}
