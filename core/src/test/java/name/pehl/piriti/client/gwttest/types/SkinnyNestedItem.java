package name.pehl.piriti.client.gwttest.types;

import java.util.Date;

import name.pehl.piriti.client.json.Json;
import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.client.json.JsonWriter;
import name.pehl.piriti.client.xml.Xml;
import name.pehl.piriti.client.xml.XmlReader;
import name.pehl.piriti.client.xml.XmlWriter;

import com.google.gwt.core.client.GWT;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 131 $
 */
public class SkinnyNestedItem
{
    // ------------------------------------------------------------ json reader

    // @formatter:off
    interface SkinnyNestedItemJsonReader extends JsonReader<SkinnyNestedItem> {}
    public static final SkinnyNestedItemJsonReader SOME_FANCY_JSON_NAME = GWT.create(SkinnyNestedItemJsonReader.class);

    interface SkinnyNestedItemJsonWriter extends JsonWriter<SkinnyNestedItem> {}
    public static final SkinnyNestedItemJsonWriter ANOTHER_FANCY_JSON_NAME = GWT.create(SkinnyNestedItemJsonWriter.class);
    // @formatter:off

    // ------------------------------------------------------------- xml reader

    // @formatter:off
    interface SkinnyNestedItemXmlReader extends XmlReader<SkinnyNestedItem> {}
    public static final SkinnyNestedItemXmlReader SOME_FANCY_XML_NAME = GWT.create(SkinnyNestedItemXmlReader.class);

    interface SkinnyNestedItemXmlWriter extends XmlWriter<SkinnyNestedItem> {}
    public static final SkinnyNestedItemXmlWriter ANOTHER_FANCY_XML_NAME = GWT.create(SkinnyNestedItemXmlWriter.class);
    // @formatter:on

    // -------------------------------------------------------- private members

    @Json
    @Xml
    public boolean booleanPrimitive;

    @Json
    @Xml
    public Boolean booleanObject;

    @Json
    @Xml
    public byte bytePrimitive;

    @Json
    @Xml
    public Byte byteObject;

    @Json
    @Xml
    public char characterPrimitive;

    @Json
    @Xml
    public Character characterObject;

    @Json(format = "dd.MM.yyyy")
    @Xml(format = "dd.MM.yyyy")
    public Date date;

    @Json
    @Xml
    public java.sql.Date sqlDate;

    @Json
    @Xml
    public java.sql.Time time;

    @Json
    @Xml
    public java.sql.Timestamp timestamp;

    @Json
    @Xml
    public Amount amount;

    @Json
    @Xml
    public double doublePrimitive;

    @Json
    @Xml
    public Double doubleObject;

    @Json
    @Xml
    public float floatPrimitive;

    @Json
    @Xml
    public Float floatObject;

    @Json
    @Xml
    public int integerPrimitive;

    @Json
    @Xml
    public Integer integerObject;

    @Json
    @Xml
    public long longPrimitive;

    @Json
    @Xml
    public Long longObject;

    @Json
    @Xml
    public short shortPrimitive;

    @Json
    @Xml
    public Short shortObject;

    @Json
    @Xml
    public String string;

    @Json("customNameForStringAttribute")
    @Xml("string/@attribute")
    public String stringAttribute;
}
