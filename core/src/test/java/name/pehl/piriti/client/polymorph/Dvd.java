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
public class Dvd extends Medium
{
    public interface DvdReader extends JsonReader<Dvd> {}
    public static final DvdReader READER = GWT.create(DvdReader.class);

    public interface DvdWriter extends JsonWriter<Dvd> {}
    public static final DvdWriter WRITER = GWT.create(DvdWriter.class);

    int duration;
}
