package name.pehl.piriti.client.gwttest.types;

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

import name.pehl.piriti.client.json.Json;
import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.client.json.JsonWriter;
import name.pehl.piriti.client.xml.Xml;
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

    @Json
    @Xml
    public boolean booleanPrimitive;

    @Json
    @Xml
    public Boolean booleanObject;

    @Json
    @Xml
    public byte bytePrimitive;

    @Json
    @Xml
    public Byte byteObject;

    @Json
    @Xml
    public char characterPrimitive;

    @Json
    @Xml
    public Character characterObject;

    @Json(format = "dd.MM.yyyy")
    @Xml(format = "dd.MM.yyyy")
    public Date date;

    @Json
    @Xml
    public java.sql.Date sqlDate;

    @Json
    @Xml
    public java.sql.Time time;

    @Json
    @Xml
    public java.sql.Timestamp timestamp;

    @Json
    @Xml
    public Amount amount;

    @Json
    @Xml
    public double doublePrimitive;

    @Json
    @Xml
    public Double doubleObject;

    @Json
    @Xml
    public float floatPrimitive;

    @Json
    @Xml
    public Float floatObject;

    @Json
    @Xml
    public int integerPrimitive;

    @Json
    @Xml
    public Integer integerObject;

    @Json
    @Xml
    public long longPrimitive;

    @Json
    @Xml
    public Long longObject;

    @Json
    @Xml
    public short shortPrimitive;

    @Json
    @Xml
    public Short shortObject;

    @Json
    @Xml
    public String string;

    @Json("customNameForStringAttribute")
    @Xml("string/@attribute")
    public String stringAttribute;

    // --------------------------------------------------------- nested objects

    @Json
    @Xml
    public FatGlobalItem fatGlobalItem;

    @Json
    @Xml
    public SkinnyNestedItem skinnyNestedItem;

    // ----------------------------------------------------------------- arrays

    @Json
    @Xml
    public int[] arrayOfIntegerPrimitives;

    @Json
    @Xml
    public Integer[] arrayOfIntegerObjects;

    @Json
    @Xml
    public String[] arrayOfStrings;

    @Json
    @Xml("arrayOfFatGlobalItems/fatGlobalItem")
    public FatGlobalItem[] arrayOfFatGlobalItems;

    @Json
    @Xml("arrayOfSkinnyNestedItems/skinnyNestedItem")
    public SkinnyNestedItem[] arrayOfSkinnyNestedItems;

    // --------------------------------------------------------- invalid arrays

    @Json
    @Xml
    public List<String>[] arrayOfLists;

    @Json
    @Xml
    public Map<String, String>[] arrayOfMaps;

    @Json
    @Xml
    public int[][] multiDimensionalIntegerPrimitiveArray;

    @Json
    @Xml
    public Integer[][] multiDimensionalIntegerObjectArray;

    // ------------------------------------------------------------ collections

    @Json
    @Xml
    public Collection<Integer> collectionOfIntegerObjects;

    @Json
    @Xml
    public Collection<String> collectionOfStrings;

    @Json
    @Xml("collectionOfFatGlobalItems/fatGlobalItem")
    public Collection<FatGlobalItem> collectionOfFatGlobalItems;

    @Json
    @Xml("collectionOfSkinnyNestedItems/skinnyNestedItem")
    public Collection<SkinnyNestedItem> collectionOfSkinnyNestedItems;

    // ---------------------------------------------------- invalid collections

    @Json
    @Xml
    @SuppressWarnings("rawtypes")
    public Collection untypedCollection;

    @Json
    @Xml
    public Collection<String[]> collectionOfArrays;

    @Json
    @Xml
    public Collection<Collection<String>> collectionOfCollections;

    @Json
    @Xml
    public Collection<Map<String, String>> collectionOfMaps;

    // ------------------------------------------------------------------ lists

    @Json
    @Xml
    public List<Integer> listOfIntegerObjects;

    @Json
    @Xml
    public List<String> listOfStrings;

    @Json
    @Xml
    public ArrayList<String> arrayListOfStrings;

    @Json
    @Xml
    public LinkedList<String> linkedListOfStrings;

    @Json
    @Xml("listOfFatGlobalItems/fatGlobalItem")
    public List<FatGlobalItem> listOfFatGlobalItems;

    @Json
    @Xml("listOfSkinnyNestedItems/skinnyNestedItem")
    public List<SkinnyNestedItem> listOfSkinnyNestedItems;

    // ---------------------------------------------------------- invalid lists

    @Json
    @Xml
    @SuppressWarnings("rawtypes")
    public List untypedList;

    @Json
    @Xml
    public List<String[]> listOfArrays;

    @Json
    @Xml
    public List<List<String>> listOfLists;

    @Json
    @Xml
    public List<Map<String, String>> listOfMaps;

    // ------------------------------------------------------------------- sets

    @Json
    @Xml
    public Set<Integer> setOfIntegerObjects;

    @Json
    @Xml
    public Set<String> setOfStrings;

    @Json
    @Xml
    public HashSet<String> hashSetOfStrings;

    @Json
    @Xml
    public LinkedHashSet<String> linkedHashSetOfStrings;

    @Json
    @Xml
    public TreeSet<String> treeSetOfStrings;

    @Json
    @Xml("setOfFatGlobalItems/fatGlobalItem")
    public Set<FatGlobalItem> setOfFatGlobalItems;

    @Json
    @Xml("setOfSkinnyNestedItems/skinnyNestedItem")
    public Set<SkinnyNestedItem> setOfSkinnyNestedItems;

    // ----------------------------------------------------------- invalid sets

    @Xml
    @Json
    @SuppressWarnings("rawtypes")
    public Set untypedSet;

    @Xml
    @Json
    public Set<String[]> setOfArrays;

    @Xml
    @Json
    public Set<Set<String>> setOfSets;

    @Xml
    @Json
    public Set<Map<String, String>> setOfMaps;
}
