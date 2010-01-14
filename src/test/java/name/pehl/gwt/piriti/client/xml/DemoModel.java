package name.pehl.gwt.piriti.client.xml;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gwt.core.client.GWT;

/**
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class DemoModel
{
    // ------------------------------------------------------- xml reader stuff

    interface DemoReader extends XmlReader<DemoModel>
    {
    }

    public static final DemoReader XML = GWT.create(DemoReader.class);

    // ------------------------------------------ primitives and simple objects

    @XmlField
    boolean booleanPrimitive;

    @XmlField
    Boolean booleanObject;

    @XmlField
    byte bytePrimitive;

    @XmlField
    Byte byteObject;

    @XmlField
    char characterPrimitive;

    @XmlField
    Character characterObject;

    @XmlField
    Date date;

    @XmlField
    DemoEnum demoEnum;

    @XmlField
    double doublePrimitive;

    @XmlField
    Double doubleObject;

    @XmlField
    float floatPrimitive;

    @XmlField
    Float floatObject;

    @XmlField
    int integerPrimitive;

    @XmlField
    Integer integerObject;

    @XmlField
    long longPrimitive;

    @XmlField
    Long longObject;

    @XmlField
    short shortPrimitive;

    @XmlField
    Short shortObject;

    @XmlField
    String string;

    @XmlField("string/@attribute")
    String stringAttribute;

    // --------------------------------------------------------- custom objects

    @XmlField
    DemoModel demoModel;

    @XmlField
    DemoNestedModel demoNestedModel;

    // ----------------------------------------------------------------- arrays

    @XmlField
    int[] arrayOfIntegerPrimitives;

    @XmlField
    Integer[] arrayOfIntegerObjects;

    @XmlField
    String[] arrayOfStrings;

    @XmlField("arrayOfDemoNestedModels/demoNestedModel")
    DemoNestedModel[] arrayOfDemoNestedModels;

    // --------------------------------------------------------- invalid arrays

    @XmlField
    DemoModel[] arrayOfDemoModels;

    @XmlField
    int[][] multiDimensionalIntegerPrimitiveArray;

    @XmlField
    Integer[][] multiDimensionalIntegerObjectArray;

    // ------------------------------------------------------------ collections

    @XmlField
    Collection<Integer> collectionOfIntegerObjects;

    @XmlField
    Collection<String> collectionOfStrings;

    @XmlField("collectionOfDemoNestedModels/demoNestedModel")
    Collection<DemoNestedModel> collectionOfDemoNestedModels;

    // ---------------------------------------------------- invalid collections

    @XmlField
    @SuppressWarnings("unchecked")
    Collection untypedCollection;

    @XmlField
    Collection<DemoModel> collectionOfDemoModels;

    @XmlField
    Collection<Collection<String>> collectionOfCollections;

    @XmlField
    Collection<Map<String, String>> collectionOfMaps;

    // ------------------------------------------------------------------ lists

    @XmlField
    List<Integer> listOfIntegerObjects;

    @XmlField
    List<String> listOfStrings;

    @XmlField("listOfDemoNestedModels/demoNestedModel")
    List<DemoNestedModel> listOfDemoNestedModels;

    // ---------------------------------------------------------- invalid lists

    @XmlField
    @SuppressWarnings("unchecked")
    List untypedList;

    @XmlField
    List<DemoModel> listOfDemoModels;

    @XmlField
    List<List<String>> listOfLists;

    @XmlField
    List<Map<String, String>> listOfMaps;

    // ------------------------------------------------------------------- sets

    @XmlField
    Set<Integer> setOfIntegerObjects;

    @XmlField
    Set<String> setOfStrings;

    @XmlField("setOfDemoNestedModels/demoNestedModel")
    Set<DemoNestedModel> setOfDemoNestedModels;

    // ----------------------------------------------------------- invalid sets

    @XmlField
    @SuppressWarnings("unchecked")
    Set untypedSet;

    @XmlField
    Set<DemoModel> setOfDemoModels;

    @XmlField
    Set<Set<String>> setOfSets;

    @XmlField
    Set<Map<String, String>> setOfMaps;

    // ------------------------------------------------------------------- maps

    @XmlField
    Map<String, Integer> mapOfIntegerObjects;

    @XmlField
    Map<String, String> mapOfStrings;

    @XmlField
    Map<String, DemoNestedModel> mapOfDemoNestedModels;

    // ----------------------------------------------------------- invalid maps

    @XmlField
    @SuppressWarnings("unchecked")
    Map untypedMap;

    @XmlField
    Map<String, DemoModel> mapOfDemoModels;

    @XmlField
    Map<String, Map<String, String>> mapOfMaps;

    @XmlField
    Map<String, List<String>> mapOfLists;

}
