package name.pehl.piriti.client.gwttest.fat;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */

public interface FatGlobalItemResources extends ClientBundle
{
    // -------------------------------------------------------------- constants

    int SIZE = 3;
    String ITEMS = "items";
    Date MY_BIRTHDAY = new Date(115813353000l);

    // ------------------------------------------------------- deferred binding

    FatGlobalItemResources INSTANCE = GWT.create(FatGlobalItemResources.class);


    @Source("fatGlobalItem.json")
    public TextResource fatGlobalItemJson();


    @Source("fatGlobalItems.json")
    public TextResource fatGlobalItemsJson();


    @Source("fatGlobalItem.xml")
    public TextResource fatGlobalItemXml();
}
