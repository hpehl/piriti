package name.pehl.piriti;

import name.pehl.piriti.client.gwttest.ticketgoose.TicketgooseTest;
import junit.framework.Test;
import junit.framework.TestSuite;

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

        suite.addTestSuite(TicketgooseTest.class);

        return suite;
    }
}
