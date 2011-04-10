package name.pehl.piriti.client.types;

import java.util.Date;

import name.pehl.piriti.commons.client.Format;
import name.pehl.piriti.commons.client.Path;
import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

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

    public boolean booleanPrimitive;
    public Boolean booleanObject;
    public byte bytePrimitive;
    public Byte byteObject;
    public char characterPrimitive;
    public Character characterObject;
    @Format("dd.MM.yyyy")
    public Date date;
    public java.sql.Date sqlDate;
    public java.sql.Time time;
    public java.sql.Timestamp timestamp;
    public Amount amount;
    public double doublePrimitive;
    public Double doubleObject;
    public float floatPrimitive;
    public Float floatObject;
    public int integerPrimitive;
    public Integer integerObject;
    public long longPrimitive;
    public Long longObject;
    public short shortPrimitive;
    public Short shortObject;
    public String string;
    @Path("customNameForStringAttribute")
    // @Path("string/@attribute")
    public String stringAttribute;
}
