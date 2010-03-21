package name.pehl.piriti.gxt.client.fat;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import name.pehl.piriti.client.fat.Amount;
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
@XmlModel( {
        @XmlField(property = "booleanObject", type = Boolean.class),
        @XmlField(property = "byteObject", type = Byte.class),
        @XmlField(property = "characterObject", type = Character.class),
        @XmlField(property = "date", type = Date.class),
        @XmlField(property = "amount", type = Amount.class),
        @XmlField(property = "doubleObject", type = Double.class),
        @XmlField(property = "floatObject", type = Float.class),
        @XmlField(property = "integerObject", type = Integer.class),
        @XmlField(property = "longObject", type = Long.class),
        @XmlField(property = "shortObject", type = Short.class),
        @XmlField(property = "string", type = String.class),
        @XmlField(path = "string/@attribute", property = "stringAttribute", type = String.class),
        @XmlField(property = "fatGlobalItem", type = FatGlobalItem.class),
        @XmlField(property = "skinnyNestedItem", type = SkinnyNestedItem.class),
        @XmlField(property = "arrayOfIntegerObjects", type = Integer.class, array = true),
        @XmlField(property = "arrayOfStrings", type = String.class, array = true),
        @XmlField(path = "arrayOfSkinnyNestedItems/skinnyNestedItem", property = "arrayOfSkinnyNestedItems", type = SkinnyNestedItem.class, array = true),
        @XmlField(property = "arrayOfFatGlobalItems", type = FatGlobalItem.class, array = true),
        @XmlField(property = "arrayOfLists", type = List.class, array = true),
        @XmlField(property = "arrayOfMaps", type = Map.class, array = true),
        @XmlField(property = "collectionOfIntegerObjects", type = Collection.class, typeVariable = Integer.class),
        @XmlField(property = "collectionOfStrings", type = Collection.class, typeVariable = String.class),
        @XmlField(path = "collectionOfSkinnyNestedItems/skinnyNestedItem", property = "collectionOfSkinnyNestedItems", type = Collection.class, typeVariable = SkinnyNestedItem.class),
        @XmlField(property = "untypedCollection", type = Collection.class),
        @XmlField(property = "collectionOfFatGlobalItems", type = Collection.class, typeVariable = FatGlobalItem.class),
        @XmlField(property = "collectionOfCollections", type = Collection.class, typeVariable = Collection.class),
        @XmlField(property = "collectionOfMaps", type = Collection.class, typeVariable = Map.class),
        @XmlField(property = "listOfIntegerObjects", type = List.class, typeVariable = Integer.class),
        @XmlField(property = "listOfStrings", type = List.class, typeVariable = String.class),
        @XmlField(path = "listOfSkinnyNestedItems/skinnyNestedItem", property = "listOfSkinnyNestedItems", type = List.class, typeVariable = SkinnyNestedItem.class),
        @XmlField(property = "untypedList", type = List.class),
        @XmlField(property = "listOfFatGlobalItems", type = List.class, typeVariable = FatGlobalItem.class),
        @XmlField(property = "listOfLists", type = List.class, typeVariable = List.class),
        @XmlField(property = "listOfMaps", type = List.class, typeVariable = Map.class),
        @XmlField(property = "setOfIntegerObjects", type = Set.class, typeVariable = Integer.class),
        @XmlField(property = "setOfStrings", type = Set.class, typeVariable = String.class),
        @XmlField(path = "setOfSkinnyNestedItems/skinnyNestedItem", property = "setOfSkinnyNestedItems", type = Set.class, typeVariable = SkinnyNestedItem.class),
        @XmlField(property = "untypedSet", type = Set.class),
        @XmlField(property = "setOfFatGlobalItems", type = Set.class, typeVariable = FatGlobalItem.class),
        @XmlField(property = "setOfLists", type = Set.class, typeVariable = Set.class),
        @XmlField(property = "setOfMaps", type = Set.class, typeVariable = Map.class)})
