package name.pehl.piriti.client.gwttest.fat;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
public class FatGlobalItem
{
    // ------------------------------------------------------------ json reader

    // @formatter:off
    public interface FatGlobalItemJsonReader extends JsonReader<FatGlobalItem> {}
    public static final FatGlobalItemJsonReader JSON_READER = GWT.create(FatGlobalItemJsonReader.class);

    public interface FatGlobalItemJsonWriter extends JsonWriter<FatGlobalItem> {}
    public static final FatGlobalItemJsonWriter JSON_WRITER = GWT.create(FatGlobalItemJsonWriter.class);
    // @formatter:on

    // ------------------------------------------------------------- xml reader

    // @formatter:off
    public interface FatGlobalItemXmlReader extends XmlReader<FatGlobalItem> {}
    public static final FatGlobalItemXmlReader XML_READER = GWT.create(FatGlobalItemXmlReader.class);

    public interface FatGlobalItemXmlWriter extends XmlWriter<FatGlobalItem> {}
    public static final FatGlobalItemXmlWriter XML_WRITER = GWT.create(FatGlobalItemXmlWriter.class);
    // @formatter:on

    // ------------------------------------------ primitives and simple objects

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

    @JsonField(format = "dd.MM.yyyy")
    @XmlField(format = "dd.MM.yyyy")
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

    @JsonField("customNameForStringAttribute")
    @XmlField("string/@attribute")
    public String stringAttribute;

    // --------------------------------------------------------- nested objects

    @JsonField
    @XmlField
    public FatGlobalItem fatGlobalItem;

    @JsonField
    @XmlField
    public SkinnyNestedItem skinnyNestedItem;

    // ----------------------------------------------------------------- arrays

    @JsonField
    @XmlField
    public int[] arrayOfIntegerPrimitives;

    @JsonField
    @XmlField
    public Integer[] arrayOfIntegerObjects;

    @JsonField
    @XmlField
    public String[] arrayOfStrings;

    @JsonField
    @XmlField("arrayOfFatGlobalItems/fatGlobalItem")
    public FatGlobalItem[] arrayOfFatGlobalItems;

    @JsonField
    @XmlField("arrayOfSkinnyNestedItems/skinnyNestedItem")
    public SkinnyNestedItem[] arrayOfSkinnyNestedItems;

    // --------------------------------------------------------- invalid arrays

    @JsonField
    @XmlField
    public List<String>[] arrayOfLists;

    @JsonField
    @XmlField
    public Map<String, String>[] arrayOfMaps;

    @JsonField
    @XmlField
    public int[][] multiDimensionalIntegerPrimitiveArray;

    @JsonField
    @XmlField
    public Integer[][] multiDimensionalIntegerObjectArray;

    // ------------------------------------------------------------ collections

    @JsonField
    @XmlField
    public Collection<Integer> collectionOfIntegerObjects;

    @JsonField
    @XmlField
    public Collection<String> collectionOfStrings;

    @JsonField
    @XmlField("collectionOfFatGlobalItems/fatGlobalItem")
    public Collection<FatGlobalItem> collectionOfFatGlobalItems;

    @JsonField
    @XmlField("collectionOfSkinnyNestedItems/skinnyNestedItem")
    public Collection<SkinnyNestedItem> collectionOfSkinnyNestedItems;

    // ---------------------------------------------------- invalid collections

    @JsonField
    @XmlField
    @SuppressWarnings("rawtypes")
    public Collection untypedCollection;

    @JsonField
    @XmlField
    public Collection<String[]> collectionOfArrays;

    @JsonField
    @XmlField
    public Collection<Collection<String>> collectionOfCollections;

    @JsonField
    @XmlField
    public Collection<Map<String, String>> collectionOfMaps;

    // ------------------------------------------------------------------ lists

    @JsonField
    @XmlField
    public List<Integer> listOfIntegerObjects;

    @JsonField
    @XmlField
    public List<String> listOfStrings;

    @JsonField
    @XmlField("listOfFatGlobalItems/fatGlobalItem")
    public List<FatGlobalItem> listOfFatGlobalItems;

    @JsonField
    @XmlField("listOfSkinnyNestedItems/skinnyNestedItem")
    public List<SkinnyNestedItem> listOfSkinnyNestedItems;

    // ---------------------------------------------------------- invalid lists

    @JsonField
    @XmlField
    @SuppressWarnings("rawtypes")
    public List untypedList;

    @JsonField
    @XmlField
    public List<String[]> listOfArrays;

    @JsonField
    @XmlField
    public List<List<String>> listOfLists;

    @JsonField
    @XmlField
    public List<Map<String, String>> listOfMaps;

    // ------------------------------------------------------------------- sets

    @JsonField
    @XmlField
    public Set<Integer> setOfIntegerObjects;

    @JsonField
    @XmlField
    public Set<String> setOfStrings;

    @JsonField
    @XmlField("setOfFatGlobalItems/fatGlobalItem")
    public Set<FatGlobalItem> setOfFatGlobalItems;

    @JsonField
    @XmlField("setOfSkinnyNestedItems/skinnyNestedItem")
    public Set<SkinnyNestedItem> setOfSkinnyNestedItems;

    // ----------------------------------------------------------- invalid sets

    @XmlField
    @JsonField
    @SuppressWarnings("rawtypes")
    public Set untypedSet;

    @XmlField
    @JsonField
    public Set<String[]> setOfArrays;

    @XmlField
    @JsonField
    public Set<Set<String>> setOfSets;

    @XmlField
    @JsonField
    public Set<Map<String, String>> setOfMaps;
}
