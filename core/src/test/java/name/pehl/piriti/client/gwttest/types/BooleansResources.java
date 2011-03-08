package name.pehl.piriti.client.gwttest.types;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 1458 $
 */
public interface BooleansResources extends ClientBundle
{
    BooleansResources INSTANCE = GWT.create(BooleansResources.class);


    @Source("booleans.json")
    public TextResource booleansJson();


    @Source("booleans.xml")
    public TextResource booleansXml();
}
