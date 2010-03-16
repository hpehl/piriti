package name.pehl.piriti.client.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import name.pehl.piriti.client.json.JsonField;
import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.client.xml.XmlField;
import name.pehl.piriti.client.xml.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 131 $
 */
public class Model
{
    // ------------------------------------------------------------- xml reader

    public interface ModelXmlReader extends XmlReader<Model>
    {
    }

    public static final ModelXmlReader XML = GWT.create(ModelXmlReader.class);

    // ------------------------------------------------------------ json reader

    public interface ModelJsonReader extends JsonReader<Model>
    {
    }

    public static final ModelJsonReader JSON = GWT.create(ModelJsonReader.class);

    // ------------------------------------------ primitives and simple objects

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

    // --------------------------------------------------------- custom objects

    @XmlField
    @JsonField
    public Model model;

    @XmlField
    @JsonField
    public NestedModel nestedModel;

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

    @XmlField("arrayOfNestedModels/nestedModel")
    @JsonField
    public NestedModel[] arrayOfNestedModels;

    // --------------------------------------------------------- invalid arrays

    @XmlField
    @JsonField
    public Model[] arrayOfModels;

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

    @XmlField("collectionOfNestedModels/nestedModel")
    @JsonField
    public Collection<NestedModel> collectionOfNestedModels;

    // ---------------------------------------------------- invalid collections

    @XmlField
    @JsonField
    public @SuppressWarnings("unchecked")
    Collection untypedCollection;

    @XmlField
    @JsonField
    public Collection<Model> collectionOfModels;

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

    @XmlField("listOfNestedModels/nestedModel")
    @JsonField
    public List<NestedModel> listOfNestedModels;

    // ---------------------------------------------------------- invalid lists

    @XmlField
    @JsonField
    public @SuppressWarnings("unchecked")
    List untypedList;

    @XmlField
    @JsonField
    public List<Model> listOfModels;

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

    @XmlField("setOfNestedModels/nestedModel")
    @JsonField
    public Set<NestedModel> setOfNestedModels;

    // ----------------------------------------------------------- invalid sets

    @XmlField
    @JsonField
    public @SuppressWarnings("unchecked")
    Set untypedSet;

    @XmlField
    @JsonField
    public Set<Model> setOfModels;

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
