package name.pehl.piriti;

import junit.framework.Test;
import junit.framework.TestSuite;
import name.pehl.piriti.client.arcbees.BlogItemTest;
import name.pehl.piriti.client.campaign.CampaignTest;
import name.pehl.piriti.client.createwith.SaveFileResultTest;
import name.pehl.piriti.client.sabajtouch.RegistryTest;
import name.pehl.piriti.client.simplejsonpath.SimpleJsonPathTest;
import name.pehl.piriti.client.ticketgoose.TicketgooseTest;

import com.google.gwt.junit.tools.GWTTestSuite;

/**
 * @author $Author$
 * @version $Date$ $Revision: 597
 *          $
 */
public class PlaygroundTestSuite extends GWTTestSuite
{
    public static Test suite()
    {
        TestSuite suite = new TestSuite("GWT tests for piriti-playground");

        suite.addTestSuite(BlogItemTest.class);
        suite.addTestSuite(CampaignTest.class);
        suite.addTestSuite(RegistryTest.class);
        suite.addTestSuite(SaveFileResultTest.class);
        suite.addTestSuite(SimpleJsonPathTest.class);
        suite.addTestSuite(TicketgooseTest.class);

        return suite;
    }
}
