package name.pehl.piriti.gxt.client.fat;

import name.pehl.piriti.gxt.client.json.JsonModelReader;
import name.pehl.piriti.gxt.client.xml.XmlModelReader;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.core.client.GWT;

/**
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
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

    // ------------------------------------------ primitives and simple objects
    /*
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
    public Amount amount;

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

    // --------------------------------------------------------- nested objects

    @XmlField
    @JsonField
    public FatGlobalItem fatGlobalItem;

    @XmlField
    @JsonField
    public SkinnyNestedItem skinnyNestedItem;

    // ----------------------------------------------------------------- arrays

    @XmlField
    @JsonField
    public int[] arrayOfIntegerPrimitives;

    @XmlField
    @JsonField
    public Integer[] arrayOfIntegerObjects;

    @XmlField
    @JsonField
    public String[] arrayOfStrings;

    @XmlField("arrayOfSkinnyNestedItems/skinnyNestedItem")
    @JsonField
    public SkinnyNestedItem[] arrayOfSkinnyNestedItems;

    // --------------------------------------------------------- invalid arrays

    @XmlField
    @JsonField
    public FatGlobalItem[] arrayOfFatGlobalItems;

    @XmlField
    @JsonField
    public List<String>[] arrayOfLists;

    @XmlField
    @JsonField
    public Map<String, String>[] arrayOfMaps;

    @XmlField
    @JsonField
    public int[][] multiDimensionalIntegerPrimitiveArray;

    @XmlField
    @JsonField
    public Integer[][] multiDimensionalIntegerObjectArray;

    // ------------------------------------------------------------ collections

    @XmlField
    @JsonField
    public Collection<Integer> collectionOfIntegerObjects;

    @XmlField
    @JsonField
    public Collection<String> collectionOfStrings;

    @XmlField("collectionOfSkinnyNestedItems/skinnyNestedItem")
    @JsonField
    public Collection<SkinnyNestedItem> collectionOfSkinnyNestedItems;

    // ---------------------------------------------------- invalid collections

    @XmlField
    @JsonField
    public @SuppressWarnings("unchecked")
    Collection untypedCollection;

    @XmlField
    @JsonField
    public Collection<FatGlobalItem> collectionOfFatGlobalItems;

    @XmlField
    @JsonField
    public Collection<String[]> collectionOfArrays;

    @XmlField
    @JsonField
    public Collection<Collection<String>> collectionOfCollections;

    @XmlField
    @JsonField
    public Collection<Map<String, String>> collectionOfMaps;

    // ------------------------------------------------------------------ lists

    @XmlField
    @JsonField
    public List<Integer> listOfIntegerObjects;

    @XmlField
    @JsonField
    public List<String> listOfStrings;

    @XmlField("listOfSkinnyNestedItems/skinnyNestedItem")
    @JsonField
    public List<SkinnyNestedItem> listOfSkinnyNestedItems;

    // ---------------------------------------------------------- invalid lists

    @XmlField
    @JsonField
    public @SuppressWarnings("unchecked")
    List untypedList;

    @XmlField
    @JsonField
    public List<FatGlobalItem> listOfFatGlobalItems;

    @XmlField
    @JsonField
    public List<String[]> listOfArrays;

    @XmlField
    @JsonField
    public List<List<String>> listOfLists;

    @XmlField
    @JsonField
    public List<Map<String, String>> listOfMaps;

    // ------------------------------------------------------------------- sets

    @XmlField
    @JsonField
    public Set<Integer> setOfIntegerObjects;

    @XmlField
    @JsonField
    public Set<String> setOfStrings;

    @XmlField("setOfSkinnyNestedItems/skinnyNestedItem")
    @JsonField
    public Set<SkinnyNestedItem> setOfSkinnyNestedItems;

    // ----------------------------------------------------------- invalid sets

    @XmlField
    @JsonField
    public @SuppressWarnings("unchecked")
    Set untypedSet;

    @XmlField
    @JsonField
    public Set<FatGlobalItem> setOfFatGlobalItems;

    @XmlField
    @JsonField
    public Set<String[]> setOfArrays;

    @XmlField
    @JsonField
    public Set<Set<String>> setOfSets;

    @XmlField
    @JsonField
    public Set<Map<String, String>> setOfMaps;
    */
}
