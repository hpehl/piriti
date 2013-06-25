package name.pehl.piriti.client.generics;

import java.util.List;

public class GetResults<T>
{
    List<T> results;
    SomeGeneric<T> generic;

    GetResults()
    {
    }

    public GetResults(List<T> results)
    {
        this.results = results;
        this.generic = new SomeGeneric<T>(results.get(0));
    }

    public List<T> getResults()
    {
        return results;
    }
}
