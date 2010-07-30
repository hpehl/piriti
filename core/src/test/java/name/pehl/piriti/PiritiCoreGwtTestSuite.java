package name.pehl.piriti;

import junit.framework.Test;
import junit.framework.TestSuite;
import name.pehl.piriti.client.gwttest.animal.JsonAnimalTest;
import name.pehl.piriti.client.gwttest.animal.XmlAnimalTest;
import name.pehl.piriti.client.gwttest.book.JsonBookTest;
import name.pehl.piriti.client.gwttest.book.XmlBookTest;
import name.pehl.piriti.client.gwttest.employee.XmlEmployeeReaderTest;
import name.pehl.piriti.client.gwttest.fat.JsonFatGlobalItemReaderTest;
import name.pehl.piriti.client.gwttest.fat.XmlFatGlobalItemReaderTest;
import name.pehl.piriti.client.gwttest.lotteryticket.LotteryTicketReaderTest;
import name.pehl.piriti.client.gwttest.shop.JsonShopReaderTest;
import name.pehl.piriti.client.gwttest.shop.XmlShopReaderTest;

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

        suite.addTestSuite(JsonAnimalTest.class);
        suite.addTestSuite(XmlAnimalTest.class);

        suite.addTestSuite(JsonBookTest.class);
        suite.addTestSuite(XmlBookTest.class);

        suite.addTestSuite(XmlEmployeeReaderTest.class);

        suite.addTestSuite(JsonFatGlobalItemReaderTest.class);
        suite.addTestSuite(XmlFatGlobalItemReaderTest.class);

        suite.addTestSuite(LotteryTicketReaderTest.class);

        suite.addTestSuite(JsonShopReaderTest.class);
        suite.addTestSuite(XmlShopReaderTest.class);

        return suite;
    }
}
