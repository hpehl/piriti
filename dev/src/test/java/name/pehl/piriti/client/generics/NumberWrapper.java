package name.pehl.piriti.client.generics;

public class NumberWrapper<T>
{
    T number;

    NumberWrapper()
    {
    }

    public NumberWrapper(T number)
    {
        this.number = number;
    }

    public T getNumber()
    {
        return number;
    }

    public void setNumber(T number)
    {
        this.number = number;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NumberWrapper that = (NumberWrapper) o;

        return !(number != null ? !number.equals(that.number) : that.number != null);
    }

    @Override
    public int hashCode()
    {
        return number != null ? number.hashCode() : 0;
    }
}
