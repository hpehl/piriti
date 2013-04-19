package name.pehl.piriti.rebind.json;

import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonReaderWriter;
import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.rebind.AbstractReaderWriterGenerator;

public class JsonReaderWriterGenerator extends AbstractReaderWriterGenerator
{
    @Override
    protected String getInterfaceClassName()
    {
        return JsonReaderWriter.class.getName();
    }

    @Override
    protected Class<?> getReaderClass()
    {
        return JsonReader.class;
    }

    @Override
    protected Class<?> getWriterClass()
    {
        return JsonWriter.class;
    }

    @Override
    protected String getFormat()
    {
        return "Json";
    }
}
