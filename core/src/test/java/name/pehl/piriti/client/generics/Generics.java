package name.pehl.piriti.client.generics;

import java.util.List;

import name.pehl.piriti.json.client.JsonWriter;

import com.google.gwt.core.client.GWT;

/**
 * Tests for group discussion
 * https://groups.google.com/d/topic/piriti/g1PdfE6AiCc/discussion
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class Generics/* <T> */
{
    // ------------------------------------------------------- without generics

    // @SuppressWarnings("rawtypes")
    public interface GenericsWriter extends JsonWriter<Generics>
    {
    }

    public static final GenericsWriter GW = GWT.create(GenericsWriter.class);

    // --------------------------------------------------------------- any type

    // public interface AnyGenericsWriter extends JsonWriter<Generics<?>>
    // {
    // }
    //
    // public static final AnyGenericsWriter AGW =
    // GWT.create(AnyGenericsWriter.class);

    // ------------------------------------------------------ with type integer

    // public interface IntegerGenericsWriter extends
    // JsonWriter<Generics<Integer>>
    // {
    // }
    //
    // public static final IntegerGenericsWriter IGW =
    // GWT.create(IntegerGenericsWriter.class);

    String s;
    Object o;
    // T t;
    List<String> slist;
    List<Object> olist;
    // List<T> tlist;
}
