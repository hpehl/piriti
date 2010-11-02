package name.pehl.piriti.client.gwttest.arcbees;

import java.util.List;

import name.pehl.piriti.client.gwttest.AbstractPlaygroundTest;
import name.pehl.totoe.xml.client.Document;
import name.pehl.totoe.xml.client.XmlParser;

/**
 * @author $Author$
 * @version $Revision$
 */
public class BlogItemTest extends AbstractPlaygroundTest
{
    static final int BLOG_ITEMS = 10;
    static final String NAMESPACES = "xmlns:content=\"http://purl.org/rss/1.0/modules/content/\" xmlns:wfw=\"http://wellformedweb.org/CommentAPI/\" xmlns:dc=\"http://purl.org/dc/elements/1.1/\" xmlns:atom=\"http://www.w3.org/2005/Atom\" xmlns:sy=\"http://purl.org/rss/1.0/modules/syndication/\" xmlns:slash=\"http://purl.org/rss/1.0/modules/slash/\" xmlns:georss=\"http://www.georss.org/georss\" xmlns:geo=\"http://www.w3.org/2003/01/geo/wgs84_pos#\" xmlns:media=\"http://search.yahoo.com/mrss/\"";

    static final int INDEX_FIXTURE = 1;
    static final String TITLE_FIXTURE = "We\u2019re going to use Maven";
    static final String LINK_FIXTURE = "http://arcbees.wordpress.com/2010/09/28/were-going-to-use-maven/";
    static final String CREATOR_FIXTURE = "Christian Goudreau";
    static final String[] CATEGORIES_FIXTURE = {"ArcBees website", "Development"};


    public void testReadBlogItemsFromDocument()
    {
        String xml = ArcBeesResources.INSTANCE.feedXml().getText();
        Document doc = new XmlParser().parse(xml, NAMESPACES);
        List<BlogItem> blogItems = BlogItem.XML.readList(doc, "/rss/channel/item");
        assertEquals(BLOG_ITEMS, blogItems.size());

        BlogItem blogItem = blogItems.get(INDEX_FIXTURE);
        assertEquals(TITLE_FIXTURE, blogItem.getTitle());
        assertEquals(LINK_FIXTURE, blogItem.getLink());
        assertEquals(CREATOR_FIXTURE, blogItem.getCreator());
        assertEquals(CATEGORIES_FIXTURE.length, blogItem.getCategories().size());
        for (int i = 0; i < CATEGORIES_FIXTURE.length; i++)
        {
            assertEquals(CATEGORIES_FIXTURE[i], blogItem.getCategories().get(i));
        }
    }
}
