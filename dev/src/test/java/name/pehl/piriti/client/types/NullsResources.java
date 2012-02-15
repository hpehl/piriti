package name.pehl.piriti.client.types;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 1458 $
 */
public interface NullsResources extends ClientBundle
{
    NullsResources INSTANCE = GWT.create(NullsResources.class);


    @Source("nulls.json")
    public TextResource nullsJson();


    @Source("nullsCompact.json")
    public TextResource nullsCompactJson();


    @Source("nulls.xml")
    public TextResource nullsXml();
}
