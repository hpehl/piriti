package name.pehl.piriti.client.generics;

import java.util.List;

import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

public class GetResults<T>
{
    interface GetResultsXmlWriter<T> extends XmlWriter<GetResults<T>>
    {
    }

    interface GetResultsXmlReader<T> extends XmlReader<GetResults<T>>
    {
    }

    interface GetResultsJsonWriter<T> extends JsonWriter<GetResults<T>>
    {
    }

    interface GetResultsJsonReader<T> extends JsonReader<GetResults<T>>
    {
    }
    
    List<T> results;

    GetResults()
    {
    }

    public GetResults(List<T> results)
    {
        this.results = results;
    }

    public List<T> getResults()
    {
        return results;
    }
}
