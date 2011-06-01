package name.pehl.piriti.client.polymorph;

import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2011-02-22 22:55:05 +0100 (Di, 22. Feb 2011) $ $Revision:
 *          1454 $
 */
//@formatter:off
public class Book extends Medium
{
    public interface BookReader extends JsonReader<Book> {}
    public static final BookReader READER = GWT.create(BookReader.class);

    public interface BookWriter extends JsonWriter<Book> {}
    public static final BookWriter WRITER = GWT.create(BookWriter.class);

    int pages;
}
