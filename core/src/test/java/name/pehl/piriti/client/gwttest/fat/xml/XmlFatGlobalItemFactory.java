package name.pehl.piriti.client.gwttest.fat.xml;

import static name.pehl.piriti.client.gwttest.fat.FatGlobalItemTestCase.*;
import name.pehl.piriti.client.converter.DateConverter;
import name.pehl.piriti.client.gwttest.fat.Amount;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Text;
import com.google.gwt.xml.client.XMLParser;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 135 $
 */
public final class XmlFatGlobalItemFactory
{
    private XmlFatGlobalItemFactory()
    {
    }


    /**
     * Create FatGlobalItems with nested FatGlobalItems.
     * 
     * @return
     */
    public static Document createFatGlobalItems()
    {
        Document document = XMLParser.createDocument();
        Element items = document.createElement("fatGlobalItems");
        for (int i = 0; i < SIZE; i++)
        {
            items.appendChild(createFatGlobalItemElement(document, "fatGlobalItem", true));
        }

        document.appendChild(items);
        return document;
    }


    /**
     * Create a FatGlobalItem with nested FatGlobalItems.
     * 
     * @return
     */
    public static Document createFatGlobalItem()
    {
        Document document = XMLParser.createDocument();
        document.appendChild(createFatGlobalItemElement(document, "fatGlobalItem", true));
        return document;
    }


    /**
     * Create a FatGlobalItem. Depending on the parameter {@code
     * withNestedFatGlobalItems} nested FatGlobalItems are also generated.
     * 
     * @param withNestedFatGlobalItems
     * @return
     */
    private static Element createFatGlobalItemElement(Document document, String elementName,
            boolean withNestedFatGlobalItems)
    {
        Element fgi = document.createElement(elementName);

        // Primitives and simple objects
        createElementAndAppend(document, fgi, "booleanPrimitive", "true");
        createElementAndAppend(document, fgi, "booleanObject", "true");
        createElementAndAppend(document, fgi, "bytePrimitive", "1");
        createElementAndAppend(document, fgi, "byteObject", "2");
        createElementAndAppend(document, fgi, "characterPrimitive", "a");
        createElementAndAppend(document, fgi, "characterObject", "b");
        createElementAndAppend(document, fgi, "date", DateTimeFormat.getFormat(DateConverter.DEFAULT_FORMAT).format(
                MY_BIRTHDAY));
        createElementAndAppend(document, fgi, "amount", Amount.THREE.name());
        createElementAndAppend(document, fgi, "doublePrimitive", "4.5");
        createElementAndAppend(document, fgi, "doubleObject", "6.7");
        createElementAndAppend(document, fgi, "floatPrimitive", "8.9");
        createElementAndAppend(document, fgi, "floatObject", "10.11");
        createElementAndAppend(document, fgi, "integerPrimitive", "12");
        createElementAndAppend(document, fgi, "integerObject", "13");
        createElementAndAppend(document, fgi, "longPrimitive", "14");
        createElementAndAppend(document, fgi, "longObject", "15");
        createElementAndAppend(document, fgi, "shortPrimitive", "16");
        createElementAndAppend(document, fgi, "shortObject", "17");
        Element stringElement = createElementAndAppend(document, fgi, "string", "achtzehn");
        stringElement.setAttribute("attribute", "neunzehn");

        // Nested objects
        if (withNestedFatGlobalItems)
        {
            fgi.appendChild(createFatGlobalItemElement(document, "fatGlobalItem", false));
        }
        fgi.appendChild(createNestedModelElement(document, "skinnyNestedItem"));

        // Arrays
        createElementsAndAppend(document, fgi, "arrayOfIntegerPrimitives", "0", "1", "2");
        createElementsAndAppend(document, fgi, "arrayOfIntegerObjects", "0", "1", "2");
        createElementsAndAppend(document, fgi, "arrayOfStrings", "0", "1", "2");
        if (withNestedFatGlobalItems)
        {
            Element arrayOfFatGlobalItems = document.createElement("arrayOfFatGlobalItems");
            for (int i = 0; i < SIZE; i++)
            {
                arrayOfFatGlobalItems.appendChild(createFatGlobalItemElement(document, "fatGlobalItem", false));
            }
            fgi.appendChild(arrayOfFatGlobalItems);
        }
        Element arrayOfSkinnyNestedModels = document.createElement("arrayOfSkinnyNestedItems");
        for (int i = 0; i < SIZE; i++)
        {
            arrayOfSkinnyNestedModels.appendChild(createNestedModelElement(document, "skinnyNestedItem"));
        }
        fgi.appendChild(arrayOfSkinnyNestedModels);

        // Collections
        createElementsAndAppend(document, fgi, "collectionOfIntegerObjects", "0", "1", "2");
        createElementsAndAppend(document, fgi, "collectionOfStrings", "0", "1", "2");
        if (withNestedFatGlobalItems)
        {
            Element collectionOfFatGlobalItems = document.createElement("collectionOfFatGlobalItems");
            for (int i = 0; i < SIZE; i++)
            {
                collectionOfFatGlobalItems.appendChild(createFatGlobalItemElement(document, "fatGlobalItem", false));
            }
            fgi.appendChild(collectionOfFatGlobalItems);
        }
        Element collectionOfSkinnyNestedModels = document.createElement("collectionOfSkinnyNestedItems");
        for (int i = 0; i < SIZE; i++)
        {
            collectionOfSkinnyNestedModels.appendChild(createNestedModelElement(document, "skinnyNestedItem"));
        }
        fgi.appendChild(collectionOfSkinnyNestedModels);

        // Lists
        createElementsAndAppend(document, fgi, "listOfIntegerObjects", "0", "1", "2");
        createElementsAndAppend(document, fgi, "listOfStrings", "0", "1", "2");
        if (withNestedFatGlobalItems)
        {
            Element listOfFatGlobalItems = document.createElement("listOfFatGlobalItems");
            for (int i = 0; i < SIZE; i++)
            {
                listOfFatGlobalItems.appendChild(createFatGlobalItemElement(document, "fatGlobalItem", false));
            }
            fgi.appendChild(listOfFatGlobalItems);
        }
        Element listOfSkinnyNestedModels = document.createElement("listOfSkinnyNestedItems");
        for (int i = 0; i < SIZE; i++)
        {
            listOfSkinnyNestedModels.appendChild(createNestedModelElement(document, "skinnyNestedItem"));
        }
        fgi.appendChild(listOfSkinnyNestedModels);

        // Sets
        createElementsAndAppend(document, fgi, "setOfIntegerObjects", "0", "1", "2");
        createElementsAndAppend(document, fgi, "setOfStrings", "0", "1", "2");
        if (withNestedFatGlobalItems)
        {
            Element setOfFatGlobalItems = document.createElement("setOfFatGlobalItems");
            for (int i = 0; i < SIZE; i++)
            {
                setOfFatGlobalItems.appendChild(createFatGlobalItemElement(document, "fatGlobalItem", false));
            }
            fgi.appendChild(setOfFatGlobalItems);
        }
        Element setOfSkinnyNestedModels = document.createElement("setOfSkinnyNestedItems");
        for (int i = 0; i < SIZE; i++)
        {
            setOfSkinnyNestedModels.appendChild(createNestedModelElement(document, "skinnyNestedItem"));
        }
        fgi.appendChild(setOfSkinnyNestedModels);

        return fgi;
    }


