package name.pehl.piriti.client.native_;

import name.pehl.piriti.commons.client.Native;
import name.pehl.piriti.commons.client.Path;
import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.totoe.xml.client.Element;
import name.pehl.totoe.xml.client.Node;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

// @formatter:off
public class Response
{
    public interface ResponseJsonReader extends JsonReader<Response> {}
    public static final ResponseJsonReader JSON_READER = GWT.create(ResponseJsonReader.class);

    public interface ResponseXmlReader extends XmlReader<Response> {}
    public static final ResponseXmlReader XML_READER = GWT.create(ResponseXmlReader.class);

    String version;
    int recode;

    @Native @Path("result") String resultAsString;
    @Native @Path("result") JSONValue resultAsJsonValue;
    @Native @Path("result") JSONObject resultAsJsonObject;
    @Native @Path("result") Node resultAsNode;
    @Native @Path("result") Element resultAsElement;
}
