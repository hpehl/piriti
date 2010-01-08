package name.pehl.gwt.piriti.client.xml;

import java.util.Date;

import name.pehl.gwt.piriti.client.xml.XmlField;
import name.pehl.gwt.piriti.client.xml.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $LastChangedBy:$ 
 * @version $LastChangedRevision:$ 
 */
public class DemoNestedModel
{
    // ------------------------------------------------------- xml reader stuff

    interface DemoNestedReader extends XmlReader<DemoNestedModel>
    {
    }

    public static final DemoNestedReader SOME_FANCY_NAME = GWT.create(DemoNestedReader.class);

    // -------------------------------------------------------- private members

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
}
