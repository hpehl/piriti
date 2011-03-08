package name.pehl.piriti.client.gwttest.escape;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 1454 $
 */
public interface RefugeeResources extends ClientBundle
{
    // -------------------------------------------------------------- constants

    String JSON_STRING = "A \"quoted\" string with \u00fbnicode, control characters like \n\t\r and a slash: \"\\\"";

    // ------------------------------------------------------- deferred binding

    RefugeeResources INSTANCE = GWT.create(RefugeeResources.class);


    @Source("refugee.json")
    public TextResource refugeeJson();


    @Source("refugee.xml")
    public TextResource refugeeXml();
}