@JsonModel( {
        @JsonField(property = "booleanObject", type = Boolean.class),
        @JsonField(property = "byteObject", type = Byte.class),
        @JsonField(property = "characterObject", type = Character.class),
        @JsonField(property = "date", type = Date.class),
        @JsonField(property = "amount", type = Amount.class),
        @JsonField(property = "doubleObject", type = Double.class),
        @JsonField(property = "floatObject", type = Float.class),
        @JsonField(property = "integerObject", type = Integer.class),
        @JsonField(property = "longObject", type = Long.class),
        @JsonField(property = "shortObject", type = Short.class),
        @JsonField(property = "string", type = String.class),
        @JsonField(property = "stringAttribute", type = String.class),
        @JsonField(property = "fatGlobalItem", type = FatGlobalItem.class),
        @JsonField(property = "skinnyNestedItem", type = SkinnyNestedItem.class),
        @JsonField(property = "arrayOfIntegerObjects", type = Integer.class, array = true),
        @JsonField(property = "arrayOfStrings", type = String.class, array = true),
        @JsonField(property = "arrayOfSkinnyNestedItems", type = SkinnyNestedItem.class, array = true),
        @JsonField(property = "arrayOfFatGlobalItems", type = FatGlobalItem.class, array = true),
        @JsonField(property = "arrayOfLists", type = List.class, array = true),
        @JsonField(property = "arrayOfMaps", type = Map.class, array = true),
        @JsonField(property = "collectionOfIntegerObjects", type = Collection.class, typeVariable = Integer.class),
        @JsonField(property = "collectionOfStrings", type = Collection.class, typeVariable = String.class),
        @JsonField(property = "collectionOfSkinnyNestedItems", type = Collection.class, typeVariable = SkinnyNestedItem.class),
        @JsonField(property = "untypedCollection", type = Collection.class),
        @JsonField(property = "collectionOfFatGlobalItems", type = Collection.class, typeVariable = FatGlobalItem.class),
        @JsonField(property = "collectionOfCollections", type = Collection.class, typeVariable = Collection.class),
        @JsonField(property = "collectionOfMaps", type = Collection.class, typeVariable = Map.class),
        @JsonField(property = "listOfIntegerObjects", type = List.class, typeVariable = Integer.class),
        @JsonField(property = "listOfStrings", type = List.class, typeVariable = String.class),
        @JsonField(property = "listOfSkinnyNestedItems", type = List.class, typeVariable = SkinnyNestedItem.class),
        @JsonField(property = "untypedList", type = List.class),
        @JsonField(property = "listOfFatGlobalItems", type = List.class, typeVariable = FatGlobalItem.class),
        @JsonField(property = "listOfLists", type = List.class, typeVariable = List.class),
        @JsonField(property = "listOfMaps", type = List.class, typeVariable = Map.class),
        @JsonField(property = "setOfIntegerObjects", type = Set.class, typeVariable = Integer.class),
        @JsonField(property = "setOfStrings", type = Set.class, typeVariable = String.class),
        @JsonField(property = "setOfSkinnyNestedItems", type = Set.class, typeVariable = SkinnyNestedItem.class),
        @JsonField(property = "untypedSet", type = Set.class),
        @JsonField(property = "setOfFatGlobalItems", type = Set.class, typeVariable = FatGlobalItem.class),
        @JsonField(property = "setOfLists", type = Set.class, typeVariable = Set.class),
        @JsonField(property = "setOfMaps", type = Set.class, typeVariable = Map.class)})
public class FatGlobalItem extends BaseModel
{
    // ------------------------------------------------------------- xml reader

    public interface FatGlobalItemXmlReader extends XmlModelReader<FatGlobalItem>
    {
    }

    public static final FatGlobalItemXmlReader XML = GWT.create(FatGlobalItemXmlReader.class);

    // ------------------------------------------------------------ json reader

    public interface FatGlobalItemJsonReader extends JsonModelReader<FatGlobalItem>
    {
    }

    public static final FatGlobalItemJsonReader JSON = GWT.create(FatGlobalItemJsonReader.class);
}
