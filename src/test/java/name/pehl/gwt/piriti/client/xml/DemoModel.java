package name.pehl.gwt.piriti.client.xml;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import name.pehl.gwt.piriti.client.xml.XmlField;
import name.pehl.gwt.piriti.client.xml.XmlReader;

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
    String[] arrayOfStrings;

    @XmlField
    DemoModel[] arrayOfDemoModels;

    @XmlField
    DemoNestedModel[] arrayOfDemoNestedModels;

    // ------------------------------------------------------------ collections

    @XmlField
    List<String> listOfStrings;

    @XmlField
    List<DemoModel> listOfDemoModels;

    @XmlField
    List<DemoNestedModel> listOfDemoNestedModels;

    @XmlField
    Set<String> setOfStrings;

    @XmlField
    Set<DemoModel> setOfDemoModels;

    @XmlField
    Set<DemoNestedModel> setOfDemoNestedModels;

    // ------------------------------------------------------------------- maps

    @XmlField
    Map<String, String> mapOfStrings;

    @XmlField
    Map<String, DemoModel> mapOfDemoModels;

    @XmlField
    Map<String, DemoNestedModel> mapOfDemoNestedModels;
}
