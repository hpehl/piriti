package name.pehl.piriti;

import junit.framework.Test;
import junit.framework.TestSuite;
import name.pehl.piriti.client.gwttest.book.json.JsonBookReaderTest;
import name.pehl.piriti.client.gwttest.book.xml.XmlBookReaderTest;
import name.pehl.piriti.client.gwttest.employee.xml.XmlEmployeeReaderTest;
import name.pehl.piriti.client.gwttest.fat.json.JsonFatGlobalItemReaderTest;
import name.pehl.piriti.client.gwttest.fat.xml.XmlFatGlobalItemReaderTest;

import com.google.gwt.junit.tools.GWTTestSuite;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public class PiritiCoreGwtTestSuite extends GWTTestSuite
{
    public static Test suite()
    {
        TestSuite suite = new TestSuite("GWT tests for piriti-core");

        suite.addTestSuite(JsonBookReaderTest.class);
        suite.addTestSuite(XmlBookReaderTest.class);

        suite.addTestSuite(XmlEmployeeReaderTest.class);

        suite.addTestSuite(JsonFatGlobalItemReaderTest.class);
        suite.addTestSuite(XmlFatGlobalItemReaderTest.class);

        return suite;
    }
}
