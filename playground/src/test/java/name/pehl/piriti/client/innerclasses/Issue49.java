package name.pehl.piriti.client.innerclasses;

import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;

import com.google.gwt.core.client.GWT;

//@formatter:off
public class Issue49
{
    public interface Issue49Reader extends JsonReader<Issue49> {}
    public static final Issue49Reader READER = GWT.create(Issue49Reader.class);

    public interface Issue49Writer extends JsonWriter<Issue49> {}
    public static final Issue49Writer WRITER = GWT.create(Issue49Writer.class);

    String name = "Have a";
    State intern = new State("nice", "day");
    
    public class State
    {
        String x;
        String y;

        public State(String xx, String yy)
        {
            this.x = xx;
            this.y = yy;
        }
    }
}
