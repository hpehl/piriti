package name.pehl.piriti.gxt.client.gwttest.fat;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import name.pehl.piriti.client.gwttest.fat.Amount;
import name.pehl.piriti.gxt.client.json.Json;
import name.pehl.piriti.gxt.client.json.JsonMappings;
import name.pehl.piriti.gxt.client.json.JsonModelReader;
import name.pehl.piriti.gxt.client.xml.Xml;
import name.pehl.piriti.gxt.client.xml.XmlMappings;
import name.pehl.piriti.gxt.client.xml.XmlModelReader;

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
    @Json(property = "stringAttribute", path="customNameForStringAttribute", type = String.class),
    @Json(property = "fatGlobalItem", type = FatGlobalItem.class),
    @Json(property = "skinnyNestedItem", type = SkinnyNestedItem.class),
    @Json(property = "arrayOfIntegerObjects", type = Integer.class, array = true),
    @Json(property = "arrayOfStrings", type = String.class, array = true),
    @Json(property = "arrayOfFatGlobalItems", type = FatGlobalItem.class, array = true),
    @Json(property = "arrayOfSkinnyNestedItems", type = SkinnyNestedItem.class, array = true),
    @Json(property = "arrayOfLists", type = List.class, array = true),
    @Json(property = "arrayOfMaps", type = Map.class, array = true),
    @Json(property = "collectionOfIntegerObjects", type = Collection.class, typeVariable = Integer.class),
    @Json(property = "collectionOfStrings", type = Collection.class, typeVariable = String.class),
    @Json(property = "collectionOfFatGlobalItems", type = Collection.class, typeVariable = FatGlobalItem.class),
    @Json(property = "collectionOfSkinnyNestedItems", type = Collection.class, typeVariable = SkinnyNestedItem.class),
    @Json(property = "untypedCollection", type = Collection.class),
    @Json(property = "collectionOfCollections", type = Collection.class, typeVariable = Collection.class),
    @Json(property = "collectionOfMaps", type = Collection.class, typeVariable = Map.class),
    @Json(property = "listOfIntegerObjects", type = List.class, typeVariable = Integer.class),
    @Json(property = "listOfStrings", type = List.class, typeVariable = String.class),
    @Json(property = "listOfFatGlobalItems", type = List.class, typeVariable = FatGlobalItem.class),
    @Json(property = "listOfSkinnyNestedItems", type = List.class, typeVariable = SkinnyNestedItem.class),
    @Json(property = "untypedList", type = List.class),
    @Json(property = "listOfLists", type = List.class, typeVariable = List.class),
    @Json(property = "listOfMaps", type = List.class, typeVariable = Map.class),
    @Json(property = "setOfIntegerObjects", type = Set.class, typeVariable = Integer.class),
    @Json(property = "setOfStrings", type = Set.class, typeVariable = String.class),
    @Json(property = "setOfFatGlobalItems", type = Set.class, typeVariable = FatGlobalItem.class),
    @Json(property = "setOfSkinnyNestedItems", type = Set.class, typeVariable = SkinnyNestedItem.class),
    @Json(property = "untypedSet", type = Set.class),
    @Json(property = "setOfLists", type = Set.class, typeVariable = Set.class),
    @Json(property = "setOfMaps", type = Set.class, typeVariable = Map.class)})
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
    @Xml(property = "stringAttribute", path = "string/@attribute", type = String.class),
    @Xml(property = "fatGlobalItem", type = FatGlobalItem.class),
    @Xml(property = "skinnyNestedItem", type = SkinnyNestedItem.class),
    @Xml(property = "arrayOfIntegerObjects", type = Integer.class, array = true),
    @Xml(property = "arrayOfStrings", type = String.class, array = true),
    @Xml(property = "arrayOfFatGlobalItems", path = "arrayOfFatGlobalItems/fatGlobalItem", type = FatGlobalItem.class, array = true),
    @Xml(property = "arrayOfSkinnyNestedItems", path = "arrayOfSkinnyNestedItems/skinnyNestedItem", type = SkinnyNestedItem.class, array = true),
    @Xml(property = "arrayOfLists", type = List.class, array = true),
    @Xml(property = "arrayOfMaps", type = Map.class, array = true),
    @Xml(property = "collectionOfIntegerObjects", type = Collection.class, typeVariable = Integer.class),
    @Xml(property = "collectionOfStrings", type = Collection.class, typeVariable = String.class),
    @Xml(property = "collectionOfFatGlobalItems", path = "collectionOfFatGlobalItems/fatGlobalItem", type = Collection.class, typeVariable = FatGlobalItem.class),
    @Xml(property = "collectionOfSkinnyNestedItems", path = "collectionOfSkinnyNestedItems/skinnyNestedItem", type = Collection.class, typeVariable = SkinnyNestedItem.class),
    @Xml(property = "untypedCollection", type = Collection.class),
    @Xml(property = "collectionOfCollections", type = Collection.class, typeVariable = Collection.class),
    @Xml(property = "collectionOfMaps", type = Collection.class, typeVariable = Map.class),
    @Xml(property = "listOfIntegerObjects", type = List.class, typeVariable = Integer.class),
    @Xml(property = "listOfStrings", type = List.class, typeVariable = String.class),
    @Xml(property = "listOfFatGlobalItems", path = "listOfFatGlobalItems/fatGlobalItem", type = List.class, typeVariable = FatGlobalItem.class),
    @Xml(property = "listOfSkinnyNestedItems", path = "listOfSkinnyNestedItems/skinnyNestedItem", type = List.class, typeVariable = SkinnyNestedItem.class),
    @Xml(property = "untypedList", type = List.class),
    @Xml(property = "listOfLists", type = List.class, typeVariable = List.class),
    @Xml(property = "listOfMaps", type = List.class, typeVariable = Map.class),
    @Xml(property = "setOfIntegerObjects", type = Set.class, typeVariable = Integer.class),
    @Xml(property = "setOfStrings", type = Set.class, typeVariable = String.class),
    @Xml(property = "setOfFatGlobalItems", path = "setOfFatGlobalItems/fatGlobalItem", type = Set.class, typeVariable = FatGlobalItem.class),
    @Xml(property = "setOfSkinnyNestedItems", path = "setOfSkinnyNestedItems/skinnyNestedItem", type = Set.class, typeVariable = SkinnyNestedItem.class),
    @Xml(property = "untypedSet", type = Set.class),
    @Xml(property = "setOfLists", type = Set.class, typeVariable = Set.class),
    @Xml(property = "setOfMaps", type = Set.class, typeVariable = Map.class)})
// @formatter:on
public class FatGlobalItem extends BaseModel
{
    // --------------------------------------------------- json reader / writer
    
    public interface FatGlobalItemJsonReader extends JsonModelReader<FatGlobalItem>
    {
    }

    public static final FatGlobalItemJsonReader JSON_READER = GWT.create(FatGlobalItemJsonReader.class);

    // ---------------------------------------------------- xml reader / writer
    
    public interface FatGlobalItemXmlReader extends XmlModelReader<FatGlobalItem>
    {
    }

    public static final FatGlobalItemXmlReader XML_READER = GWT.create(FatGlobalItemXmlReader.class);
}
