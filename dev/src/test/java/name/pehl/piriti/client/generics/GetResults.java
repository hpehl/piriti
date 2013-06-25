package name.pehl.piriti.client.generics;

import java.util.ArrayList;
import java.util.List;

public class GetResults<T>
{
    List<T> results;
    SomeGeneric<T> generic;
    List<String> someList;

    GetResults() {
    }

    public GetResults(List<T> results) {
        this.results = results;
        this.generic = new SomeGeneric<T>(results.get(0));
        someList = new ArrayList<String>();
    }

    public List<T> getResults() {
        return results;
    }

    public List<String> getSomeList() {
        return someList;
    }

    public SomeGeneric<T> getGeneric() {
        return generic;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public void setGeneric(SomeGeneric<T> generic) {
        this.generic = generic;
    }

    public void setSomeList(List<String> someList) {
        this.someList = someList;
    }
}
