package name.pehl.piriti.client.gwttest.xml;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */

public interface XmlParserResources extends ClientBundle
{
    // -------------------------------------------------------------- constants

    String DOCUMENT_NAME = "#document";
    String ROOT_NAME = "swissArmyKnife";
    String ASIN = "B001DZTJRQ";

    // ------------------------------------------------------- deferred binding

    XmlParserResources INSTANCE = GWT.create(XmlParserResources.class);


    @Source("swissArmyKnife.xml")
    public TextResource swissArmyKnife();
}
