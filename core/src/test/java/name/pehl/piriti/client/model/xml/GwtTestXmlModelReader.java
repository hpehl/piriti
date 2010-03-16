package name.pehl.piriti.client.model.xml;

import name.pehl.piriti.client.model.Model;
import name.pehl.piriti.client.model.ModelTestCase;

import com.google.gwt.xml.client.Document;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 133 $
 */
public class GwtTestXmlModelReader extends ModelTestCase
{
    public void testRead()
    {
        Document document = XmlModelFactory.createModel();
        Model model = Model.XML.read(document);
        assertModel(model);
    }
}
