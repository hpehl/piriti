package name.pehl.gwt.piriti.client.xml;

import java.util.Date;

import name.pehl.gwt.piriti.client.converter.DateConverter;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Text;
import com.google.gwt.xml.client.XMLParser;

/**
 * @author $LastChangedBy$ 
 * @version $LastChangedRevision$ 
 */
public final class DemoXmlFactory
{
    private static final Date MY_BIRTHDAY = new Date(115813353000l);


    private DemoXmlFactory()
    {
    }


    public static Document createDemoModelDocument(String elementName)
    {
        Document document = XMLParser.createDocument();
        document.appendChild(createDemoModelElement(elementName));
        return document;
    }


    public static Element createDemoModelElement(String elementName)
    {
        Document document = XMLParser.createDocument();
        Element demoModel = document.createElement(elementName);

        createElementAndAppend(document, demoModel, "booleanPrimitive", "true");
        createElementAndAppend(document, demoModel, "booleanObject", "true");
        createElementAndAppend(document, demoModel, "bytePrimitive", "1");
        createElementAndAppend(document, demoModel, "byteObject", "2");
        createElementAndAppend(document, demoModel, "characterPrimitive", "a");
        createElementAndAppend(document, demoModel, "characterObject", "b");
        createElementAndAppend(document, demoModel, "date", DateTimeFormat.getFormat(DateConverter.DEFAULT_FORMAT)
                .format(MY_BIRTHDAY));
        createElementAndAppend(document, demoModel, "demoEnum", "DREI");
        createElementAndAppend(document, demoModel, "doublePrimitive", "4.5");
        createElementAndAppend(document, demoModel, "doubleObject", "6.7");
        createElementAndAppend(document, demoModel, "floatPrimitive", "8.9");
        createElementAndAppend(document, demoModel, "floatObject", "10.11");
        createElementAndAppend(document, demoModel, "integerPrimitive", "12");
        createElementAndAppend(document, demoModel, "integerObject", "13");
        createElementAndAppend(document, demoModel, "longPrimitive", "14");
        createElementAndAppend(document, demoModel, "longObject", "15");
        createElementAndAppend(document, demoModel, "shortPrimitive", "16");
        createElementAndAppend(document, demoModel, "shortObject", "17");
        Element stringElement = createElementAndAppend(document, demoModel, "string", "achtzehn");
        stringElement.setAttribute("attribute", "neunzehn");

        Element demoNestedModel = createDemoNestedModelElement("demoNestedModel");
        demoModel.appendChild(demoNestedModel);

        return demoModel;
    }


    public static Document createDemoNestedModelDocument(String elementName)
    {
        Document document = XMLParser.createDocument();
        document.appendChild(createDemoNestedModelElement(elementName));
        return document;
    }


    public static Element createDemoNestedModelElement(String elementName)
    {
        Document document = XMLParser.createDocument();
        Element demoNestedModel = document.createElement(elementName);

        createElementAndAppend(document, demoNestedModel, "booleanPrimitive", "true");
        createElementAndAppend(document, demoNestedModel, "booleanObject", "true");
        createElementAndAppend(document, demoNestedModel, "bytePrimitive", "1");
        createElementAndAppend(document, demoNestedModel, "byteObject", "2");
        createElementAndAppend(document, demoNestedModel, "characterPrimitive", "a");
        createElementAndAppend(document, demoNestedModel, "characterObject", "b");
        createElementAndAppend(document, demoNestedModel, "date", DateTimeFormat
                .getFormat(DateConverter.DEFAULT_FORMAT).format(MY_BIRTHDAY));
        createElementAndAppend(document, demoNestedModel, "demoEnum", "DREI");
        createElementAndAppend(document, demoNestedModel, "doublePrimitive", "4.5");
        createElementAndAppend(document, demoNestedModel, "doubleObject", "6.7");
        createElementAndAppend(document, demoNestedModel, "floatPrimitive", "8.9");
        createElementAndAppend(document, demoNestedModel, "floatObject", "10.11");
        createElementAndAppend(document, demoNestedModel, "integerPrimitive", "12");
        createElementAndAppend(document, demoNestedModel, "integerObject", "13");
        createElementAndAppend(document, demoNestedModel, "longPrimitive", "14");
        createElementAndAppend(document, demoNestedModel, "longObject", "15");
        createElementAndAppend(document, demoNestedModel, "shortPrimitive", "16");
        createElementAndAppend(document, demoNestedModel, "shortObject", "17");
        Element stringElement = createElementAndAppend(document, demoNestedModel, "string", "achtzehn");
        stringElement.setAttribute("attribute", "neunzehn");

        return demoNestedModel;
    }


    private static Element createElementAndAppend(Document document, Element parent, String elementName, String value)
    {
        Element element = document.createElement(elementName);
        Text text = document.createTextNode(value);
        element.appendChild(text);
        parent.appendChild(element);
        return element;
    }
}
