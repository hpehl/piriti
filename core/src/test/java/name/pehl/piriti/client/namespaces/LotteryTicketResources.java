package name.pehl.piriti.client.namespaces;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */

public interface LotteryTicketResources extends ClientBundle
{
    // ------------------------------------------------------------- namespaces

    String DNS_PREFIX = "dns"; // defaul namespace prefix
    String DNS = "xmlns:" + DNS_PREFIX + "=\"http://code.google.com/p/piriti\" "
            + "xmlns:foo=\"http://code.google.com/p/piriti/foo\" "
            + "xmlns:bar=\"http://code.google.com/p/piriti/bar\"";
    String NS = "xmlns:foo=\"http://code.google.com/p/piriti/foo\" "
            + "xmlns:bar=\"http://code.google.com/p/piriti/bar\"";

    // -------------------------------------------------------------- constants

    Date DATE = new Date(1286661600000l);
    int AGE = 42;
    String FIRSTNAME = "Homer";
    String SURNAME = "Simpson";
    String ADDRESS = "24, evergreen terrace, springfield";
    String ADDRESS_TYPE = "home";
    String GAME = "6x49";
    Integer[] NUMBERS = {4, 8, 15, 16, 23, 42};

    // ------------------------------------------------------- deferred binding

    LotteryTicketResources INSTANCE = GWT.create(LotteryTicketResources.class);


    @Source("lotteryTicketNs.xml")
    public TextResource lotteryTicketNs();
    
    
    @Source("lotteryTicketDns.xml")
    public TextResource lotteryTicketDns();
    
    
    @Source("lotteryTicketNns.xml")
    public TextResource lotteryTicketNns();


    @Source("lotteryTicketDnsNns.xml")
    public TextResource lotteryTicketDnsNns();
}
