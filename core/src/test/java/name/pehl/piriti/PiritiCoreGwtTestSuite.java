package name.pehl.piriti;

import junit.framework.Test;
import junit.framework.TestSuite;
import name.pehl.piriti.client.gwttest.constraints.JsonAmoebaReaderTest;
import name.pehl.piriti.client.gwttest.constraints.JsonAmoebaWriterTest;
import name.pehl.piriti.client.gwttest.constraints.XmlAmoebaReaderTest;
import name.pehl.piriti.client.gwttest.converter.JsonBackAndForthTest;
import name.pehl.piriti.client.gwttest.converter.XmlBackAndForthTest;
import name.pehl.piriti.client.gwttest.external.JsonShopTest;
import name.pehl.piriti.client.gwttest.external.XmlShopTest;
import name.pehl.piriti.client.gwttest.inheritance.JsonAnimalTest;
import name.pehl.piriti.client.gwttest.inheritance.XmlAnimalTest;
import name.pehl.piriti.client.gwttest.namespaces.LotteryTicketTest;
import name.pehl.piriti.client.gwttest.references.JsonBookTest;
import name.pehl.piriti.client.gwttest.references.XmlBookTest;
import name.pehl.piriti.client.gwttest.references.id.XmlEmployeeTest;
import name.pehl.piriti.client.gwttest.types.JsonFatGlobalItemTest;
import name.pehl.piriti.client.gwttest.types.XmlFatGlobalItemTest;

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

        suite.addTestSuite(JsonBackAndForthTest.class);
        suite.addTestSuite(XmlBackAndForthTest.class);

        suite.addTestSuite(JsonBookTest.class);
        suite.addTestSuite(XmlBookTest.class);

        suite.addTestSuite(XmlEmployeeTest.class);

        suite.addTestSuite(JsonFatGlobalItemTest.class);
        suite.addTestSuite(XmlFatGlobalItemTest.class);

        suite.addTestSuite(LotteryTicketTest.class);

        suite.addTestSuite(JsonShopTest.class);
        suite.addTestSuite(XmlShopTest.class);

        suite.addTestSuite(JsonAmoebaReaderTest.class);
        suite.addTestSuite(JsonAmoebaWriterTest.class);
        suite.addTestSuite(XmlAmoebaReaderTest.class);

        return suite;
    }
}
