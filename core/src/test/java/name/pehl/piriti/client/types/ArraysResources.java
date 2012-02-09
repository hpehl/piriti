package name.pehl.piriti.client.types;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 1458 $
 */
public interface ArraysResources extends ClientBundle
{
    ArraysResources INSTANCE = GWT.create(ArraysResources.class);


    @Source("arrays.json")
    public TextResource arraysJson();


    @Source("arraysCompact.json")
    public TextResource arraysCompactJson();


    @Source("arrays.xml")
    public TextResource arraysXml();


    @Source("arraysCompact.xml")
    public TextResource arraysCompactXml();
}
