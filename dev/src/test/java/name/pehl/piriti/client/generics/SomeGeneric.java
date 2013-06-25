package name.pehl.piriti.client.generics;

public class SomeGeneric<T>
{
    private T value;

    SomeGeneric()
    {
    }

    public SomeGeneric(T value)
    {
        this.value = value;
    }

    public T getValue()
    {
        return value;
    }

    public void setValue(T value)
    {
        this.value = value;
    }
}
