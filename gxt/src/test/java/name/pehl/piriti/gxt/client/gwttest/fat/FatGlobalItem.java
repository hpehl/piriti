package name.pehl.piriti.gxt.client.gwttest.fat;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    @JsonField(name = "stringAttribute", type = String.class),
    @JsonField(name = "fatGlobalItem", type = FatGlobalItem.class),
    @JsonField(name = "skinnyNestedItem", type = SkinnyNestedItem.class),
    @JsonField(name = "arrayOfIntegerObjects", type = Integer.class, array = true),
    @JsonField(name = "arrayOfStrings", type = String.class, array = true),
    @JsonField(name = "arrayOfFatGlobalItems", type = FatGlobalItem.class, array = true),
    @JsonField(name = "arrayOfSkinnyNestedItems", type = SkinnyNestedItem.class, array = true),
    @JsonField(name = "arrayOfLists", type = List.class, array = true),
    @JsonField(name = "arrayOfMaps", type = Map.class, array = true),
    @JsonField(name = "collectionOfIntegerObjects", type = Collection.class, typeVariable = Integer.class),
    @JsonField(name = "collectionOfStrings", type = Collection.class, typeVariable = String.class),
    @JsonField(name = "collectionOfFatGlobalItems", type = Collection.class, typeVariable = FatGlobalItem.class),
    @JsonField(name = "collectionOfSkinnyNestedItems", type = Collection.class, typeVariable = SkinnyNestedItem.class),
    @JsonField(name = "untypedCollection", type = Collection.class),
    @JsonField(name = "collectionOfCollections", type = Collection.class, typeVariable = Collection.class),
    @JsonField(name = "collectionOfMaps", type = Collection.class, typeVariable = Map.class),
    @JsonField(name = "listOfIntegerObjects", type = List.class, typeVariable = Integer.class),
    @JsonField(name = "listOfStrings", type = List.class, typeVariable = String.class),
    @JsonField(name = "listOfFatGlobalItems", type = List.class, typeVariable = FatGlobalItem.class),
    @JsonField(name = "listOfSkinnyNestedItems", type = List.class, typeVariable = SkinnyNestedItem.class),
    @JsonField(name = "untypedList", type = List.class),
    @JsonField(name = "listOfLists", type = List.class, typeVariable = List.class),
    @JsonField(name = "listOfMaps", type = List.class, typeVariable = Map.class),
    @JsonField(name = "setOfIntegerObjects", type = Set.class, typeVariable = Integer.class),
    @JsonField(name = "setOfStrings", type = Set.class, typeVariable = String.class),
    @JsonField(name = "setOfFatGlobalItems", type = Set.class, typeVariable = FatGlobalItem.class),
    @JsonField(name = "setOfSkinnyNestedItems", type = Set.class, typeVariable = SkinnyNestedItem.class),
    @JsonField(name = "untypedSet", type = Set.class),
    @JsonField(name = "setOfLists", type = Set.class, typeVariable = Set.class),
    @JsonField(name = "setOfMaps", type = Set.class, typeVariable = Map.class)})
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
    @XmlField(name = "stringAttribute", path = "string/@attribute", type = String.class),
    @XmlField(name = "fatGlobalItem", type = FatGlobalItem.class),
    @XmlField(name = "skinnyNestedItem", type = SkinnyNestedItem.class),
    @XmlField(name = "arrayOfIntegerObjects", type = Integer.class, array = true),
    @XmlField(name = "arrayOfStrings", type = String.class, array = true),
    @XmlField(name = "arrayOfFatGlobalItems", path = "arrayOfFatGlobalItems/fatGlobalItem", type = FatGlobalItem.class, array = true),
    @XmlField(name = "arrayOfSkinnyNestedItems", path = "arrayOfSkinnyNestedItems/skinnyNestedItem", type = SkinnyNestedItem.class, array = true),
    @XmlField(name = "arrayOfLists", type = List.class, array = true),
    @XmlField(name = "arrayOfMaps", type = Map.class, array = true),
    @XmlField(name = "collectionOfIntegerObjects", type = Collection.class, typeVariable = Integer.class),
    @XmlField(name = "collectionOfStrings", type = Collection.class, typeVariable = String.class),
    @XmlField(name = "collectionOfFatGlobalItems", path = "collectionOfFatGlobalItems/fatGlobalItem", type = Collection.class, typeVariable = FatGlobalItem.class),
    @XmlField(name = "collectionOfSkinnyNestedItems", path = "collectionOfSkinnyNestedItems/skinnyNestedItem", type = Collection.class, typeVariable = SkinnyNestedItem.class),
    @XmlField(name = "untypedCollection", type = Collection.class),
    @XmlField(name = "collectionOfCollections", type = Collection.class, typeVariable = Collection.class),
    @XmlField(name = "collectionOfMaps", type = Collection.class, typeVariable = Map.class),
    @XmlField(name = "listOfIntegerObjects", type = List.class, typeVariable = Integer.class),
    @XmlField(name = "listOfStrings", type = List.class, typeVariable = String.class),
    @XmlField(name = "listOfFatGlobalItems", path = "listOfFatGlobalItems/fatGlobalItem", type = List.class, typeVariable = FatGlobalItem.class),
    @XmlField(name = "listOfSkinnyNestedItems", path = "listOfSkinnyNestedItems/skinnyNestedItem", type = List.class, typeVariable = SkinnyNestedItem.class),
    @XmlField(name = "untypedList", type = List.class),
    @XmlField(name = "listOfLists", type = List.class, typeVariable = List.class),
    @XmlField(name = "listOfMaps", type = List.class, typeVariable = Map.class),
    @XmlField(name = "setOfIntegerObjects", type = Set.class, typeVariable = Integer.class),
    @XmlField(name = "setOfStrings", type = Set.class, typeVariable = String.class),
    @XmlField(name = "setOfFatGlobalItems", path = "setOfFatGlobalItems/fatGlobalItem", type = Set.class, typeVariable = FatGlobalItem.class),
    @XmlField(name = "setOfSkinnyNestedItems", path = "setOfSkinnyNestedItems/skinnyNestedItem", type = Set.class, typeVariable = SkinnyNestedItem.class),
    @XmlField(name = "untypedSet", type = Set.class),
    @XmlField(name = "setOfLists", type = Set.class, typeVariable = Set.class),
    @XmlField(name = "setOfMaps", type = Set.class, typeVariable = Map.class)})
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
