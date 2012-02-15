package name.pehl.piriti.client.types;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 1458 $
 */
public interface AnythingResources extends ClientBundle
{
    AnythingResources INSTANCE = GWT.create(AnythingResources.class);


    @Source("anything.json")
    public TextResource anythingJson();


    @Source("anything.xml")
    public TextResource anythingXml();
}
