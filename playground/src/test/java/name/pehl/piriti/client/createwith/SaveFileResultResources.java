package name.pehl.piriti.client.createwith;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 1478 $
 */
public interface SaveFileResultResources extends ClientBundle
{
    SaveFileResultResources INSTANCE = GWT.create(SaveFileResultResources.class);


    @Source("savefileresult.json")
    public TextResource saveFileResultJson();
}
