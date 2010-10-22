package name.pehl.piriti.client.gwttest.specialjsonkey;

import name.pehl.piriti.client.json.Json;
import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.client.json.JsonWriter;

import com.google.gwt.core.client.GWT;

/**
 * Test for <a href=
 * "http://groups.google.com/group/piriti/browse_thread/thread/3ee6195d0f3b766c"
 * >POJO to JSON writing</a>.
 * 
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
//@formatter:off
public class Request
{
    public interface RequestReader extends JsonReader<Request> {}
    public static final RequestReader READER = GWT.create(RequestReader.class);

    public interface RequestWriter extends JsonWriter<Request> {}
    public static final RequestWriter WRITER = GWT.create(RequestWriter.class);

    @Json("@requestId") protected String requestId;
}
