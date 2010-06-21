package name.pehl.piriti.gxt;

import junit.framework.Test;
import junit.framework.TestSuite;
import name.pehl.piriti.gxt.client.gwttest.book.json.JsonBookReaderTest;
import name.pehl.piriti.gxt.client.gwttest.book.xml.XmlBookReaderTest;
import name.pehl.piriti.gxt.client.gwttest.fat.json.JsonFatGlobalItemReaderTest;
import name.pehl.piriti.gxt.client.gwttest.fat.xml.XmlFatGlobalItemReaderTest;

import com.google.gwt.junit.tools.GWTTestSuite;

/**
 * @author $Author$
 * @version $Date$ $Revision: 589
 *          $
 */
public class PiritiGxtGwtTestSuite extends GWTTestSuite
{
    public static Test suite()
    {
        TestSuite suite = new TestSuite("GWT tests for piriti-gxt");

        suite.addTestSuite(JsonBookReaderTest.class);
        suite.addTestSuite(XmlBookReaderTest.class);

        suite.addTestSuite(JsonFatGlobalItemReaderTest.class);
        suite.addTestSuite(XmlFatGlobalItemReaderTest.class);

        return suite;
    }
}
