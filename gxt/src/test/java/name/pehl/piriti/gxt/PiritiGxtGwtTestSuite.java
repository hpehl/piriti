package name.pehl.piriti.gxt;

import junit.framework.Test;
import junit.framework.TestSuite;
import name.pehl.piriti.gxt.client.gwttest.external.JsonShopTest;
import name.pehl.piriti.gxt.client.gwttest.external.XmlShopTest;
import name.pehl.piriti.gxt.client.gwttest.inheritance.JsonAnimalTest;
import name.pehl.piriti.gxt.client.gwttest.inheritance.XmlAnimalTest;
import name.pehl.piriti.gxt.client.gwttest.references.JsonBookTest;
import name.pehl.piriti.gxt.client.gwttest.references.XmlBookTest;
import name.pehl.piriti.gxt.client.gwttest.types.JsonFatGlobalItemTest;
import name.pehl.piriti.gxt.client.gwttest.types.XmlFatGlobalItemTest;

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
