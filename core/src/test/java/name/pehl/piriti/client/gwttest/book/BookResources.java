package name.pehl.piriti.client.gwttest.book;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */

public interface BookResources extends ClientBundle
{
    // -------------------------------------------------------------- constants

    int RELATED_SIZE = 3;
    String ISBN = "978-0345417954";
    int PAGES = 432;
    String TITLE = "The Hotel New Hampshire";
    String EXTRA_INFO = "dki82";
    String AUTHOR_SURNAME = "Irving";
    String AUTHOR_FIRSTNAME = "John";
    String[] REVIEWS = new String[] {"A hectic gaudy saga with the verve of a Marx Brothers movie.",
            "Rejoice! John Irving has written another book according to your world. You must read this book.",
            "Spellbinding, intensely human, a high-wire act of dazzling virtuosity.",};

    // ------------------------------------------------------- deferred binding

    BookResources INSTANCE = GWT.create(BookResources.class);


    @Source("book.json")
    public TextResource bookJson();


    @Source("book.xml")
    public TextResource bookXml();
}
