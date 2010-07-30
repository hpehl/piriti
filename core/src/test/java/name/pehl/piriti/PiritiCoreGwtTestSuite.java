package name.pehl.piriti;

import junit.framework.Test;
import junit.framework.TestSuite;
import name.pehl.piriti.client.gwttest.animal.JsonAnimalTest;
import name.pehl.piriti.client.gwttest.animal.XmlAnimalTest;
import name.pehl.piriti.client.gwttest.book.JsonBookTest;
import name.pehl.piriti.client.gwttest.book.XmlBookTest;
import name.pehl.piriti.client.gwttest.employee.XmlEmployeeTest;
import name.pehl.piriti.client.gwttest.fat.JsonFatGlobalItemTest;
import name.pehl.piriti.client.gwttest.fat.XmlFatGlobalItemTest;
import name.pehl.piriti.client.gwttest.lotteryticket.LotteryTicketTest;
import name.pehl.piriti.client.gwttest.shop.XmlShopTest;
import name.pehl.piriti.client.gwttest.simple.JsonAmoebaTest;

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

        suite.addTestSuite(XmlEmployeeTest.class);

        suite.addTestSuite(JsonFatGlobalItemTest.class);
        suite.addTestSuite(XmlFatGlobalItemTest.class);

        suite.addTestSuite(LotteryTicketTest.class);

        // TODO Fix test failure
        // suite.addTestSuite(JsonShopTest.class);
        suite.addTestSuite(XmlShopTest.class);

        suite.addTestSuite(JsonAmoebaTest.class);

        return suite;
    }
}
