package name.pehl.piriti;

import junit.framework.Test;
import junit.framework.TestSuite;
import name.pehl.piriti.client.gwttest.specialjsonkey.RequestTest;
import name.pehl.piriti.client.gwttest.ticketgoose.TicketgooseTest;

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

        suite.addTestSuite(RequestTest.class);
        suite.addTestSuite(TicketgooseTest.class);

        return suite;
    }
}
