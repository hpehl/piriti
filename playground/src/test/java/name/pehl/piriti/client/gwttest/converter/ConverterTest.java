package name.pehl.piriti.client.gwttest.converter;

import java.util.Date;

import name.pehl.piriti.client.gwttest.AbstractPlaygroundTest;

public class ConverterTest extends AbstractPlaygroundTest
{
    @SuppressWarnings("deprecation")
    public void testDurationConverter()
    {
        TimeEntry entry = new TimeEntry();
        entry.date = new Date(73, 8, 2);
        entry.duration = 23.12;
        String json = TimeEntry.WRITER.toJson(entry);
        assertEquals("{\"date\":\"02.09.1973\",\"duration\":\"23.12h\"}", json);
    }
}
