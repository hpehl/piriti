package name.pehl.piriti.gxt.client.gwttest.fat;

import java.util.Date;

import name.pehl.piriti.client.gwttest.fat.Amount;
import name.pehl.piriti.gxt.client.json.JsonField;
import name.pehl.piriti.gxt.client.json.JsonModel;
import name.pehl.piriti.gxt.client.json.JsonModelReader;
import name.pehl.piriti.gxt.client.xml.XmlField;
import name.pehl.piriti.gxt.client.xml.XmlModel;
import name.pehl.piriti.gxt.client.xml.XmlModelReader;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.core.client.GWT;

/**
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
@XmlModel( {@XmlField(property = "booleanObject", type = Boolean.class),
        @XmlField(property = "byteObject", type = Byte.class),
        @XmlField(property = "characterObject", type = Character.class),
        @XmlField(property = "date", type = Date.class), @XmlField(property = "amount", type = Amount.class),
        @XmlField(property = "doubleObject", type = Double.class),
        @XmlField(property = "floatObject", type = Float.class),
        @XmlField(property = "integerObject", type = Integer.class),
        @XmlField(property = "longObject", type = Long.class), @XmlField(property = "shortObject", type = Short.class),
        @XmlField(property = "string", type = String.class),
        @XmlField(path = "string/@attribute", property = "stringAttribute", type = String.class)})
@JsonModel( {@JsonField(property = "booleanObject", type = Boolean.class),
        @JsonField(property = "byteObject", type = Byte.class),
        @JsonField(property = "characterObject", type = Character.class),
        @JsonField(property = "date", type = Date.class), @JsonField(property = "amount", type = Amount.class),
        @JsonField(property = "doubleObject", type = Double.class),
        @JsonField(property = "floatObject", type = Float.class),
        @JsonField(property = "integerObject", type = Integer.class),
        @JsonField(property = "longObject", type = Long.class),
        @JsonField(property = "shortObject", type = Short.class), @JsonField(property = "string", type = String.class),
        @JsonField(property = "stringAttribute", type = String.class)})
public class SkinnyNestedItem extends BaseModel
{
    // ------------------------------------------------------------- xml reader

    interface SkinnyNestedItemXmlReader extends XmlModelReader<SkinnyNestedItem>
    {
    }

    public static final SkinnyNestedItemXmlReader SOME_FANCY_XML_NAME = GWT.create(SkinnyNestedItemXmlReader.class);

    // ------------------------------------------------------------ json reader

    interface SkinnyNestedItemJsonReader extends JsonModelReader<SkinnyNestedItem>
    {
    }

    public static final SkinnyNestedItemJsonReader SOME_FANCY_JSON_NAME = GWT
            .create(SkinnyNestedItemJsonReader.class);
}
