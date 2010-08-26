package name.pehl.piriti.client.gwttest.ticketgoose;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */

public interface TicketgooseResources extends ClientBundle
{
    TicketgooseResources INSTANCE = GWT.create(TicketgooseResources.class);


    @Source("ticketgoose.xml")
    public TextResource ticketgooseXml();
}
