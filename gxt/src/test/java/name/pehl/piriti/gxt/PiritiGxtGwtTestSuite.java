package name.pehl.piriti.gxt;

import junit.framework.Test;
import junit.framework.TestSuite;
import name.pehl.piriti.gxt.client.external.JsonShopTest;
import name.pehl.piriti.gxt.client.external.XmlShopTest;
import name.pehl.piriti.gxt.client.inheritance.JsonAnimalTest;
import name.pehl.piriti.gxt.client.inheritance.XmlAnimalTest;
import name.pehl.piriti.gxt.client.references.JsonBookTest;
import name.pehl.piriti.gxt.client.references.XmlBookTest;
import name.pehl.piriti.gxt.client.types.JsonFatGlobalItemTest;
import name.pehl.piriti.gxt.client.types.XmlFatGlobalItemTest;

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
