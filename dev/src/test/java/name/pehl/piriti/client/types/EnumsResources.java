package name.pehl.piriti.client.types;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 1458 $
 */
public interface EnumsResources extends ClientBundle
{
    EnumsResources INSTANCE = GWT.create(EnumsResources.class);


    @Source("enums.json")
    public TextResource enumsJson();


    @Source("enums.xml")
    public TextResource enumsXml();
}
