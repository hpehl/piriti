package name.pehl.piriti.gxt;

import junit.framework.Test;
import junit.framework.TestSuite;
import name.pehl.piriti.gxt.client.gwttest.animal.JsonAnimalTest;
import name.pehl.piriti.gxt.client.gwttest.animal.XmlAnimalTest;
import name.pehl.piriti.gxt.client.gwttest.book.JsonBookTest;
import name.pehl.piriti.gxt.client.gwttest.book.XmlBookTest;
import name.pehl.piriti.gxt.client.gwttest.fat.JsonFatGlobalItemTest;
import name.pehl.piriti.gxt.client.gwttest.fat.XmlFatGlobalItemTest;
import name.pehl.piriti.gxt.client.gwttest.shop.JsonShopTest;
import name.pehl.piriti.gxt.client.gwttest.shop.XmlShopTest;

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

        suite.addTestSuite(JsonAnimalTest.class);
        suite.addTestSuite(XmlAnimalTest.class);

        suite.addTestSuite(JsonBookTest.class);
        suite.addTestSuite(XmlBookTest.class);

        suite.addTestSuite(JsonFatGlobalItemTest.class);
        suite.addTestSuite(XmlFatGlobalItemTest.class);

        suite.addTestSuite(JsonShopTest.class);
        suite.addTestSuite(XmlShopTest.class);

        return suite;
    }
}
