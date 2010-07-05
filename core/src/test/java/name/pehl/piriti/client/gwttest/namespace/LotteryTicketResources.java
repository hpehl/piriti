package name.pehl.piriti.client.gwttest.namespace;

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

    String DNS = "dns"; // defaul namespace prefix
    String DEFAULT_NS = "xmlns:" + DNS + "=\"http://code.google.com/p/piriti\" "
            + "xmlns:foo=\"http://code.google.com/p/piriti/foo\" "
            + "xmlns:bar=\"http://code.google.com/p/piriti/bar\"";
    String NO_DEFAULT_NS = "xmlns:foo=\"http://code.google.com/p/piriti/foo\" "
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


    @Source("lotteryTicketDefaultNs.xml")
    public TextResource lotteryTicketDefaultNs();


    @Source("lotteryTicketNoDefaultNs.xml")
    public TextResource lotteryTicketNoDefaultNs();


    @Source("lotteryTicketDefaultAndNestedNs.xml")
    public TextResource lotteryTicketDefaultAndNestedNs();


    @Source("lotteryTicketNoDefaultAndNestedNs.xml")
    public TextResource lotteryTicketNoDefaultAndNestedNs();
}
