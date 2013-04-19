package name.pehl.piriti.json.client;

public interface JsonReaderWriter<T>
{
    JsonReader<T> getJsonReader();

    JsonWriter<T> getJsonWriter();
}
