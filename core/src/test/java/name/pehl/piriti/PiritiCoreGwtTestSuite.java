package name.pehl.piriti;

import junit.framework.Test;
import junit.framework.TestSuite;
import name.pehl.piriti.client.gwttest.book.JsonBookReaderTest;
import name.pehl.piriti.client.gwttest.book.XmlBookReaderTest;
import name.pehl.piriti.client.gwttest.employee.XmlEmployeeReaderTest;
import name.pehl.piriti.client.gwttest.fat.JsonFatGlobalItemReaderTest;
import name.pehl.piriti.client.gwttest.fat.XmlFatGlobalItemReaderTest;

import com.google.gwt.junit.tools.GWTTestSuite;

/**
 * @author $Author$
 * @version $Date$ $Revision: 597
 *          $
 */
public class PiritiCoreGwtTestSuite extends GWTTestSuite
{
    public static Test suite()
    {
        TestSuite suite = new TestSuite("GWT tests for piriti-core");

        // suite.addTestSuite(NodeTest.class);

        suite.addTestSuite(JsonBookReaderTest.class);
        suite.addTestSuite(XmlBookReaderTest.class);

        suite.addTestSuite(XmlEmployeeReaderTest.class);

        suite.addTestSuite(JsonFatGlobalItemReaderTest.class);
        suite.addTestSuite(XmlFatGlobalItemReaderTest.class);

        // suite.addTestSuite(LotteryTicketReaderTest.class);

        return suite;
    }
}
