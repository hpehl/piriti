package name.pehl.piriti.client.generics;

import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

public class NumberWrapper<T>
{
    interface NumberWrapperXmlWriter<T> extends XmlWriter<NumberWrapper<T>>
    {
    }

    interface NumberWrapperXmlReader<T> extends XmlReader<NumberWrapper<T>>
    {
    }

    interface NumberWrapperJsonWriter<T> extends JsonWriter<NumberWrapper<T>>
    {
    }

    interface NumberWrapperJsonReader<T> extends JsonReader<NumberWrapper<T>>
    {
    }

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
