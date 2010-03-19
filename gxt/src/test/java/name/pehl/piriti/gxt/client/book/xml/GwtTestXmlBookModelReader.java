package name.pehl.piriti.gxt.client.book.xml;

import name.pehl.piriti.client.book.xml.XmlBookFactory;
import name.pehl.piriti.gxt.client.book.BookModel;
import name.pehl.piriti.gxt.client.book.BookModelTestCase;

import com.google.gwt.xml.client.Document;

/**
 * @author $Author$
 * @version $Revision$
 */
public class GwtTestXmlBookModelReader extends BookModelTestCase
{
    public void testRead()
    {
        Document document = XmlBookFactory.createDocument();
        BookModel book = BookModel.XML.read(document);
        assertBookModel(book);
    }
}