    private static Element createNestedModelElement(Document document, String elementName)
    {
        Element nestedModel = document.createElement(elementName);

        createElementAndAppend(document, nestedModel, "booleanPrimitive", "true");
        createElementAndAppend(document, nestedModel, "booleanObject", "true");
        createElementAndAppend(document, nestedModel, "bytePrimitive", "1");
        createElementAndAppend(document, nestedModel, "byteObject", "2");
        createElementAndAppend(document, nestedModel, "characterPrimitive", "a");
        createElementAndAppend(document, nestedModel, "characterObject", "b");
        createElementAndAppend(document, nestedModel, "date", DateTimeFormat.getFormat(DateConverter.DEFAULT_FORMAT)
                .format(MY_BIRTHDAY));
        createElementAndAppend(document, nestedModel, "amount", Amount.THREE.name());
        createElementAndAppend(document, nestedModel, "doublePrimitive", "4.5");
        createElementAndAppend(document, nestedModel, "doubleObject", "6.7");
        createElementAndAppend(document, nestedModel, "floatPrimitive", "8.9");
        createElementAndAppend(document, nestedModel, "floatObject", "10.11");
        createElementAndAppend(document, nestedModel, "integerPrimitive", "12");
        createElementAndAppend(document, nestedModel, "integerObject", "13");
        createElementAndAppend(document, nestedModel, "longPrimitive", "14");
        createElementAndAppend(document, nestedModel, "longObject", "15");
        createElementAndAppend(document, nestedModel, "shortPrimitive", "16");
        createElementAndAppend(document, nestedModel, "shortObject", "17");
        Element stringElement = createElementAndAppend(document, nestedModel, "string", "achtzehn");
        stringElement.setAttribute("attribute", "neunzehn");

        return nestedModel;
    }


    private static void createElementsAndAppend(Document document, Element parent, String elementName, String... values)
    {
        if (values != null && values.length != 0)
        {
            for (String value : values)
            {
                createElementAndAppend(document, parent, elementName, value);
            }
        }
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
