package name.pehl.piriti.client.native_;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */

public interface ResponseResources extends ClientBundle
{
    ResponseResources INSTANCE = GWT.create(ResponseResources.class);


    @Source("response.json")
    TextResource responseJson();


    @Source("response.xml")
    TextResource responseXml();
}
