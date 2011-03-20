package name.pehl.piriti.gxt.client.types;

import java.util.Date;

import name.pehl.piriti.client.types.Amount;
import name.pehl.piriti.gxt.json.client.Json;
import name.pehl.piriti.gxt.json.client.JsonMappings;
import name.pehl.piriti.gxt.json.client.JsonModelReader;
import name.pehl.piriti.gxt.xml.client.Xml;
import name.pehl.piriti.gxt.xml.client.XmlMappings;
import name.pehl.piriti.gxt.xml.client.XmlModelReader;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.core.client.GWT;

/**
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
//@formatter:off
@JsonMappings({
    @Json(property = "booleanObject", type = Boolean.class),
    @Json(property = "byteObject", type = Byte.class),
    @Json(property = "characterObject", type = Character.class),
    @Json(property = "date", type = Date.class), 
    @Json(property = "amount", type = Amount.class),
    @Json(property = "doubleObject", type = Double.class),
    @Json(property = "floatObject", type = Float.class),
    @Json(property = "integerObject", type = Integer.class),
    @Json(property = "longObject", type = Long.class),
    @Json(property = "shortObject", type = Short.class), 
    @Json(property = "string", type = String.class),
    @Json(property = "stringAttribute", path="customNameForStringAttribute", type = String.class)})
@XmlMappings({
    @Xml(property = "booleanObject", type = Boolean.class),
    @Xml(property = "byteObject", type = Byte.class),
    @Xml(property = "characterObject", type = Character.class),
    @Xml(property = "date", type = Date.class), 
    @Xml(property = "amount", type = Amount.class),
    @Xml(property = "doubleObject", type = Double.class),
    @Xml(property = "floatObject", type = Float.class),
    @Xml(property = "integerObject", type = Integer.class),
    @Xml(property = "longObject", type = Long.class), 
    @Xml(property = "shortObject", type = Short.class),
    @Xml(property = "string", type = String.class),
    @Xml(property = "stringAttribute", path = "string/@attribute", type = String.class)})
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
