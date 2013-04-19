package name.pehl.piriti.client.generics;

import java.util.ArrayList;
import java.util.List;

import name.pehl.piriti.client.AbstractPiritiTest;

import com.google.gwt.core.client.GWT;

public class JsonGenericsTest extends AbstractPiritiTest
{
    interface GetResultsIntNumberWrapperJsonWriter extends GetResults.GetResultsJsonWriter<NumberWrapper<Integer>>
    {
    }

    interface GetResultsIntNumberWrapperJsonReader extends GetResults.GetResultsJsonReader<NumberWrapper<Integer>>
    {
    }

    interface IntegerNumberWrapperJsonWriter extends NumberWrapper.NumberWrapperJsonWriter<Integer>
    {
    }

    interface IntegerNumberWrapperJsonReader extends NumberWrapper.NumberWrapperJsonReader<Integer>
    {
    }

    private IntegerNumberWrapperJsonWriter INT_NUM_JSON_WRITER;
    private IntegerNumberWrapperJsonReader INT_NUM_JSON_READER;
    private GetResultsIntNumberWrapperJsonWriter GET_RESULTS_INT_NUMBER_WRAPPER_JSON_WRITER;
    private GetResultsIntNumberWrapperJsonReader GET_RESULTS_INT_NUMBER_WRAPPER_JSON_READER;

    @Override
    protected void gwtSetUp() throws Exception
    {
        super.gwtSetUp();

        INT_NUM_JSON_WRITER = GWT.create(IntegerNumberWrapperJsonWriter.class);
        INT_NUM_JSON_READER = GWT.create(IntegerNumberWrapperJsonReader.class);
        GET_RESULTS_INT_NUMBER_WRAPPER_JSON_WRITER = GWT.create(GetResultsIntNumberWrapperJsonWriter.class);
        GET_RESULTS_INT_NUMBER_WRAPPER_JSON_READER = GWT.create(GetResultsIntNumberWrapperJsonReader.class);
    }

    public void testJsonPojoList()
    {
        NumberWrapper<Integer> object1 = makeNumberWrapper(1);
        NumberWrapper<Integer> object2 = makeNumberWrapper(2);
        NumberWrapper<Integer> object3 = makeNumberWrapper(3);

        List<NumberWrapper<Integer>> values = new ArrayList<NumberWrapper<Integer>>();
        values.add(object1);
        values.add(object2);
        values.add(object3);

        String json = GET_RESULTS_INT_NUMBER_WRAPPER_JSON_WRITER.toJson(new GetResults<NumberWrapper<Integer>>(values));
        GetResults<NumberWrapper<Integer>> result = GET_RESULTS_INT_NUMBER_WRAPPER_JSON_READER.read(json);
        String resultJson = GET_RESULTS_INT_NUMBER_WRAPPER_JSON_WRITER.toJson(result);

        assertTrue(result.getResults().contains(object1));
        assertTrue(result.getResults().contains(object2));
        assertTrue(result.getResults().contains(object3));
        assertEquals(json, resultJson);
    }

    private <T extends Number> NumberWrapper<T> makeNumberWrapper(T value)
    {
        return new NumberWrapper<T>(value);
    }
}
