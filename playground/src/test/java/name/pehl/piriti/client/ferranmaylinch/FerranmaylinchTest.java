package name.pehl.piriti.client.ferranmaylinch;

import java.util.ArrayList;
import java.util.Date;

import name.pehl.piriti.client.AbstractPlaygroundTest;

public class FerranmaylinchTest extends AbstractPlaygroundTest
{
    public void testDoc()
    {
        Doc doc = new Doc();
        doc.date = new Date();

        doc.properties = new ArrayList<Property>();
        doc.properties.add(new PropertyString("name", "Yolanda"));
        doc.properties.add(new PropertyInteger("age", 29));
        doc.properties.add(new PropertyDate("date", new Date()));

        doc.generalProperties = new ArrayList<GeneralProperty>();
        doc.generalProperties.add(new GeneralProperty("name", "string", "Yolanda"));
        doc.generalProperties.add(new GeneralProperty("age", "integer", 29));
        doc.generalProperties.add(new GeneralProperty("date", "date", new Date()));

        String json = Doc.JSON_WRITER.toJson(doc);
        assertNotNull(json);

        Doc doc2 = Doc.JSON_READER.read(json);
        assertNotNull(doc2);
    }
}
