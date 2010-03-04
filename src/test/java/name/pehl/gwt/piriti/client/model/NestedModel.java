package name.pehl.gwt.piriti.client.model;

import java.util.Date;

import name.pehl.gwt.piriti.client.json.JsonField;
import name.pehl.gwt.piriti.client.xml.XmlField;
import name.pehl.gwt.piriti.client.xml.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 131 $
 */
public class NestedModel
{
    // ------------------------------------------------------------- xml reader

    interface NestedModelXmlReader extends XmlReader<NestedModel>
    {
    }

    public static final NestedModelXmlReader SOME_FANCY_XML_NAME = GWT.create(NestedModelXmlReader.class);

    // ------------------------------------------------------------ json reader

    // interface NestedModelJsonReader extends XmlReader<NestedModel>
    // {
    // }
    //
    // public static final NestedModelJsonReader SOME_FANCY_JSON_NAME =
    // GWT.create(NestedModelJsonReader.class);

    // -------------------------------------------------------- private members

    @XmlField
    @JsonField
    public boolean booleanPrimitive;

    @XmlField
    @JsonField
    public Boolean booleanObject;

    @XmlField
    @JsonField
    public byte bytePrimitive;

    @XmlField
    @JsonField
    public Byte byteObject;

    @XmlField
    @JsonField
    public char characterPrimitive;

    @XmlField
    @JsonField
    public Character characterObject;

    @XmlField
    @JsonField
    public Date date;

    @XmlField
    @JsonField
    public Amount demoEnum;

    @XmlField
    @JsonField
    public double doublePrimitive;

    @XmlField
    @JsonField
    public Double doubleObject;

    @XmlField
    @JsonField
    public float floatPrimitive;

    @XmlField
    @JsonField
    public Float floatObject;

    @XmlField
    @JsonField
    public int integerPrimitive;

    @XmlField
    @JsonField
    public Integer integerObject;

    @XmlField
    @JsonField
    public long longPrimitive;

    @XmlField
    @JsonField
    public Long longObject;

    @XmlField
    @JsonField
    public short shortPrimitive;

    @XmlField
    @JsonField
    public Short shortObject;

    @XmlField
    @JsonField
    public String string;

    @XmlField("string/@attribute")
    @JsonField
    public String stringAttribute;
}
