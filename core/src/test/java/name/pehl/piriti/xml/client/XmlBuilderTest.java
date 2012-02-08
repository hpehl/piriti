package name.pehl.piriti.xml.client;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

public class XmlBuilderTest
{
    // ------------------------------------------------------------------ setup

    private final static String BOOK_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><book><isbn>978-0345417954</isbn><pages>432</pages><title>The Hotel New Hampshire</title><author><firstname>John</firstname><surname>Irving</surname><reviews><review>A hectic gaudy saga with the verve of a Marx Brothers movie.</review><review>Rejoice! John Irving has written another book according to your world. You must read this book.</review><review>Spellbinding, intensely human, a high-wire act of dazzling virtuosity.</review></reviews></author></book>";


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


    // --------------------------------------------- simple element / attribute

    @Test
    public void simpleElement()
    {
        XmlBuilder cut = new XmlBuilder("book").append("@isbn", "978-0345417954");
        assertEquals("<book isbn=\"978-0345417954\"/>", cut.toString());
    }


    @Test
    public void simpleAttribute()
    {
        XmlBuilder cut = new XmlBuilder("book").append("isbn", "978-0345417954");
        assertEquals("<book><isbn>978-0345417954</isbn></book>", cut.toString());
    }


    // ------------------------------------------- nested elements / attributes

    @Test
    public void nestedElements()
    {
        XmlBuilder cut = new XmlBuilder("book").append("@isbn", "978-0345417954");
        assertEquals("<book isbn=\"978-0345417954\"/>", cut.toString());
    }


    @Test
    public void nestedEmptyElements()
    {
        XmlBuilder cut = new XmlBuilder("book").append("isbn", "978-0345417954");
        assertEquals("<book><isbn>978-0345417954</isbn></book>", cut.toString());
    }


    // ------------------------------------------------------------------- book

    @Test
    @Ignore
    public void book()
    {
        //@formatter:off
        XmlBuilder book = new XmlBuilder("book")
            .append("@isbn", "978-0345417954")
            .append("pages", "432")
            .append("title", "The Hotel New Hampshire");
            XmlBuilder author = new XmlBuilder("author")
                .append("firstname", "John")
                .append("surname", "Irving");
                XmlBuilder reviews = new XmlBuilder("reviews")
                    .append("review", "A hectic gaudy saga with the verve of a Marx Brothers movie.")
                    .append("review", "Rejoice! John Irving has written another book according to your world. You must read this book.")
                    .append("review", "Spellbinding, intensely human, a high-wire act of dazzling virtuosity.");
        //@formatter:on
        assertEquals(BOOK_XML, book.toString());
    }
}
