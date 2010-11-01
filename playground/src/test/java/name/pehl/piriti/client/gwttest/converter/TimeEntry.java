package name.pehl.piriti.client.gwttest.converter;

import java.util.Date;

import name.pehl.piriti.client.json.Json;
import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.client.json.JsonWriter;

import com.google.gwt.core.client.GWT;

// @formatter:off
public class TimeEntry
{
    public interface TimeEntryReader extends JsonReader<TimeEntry> {}
    public static final TimeEntryReader READER = GWT.create(TimeEntryReader.class);

    public interface TimeEntryWriter extends JsonWriter<TimeEntry> {}
    public static final TimeEntryWriter WRITER = GWT.create(TimeEntryWriter.class);

    
    @Json(order = 1, format = "dd.MM.yyyy") Date date;
    @Json(order = 2, converter = DurationConverter.class) double duration;
}
