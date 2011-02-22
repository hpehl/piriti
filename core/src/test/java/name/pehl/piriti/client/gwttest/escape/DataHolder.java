package name.pehl.piriti.client.gwttest.escape;

import name.pehl.piriti.client.json.Json;
import name.pehl.piriti.client.json.JsonWriter;

import com.google.gwt.core.client.GWT;

public class DataHolder
{
    // @formatter:off
    public interface Writer extends JsonWriter<DataHolder> {}
    public static final Writer JSON_WRITER = GWT.create(Writer.class);

    @Json String data;
    @Json(converter = DataConverter.class) String converterData;
    // @formatter:on
}
