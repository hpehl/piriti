package name.pehl.piriti.client.generics;

import java.util.List;

public class GetResults<T>
{
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
