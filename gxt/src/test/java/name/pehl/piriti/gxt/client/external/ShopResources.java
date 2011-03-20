package name.pehl.piriti.gxt.client.external;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 732 $
 */

public interface ShopResources extends ClientBundle
{
    ShopResources INSTANCE = GWT.create(ShopResources.class);


    @Source("name/pehl/piriti/client/external/shop.json")
    public TextResource shopJson();


    @Source("shop.xml")
    public TextResource shopXml();
}
