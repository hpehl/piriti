package name.pehl.piriti.client.json.internal;

import name.pehl.piriti.client.json.JsonParser;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONException;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

/**
 * {@link JsonParser} implementation using the native JSON functionnality.
 * 
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public class NativeJsonParserImpl implements JsonParser
{
    @SuppressWarnings("unused")
    private static final JavaScriptObject typeMap = initTypeMap();


    @Override
    public native JSONValue parse(String text) throws JSONException
    /*-{
        var v = $wnd.JSON.parse(text);
        var func = @name.pehl.piriti.client.json.internal.NativeJsonParserImpl::typeMap[typeof v];
        return func ? func(v) : @name.pehl.piriti.client.json.internal.NativeJsonParserImpl::throwUnknownTypeException(Ljava/lang/String;)(typeof v);
    }-*/;


    private static native JavaScriptObject initTypeMap()
    /*-{
        return {
          "boolean": @name.pehl.piriti.client.json.internal.NativeJsonParserImpl::createBoolean(Z),
          "number": @name.pehl.piriti.client.json.internal.NativeJsonParserImpl::createNumber(D),
          "string": @name.pehl.piriti.client.json.internal.NativeJsonParserImpl::createString(Ljava/lang/String;),
          "object": @name.pehl.piriti.client.json.internal.NativeJsonParserImpl::createObject(Ljava/lang/Object;),
          "undefined": @name.pehl.piriti.client.json.internal.NativeJsonParserImpl::createUndefined(),
        }
    }-*/;


    @SuppressWarnings("unused")
    private static JSONValue createBoolean(boolean v)
    {
        return JSONBoolean.getInstance(v);
    }


    @SuppressWarnings("unused")
    private static JSONValue createNumber(double v)
    {
        return new JSONNumber(v);
    }


    @SuppressWarnings("unused")
    private static JSONValue createString(String v)
    {
        return new JSONString(v);
    }


    @SuppressWarnings("unused")
    private static native JSONValue createObject(Object o)
    /*-{
        if (!o) {
          return @com.google.gwt.json.client.JSONNull::getInstance()();
        }
        var v = o.valueOf ? o.valueOf() : o;
        if (v !== o) {
          // It was a primitive wrapper, unwrap it and try again.
          var func = @name.pehl.piriti.client.json.internal.NativeJsonParserImpl::typeMap[typeof v];
          return func ? func(v) : @name.pehl.piriti.client.json.internal.NativeJsonParserImpl::throwUnknownTypeException(Ljava/lang/String;)(typeof v);
        } else if (o instanceof Array || o instanceof $wnd.Array) {
          // Looks like an Array; wrap as JSONArray.
          // NOTE: this test can fail for objects coming from a different window,
          // but we know of no reliable tests to determine if something is an Array
          // in all cases.
          return @com.google.gwt.json.client.JSONArray::new(Lcom/google/gwt/core/client/JavaScriptObject;)(o);
        } else {
          // This is a basic JavaScript object; wrap as JSONObject.
          // Subobjects will be created on demand.
          return @com.google.gwt.json.client.JSONObject::new(Lcom/google/gwt/core/client/JavaScriptObject;)(o);
        }
    }-*/;


    @SuppressWarnings("unused")
    private static JSONValue createUndefined()
    {
        return null;
    }


    @SuppressWarnings("unused")
    private static void throwUnknownTypeException(String typeString)
    {
        throw new JSONException("Unexpected typeof result '" + typeString + "'; please report this bug to the GWT team");
    }
}
