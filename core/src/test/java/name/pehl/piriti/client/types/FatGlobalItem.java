package name.pehl.piriti.client.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import name.pehl.piriti.commons.client.Format;
import name.pehl.piriti.commons.client.Mapping;
import name.pehl.piriti.commons.client.Mappings;
import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import com.google.gwt.core.client.GWT;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 131 $
 */
@SuppressWarnings("rawtypes")
public class FatGlobalItem
{
    // ------------------------------------------------------------ json reader

    // @formatter:off
    @Mappings(@Mapping(value = "stringAttribute", path = "customNameForStringAttribute"))
    public interface FatGlobalItemJsonReader extends JsonReader<FatGlobalItem> {}
    public static final FatGlobalItemJsonReader JSON_READER = GWT.create(FatGlobalItemJsonReader.class);

    public interface FatGlobalItemJsonWriter extends JsonWriter<FatGlobalItem> {}
    public static final FatGlobalItemJsonWriter JSON_WRITER = GWT.create(FatGlobalItemJsonWriter.class);
    // @formatter:on

    // ------------------------------------------------------------- xml reader

    // @formatter:off
    @Mappings({@Mapping(value = "stringAttribute", path = "string/@attribute"),
        @Mapping(value = "arrayOfFatGlobalItems", path = "arrayOfFatGlobalItems/fatGlobalItem"),
        @Mapping(value = "arrayOfSkinnyNestedItems", path = "arrayOfSkinnyNestedItems/skinnyNestedItem"),
        @Mapping(value = "collectionOfFatGlobalItems", path = "collectionOfFatGlobalItems/fatGlobalItem"),
        @Mapping(value = "collectionOfSkinnyNestedItems", path = "collectionOfSkinnyNestedItems/skinnyNestedItem"),
        @Mapping(value = "listOfFatGlobalItems", path = "listOfFatGlobalItems/fatGlobalItem"),
        @Mapping(value = "listOfSkinnyNestedItems", path = "listOfSkinnyNestedItems/skinnyNestedItem"),
        @Mapping(value = "setOfFatGlobalItems", path = "setOfFatGlobalItems/fatGlobalItem"),
        @Mapping(value = "setOfSkinnyNestedItems", path = "setOfSkinnyNestedItems/skinnyNestedItem")})
    public interface FatGlobalItemXmlReader extends XmlReader<FatGlobalItem> {}
    public static final FatGlobalItemXmlReader XML_READER = GWT.create(FatGlobalItemXmlReader.class);

    public interface FatGlobalItemXmlWriter extends XmlWriter<FatGlobalItem> {}
    public static final FatGlobalItemXmlWriter XML_WRITER = GWT.create(FatGlobalItemXmlWriter.class);
    // @formatter:on

    // ------------------------------------------ primitives and simple objects

    public boolean booleanPrimitive;
    public Boolean booleanObject;
    public byte bytePrimitive;
    public Byte byteObject;
    public char characterPrimitive;
    public Character characterObject;
    @Format("dd.MM.yyyy")
    public Date date;
    public java.sql.Date sqlDate;
    public java.sql.Time time;
    public java.sql.Timestamp timestamp;
    public Amount amount;
    public double doublePrimitive;
    public Double doubleObject;
    public float floatPrimitive;
    public Float floatObject;
    public int integerPrimitive;
    public Integer integerObject;
    public long longPrimitive;
    public Long longObject;
    public short shortPrimitive;
    public Short shortObject;
    public String string;
    public String stringAttribute;

    // --------------------------------------------------------- nested objects

    public FatGlobalItem fatGlobalItem;
    public SkinnyNestedItem skinnyNestedItem;

    // ----------------------------------------------------------------- arrays

    public int[] arrayOfIntegerPrimitives;
    public Integer[] arrayOfIntegerObjects;
    public String[] arrayOfStrings;
    public FatGlobalItem[] arrayOfFatGlobalItems;
    public SkinnyNestedItem[] arrayOfSkinnyNestedItems;

    // --------------------------------------------------------- invalid arrays

    public List<String>[] arrayOfLists;
    public Map<String, String>[] arrayOfMaps;
    public int[][] multiDimensionalIntegerPrimitiveArray;
    public Integer[][] multiDimensionalIntegerObjectArray;

    // ------------------------------------------------------------ collections

    public Collection<Integer> collectionOfIntegerObjects;
    public Collection<String> collectionOfStrings;
    public Collection<FatGlobalItem> collectionOfFatGlobalItems;
    public Collection<SkinnyNestedItem> collectionOfSkinnyNestedItems;

    // ---------------------------------------------------- invalid collections

    public Collection untypedCollection;
    public Collection<String[]> collectionOfArrays;
    public Collection<Collection<String>> collectionOfCollections;
    public Collection<Map<String, String>> collectionOfMaps;

    // ------------------------------------------------------------------ lists

    public List<Integer> listOfIntegerObjects;
    public List<String> listOfStrings;
    public ArrayList<String> arrayListOfStrings;
    public LinkedList<String> linkedListOfStrings;
    public List<FatGlobalItem> listOfFatGlobalItems;
    public List<SkinnyNestedItem> listOfSkinnyNestedItems;

    // ---------------------------------------------------------- invalid lists

    public List untypedList;
    public List<String[]> listOfArrays;
    public List<List<String>> listOfLists;
    public List<Map<String, String>> listOfMaps;

    // ------------------------------------------------------------------- sets

    public Set<Integer> setOfIntegerObjects;
    public Set<String> setOfStrings;
    public HashSet<String> hashSetOfStrings;
    public LinkedHashSet<String> linkedHashSetOfStrings;
    public TreeSet<String> treeSetOfStrings;
    public Set<FatGlobalItem> setOfFatGlobalItems;
    public Set<SkinnyNestedItem> setOfSkinnyNestedItems;

    // ----------------------------------------------------------- invalid sets

    public Set untypedSet;
    public Set<String[]> setOfArrays;
    public Set<Set<String>> setOfSets;
    public Set<Map<String, String>> setOfMaps;
}
