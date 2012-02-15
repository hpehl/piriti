package name.pehl.piriti.client.events;

import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import com.google.gwt.core.client.GWT;

public class Person
{
    // --------------------------------------------------- json reader / writer

    // @formatter:off
    public interface PersonJsonWriter extends JsonWriter<Person> {}
    public static final PersonJsonWriter JSON_WRITER = GWT.create(PersonJsonWriter.class);

    public interface PersonJsonReader extends JsonReader<Person> {}
    public static final PersonJsonReader JSON_READER = GWT.create(PersonJsonReader.class);

    // ---------------------------------------------------- xml reader / writer

    public interface PersonXmlReader extends XmlReader<Person> {}
    public static final PersonXmlReader XML_READER = GWT.create(PersonXmlReader.class);

    public interface PersonXmlWriter extends XmlWriter<Person> {}
    public static final PersonXmlWriter XML_WRITER = GWT.create(PersonXmlWriter.class);
    // @formatter:on

    String firstname;
    String surname;


    public Person()
    {
        this(null, null);
    }


    public Person(String firstname, String surname)
    {
        super();
        this.firstname = firstname;
        this.surname = surname;
    }
}
