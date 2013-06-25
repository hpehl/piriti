package name.pehl.piriti.client.generics;

import java.util.ArrayList;
import java.util.List;

import name.pehl.piriti.client.AbstractPiritiTest;
import name.pehl.piriti.json.client.JsonReaderWriter;

import com.google.gwt.core.client.GWT;

public class JsonGenericsTest extends AbstractPiritiTest
{
    interface GetResultsIntNumberWrapperJsonReaderWriter extends JsonReaderWriter<GetResults<NumberWrapper<Integer>>>
    {
    }

    interface GetResultsIntegerJsonReaderWriter extends JsonReaderWriter<GetResults<Integer>>
    {
    }

    interface IntegerNumberWrapperJsonReaderWriter extends JsonReaderWriter<NumberWrapper<Integer>>
    {
    }

    interface IntegerNumberWrapperSGJsonReaderWriter extends JsonReaderWriter<SomeGeneric<NumberWrapper<Integer>>>
    {
    }

    interface IntegerSGJsonReaderWriter extends JsonReaderWriter<SomeGeneric<Integer>>
    {
    }

    private IntegerNumberWrapperJsonReaderWriter INT_NUM_JSON_READER_WRITER;
    private GetResultsIntNumberWrapperJsonReaderWriter GET_RESULTS_INT_NUMBER_WRAPPER_JSON_READER_WRITER;
    private GetResultsIntegerJsonReaderWriter GET_RESULTS_INTEGER_READER_WRITER;

    @Override
    protected void gwtSetUp() throws Exception
    {
        super.gwtSetUp();

        GWT.create(IntegerSGJsonReaderWriter.class);
        GWT.create(IntegerNumberWrapperSGJsonReaderWriter.class);
        INT_NUM_JSON_READER_WRITER = GWT.create(IntegerNumberWrapperJsonReaderWriter.class);
        GET_RESULTS_INT_NUMBER_WRAPPER_JSON_READER_WRITER =
                GWT.create(GetResultsIntNumberWrapperJsonReaderWriter.class);
        GET_RESULTS_INTEGER_READER_WRITER = GWT.create(GetResultsIntegerJsonReaderWriter.class);
    }

    public void testJsonGetResultsNumberWrapperInteger()
    {
        NumberWrapper<Integer> object1 = makeNumberWrapper(1);
        NumberWrapper<Integer> object2 = makeNumberWrapper(2);
        NumberWrapper<Integer> object3 = makeNumberWrapper(3);

        List<NumberWrapper<Integer>> values = new ArrayList<NumberWrapper<Integer>>();
        values.add(object1);
        values.add(object2);
        values.add(object3);

        String json = GET_RESULTS_INT_NUMBER_WRAPPER_JSON_READER_WRITER
                .getJsonWriter()
                .toJson(new GetResults<NumberWrapper<Integer>>(values));
        GetResults<NumberWrapper<Integer>> result = GET_RESULTS_INT_NUMBER_WRAPPER_JSON_READER_WRITER
                .getJsonReader()
                .read(json);
        String resultJson = GET_RESULTS_INT_NUMBER_WRAPPER_JSON_READER_WRITER
                .getJsonWriter()
                .toJson(result);

        assertTrue(result.getResults().contains(object1));
        assertTrue(result.getResults().contains(object2));
        assertTrue(result.getResults().contains(object3));
        assertEquals(json, resultJson);
    }

    public void testJsonGetResultsInteger()
    {
        List<Integer> values = new ArrayList<Integer>();
        values.add(1);
        values.add(2);
        values.add(3);

        String json = GET_RESULTS_INTEGER_READER_WRITER.getJsonWriter().toJson(new GetResults<Integer>(values));
        GetResults<Integer> result = GET_RESULTS_INTEGER_READER_WRITER.getJsonReader().read(json);
        String resultJson = GET_RESULTS_INTEGER_READER_WRITER.getJsonWriter().toJson(result);

        assertTrue(result.getResults().contains(1));
        assertTrue(result.getResults().contains(2));
        assertTrue(result.getResults().contains(3));
        assertEquals(json, resultJson);
    }

    private <T extends Number> NumberWrapper<T> makeNumberWrapper(T value)
    {
        return new NumberWrapper<T>(value);
    }
}
