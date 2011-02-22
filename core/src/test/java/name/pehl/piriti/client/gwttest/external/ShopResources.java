package name.pehl.piriti.client.gwttest.external;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */

public interface ShopResources extends ClientBundle
{
    ShopResources INSTANCE = GWT.create(ShopResources.class);


    @Source("shop.json")
    public TextResource shopJson();


    @Source("shop.xml")
    public TextResource shopXml();
}
