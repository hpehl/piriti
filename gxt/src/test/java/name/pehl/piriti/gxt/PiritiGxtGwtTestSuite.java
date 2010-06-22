package name.pehl.piriti.gxt;

import junit.framework.Test;
import junit.framework.TestSuite;
import name.pehl.piriti.gxt.client.gwttest.book.JsonBookReaderTest;
import name.pehl.piriti.gxt.client.gwttest.book.XmlBookReaderTest;
import name.pehl.piriti.gxt.client.gwttest.fat.JsonFatGlobalItemReaderTest;
import name.pehl.piriti.gxt.client.gwttest.fat.XmlFatGlobalItemReaderTest;

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
