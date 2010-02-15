package name.pehl.gwt.piriti.client.xml.book;

import name.pehl.gwt.piriti.client.xml.XmlField;
import name.pehl.gwt.piriti.client.xml.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public class Author
{
    interface AuthorReader extends XmlReader<Author>
    {
    }

    public static final AuthorReader XML = GWT.create(AuthorReader.class);

    @XmlField
    String firstname;
    @XmlField
    String surname;
}
