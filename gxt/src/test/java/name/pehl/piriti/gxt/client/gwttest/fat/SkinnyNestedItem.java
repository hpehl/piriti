package name.pehl.piriti.gxt.client.gwttest.fat;

import java.util.Date;

import name.pehl.piriti.client.gwttest.fat.Amount;
import name.pehl.piriti.gxt.client.json.JsonField;
import name.pehl.piriti.gxt.client.json.JsonFields;
import name.pehl.piriti.gxt.client.json.JsonModelReader;
import name.pehl.piriti.gxt.client.xml.XmlField;
import name.pehl.piriti.gxt.client.xml.XmlFields;
import name.pehl.piriti.gxt.client.xml.XmlModelReader;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.core.client.GWT;

/**
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
//@formatter:off
@JsonFields({
    @JsonField(name = "booleanObject", type = Boolean.class),
    @JsonField(name = "byteObject", type = Byte.class),
    @JsonField(name = "characterObject", type = Character.class),
    @JsonField(name = "date", type = Date.class), 
    @JsonField(name = "amount", type = Amount.class),
    @JsonField(name = "doubleObject", type = Double.class),
    @JsonField(name = "floatObject", type = Float.class),
    @JsonField(name = "integerObject", type = Integer.class),
    @JsonField(name = "longObject", type = Long.class),
    @JsonField(name = "shortObject", type = Short.class), 
    @JsonField(name = "string", type = String.class),
    @JsonField(name = "stringAttribute", type = String.class)})
@XmlFields({
    @XmlField(name = "booleanObject", type = Boolean.class),
    @XmlField(name = "byteObject", type = Byte.class),
    @XmlField(name = "characterObject", type = Character.class),
    @XmlField(name = "date", type = Date.class), 
    @XmlField(name = "amount", type = Amount.class),
    @XmlField(name = "doubleObject", type = Double.class),
    @XmlField(name = "floatObject", type = Float.class),
    @XmlField(name = "integerObject", type = Integer.class),
    @XmlField(name = "longObject", type = Long.class), 
    @XmlField(name = "shortObject", type = Short.class),
    @XmlField(name = "string", type = String.class),
    @XmlField(name = "stringAttribute", path = "string/@attribute", type = String.class)})
// @formatter:on
public class SkinnyNestedItem extends BaseModel
{
    // --------------------------------------------------- json reader / writer
    
    interface SkinnyNestedItemJsonReader extends JsonModelReader<SkinnyNestedItem>
    {
    }

    public static final SkinnyNestedItemJsonReader SOME_FANCY_JSON_NAME = GWT.create(SkinnyNestedItemJsonReader.class);

    // ---------------------------------------------------- xml reader / writer
    
    interface SkinnyNestedItemXmlReader extends XmlModelReader<SkinnyNestedItem>
    {
    }

    public static final SkinnyNestedItemXmlReader SOME_FANCY_XML_NAME = GWT.create(SkinnyNestedItemXmlReader.class);
}
