package name.pehl.piriti.client.gwttest.fat;

import java.util.Date;

import name.pehl.piriti.client.json.JsonField;
import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.client.json.JsonWriter;
import name.pehl.piriti.client.xml.XmlField;
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

    interface SkinnyNestedItemJsonReader extends JsonReader<SkinnyNestedItem>
    {
    }

    public static final SkinnyNestedItemJsonReader SOME_FANCY_JSON_NAME = GWT.create(SkinnyNestedItemJsonReader.class);

    interface SkinnyNestedItemJsonWriter extends JsonWriter<SkinnyNestedItem>
    {
    }

    public static final SkinnyNestedItemJsonWriter ANOTHER_FANCY_JSON_NAME = GWT
            .create(SkinnyNestedItemJsonWriter.class);

    // ------------------------------------------------------------- xml reader

    interface SkinnyNestedItemXmlReader extends XmlReader<SkinnyNestedItem>
    {
    }

    public static final SkinnyNestedItemXmlReader SOME_FANCY_XML_NAME = GWT.create(SkinnyNestedItemXmlReader.class);

    interface SkinnyNestedItemXmlWriter extends XmlWriter<SkinnyNestedItem>
    {
    }

    public static final SkinnyNestedItemXmlWriter ANOTHER_FANCY_XML_NAME = GWT.create(SkinnyNestedItemXmlWriter.class);

    // -------------------------------------------------------- private members

    @JsonField
    @XmlField
    public boolean booleanPrimitive;

    @JsonField
    @XmlField
    public Boolean booleanObject;

    @JsonField
    @XmlField
    public byte bytePrimitive;

    @JsonField
    @XmlField
    public Byte byteObject;

    @JsonField
    @XmlField
    public char characterPrimitive;

    @JsonField
    @XmlField
    public Character characterObject;

    @JsonField
    @XmlField
    public Date date;

    @JsonField
    @XmlField
    public Amount amount;

    @JsonField
    @XmlField
    public double doublePrimitive;

    @JsonField
    @XmlField
    public Double doubleObject;

    @JsonField
    @XmlField
    public float floatPrimitive;

    @JsonField
    @XmlField
    public Float floatObject;

    @JsonField
    @XmlField
    public int integerPrimitive;

    @JsonField
    @XmlField
    public Integer integerObject;

    @JsonField
    @XmlField
    public long longPrimitive;

    @JsonField
    @XmlField
    public Long longObject;

    @JsonField
    @XmlField
    public short shortPrimitive;

    @JsonField
    @XmlField
    public Short shortObject;

    @JsonField
    @XmlField
    public String string;

    @JsonField
    @XmlField("string/@attribute")
    public String stringAttribute;
}
