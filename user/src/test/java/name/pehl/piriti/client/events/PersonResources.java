package name.pehl.piriti.client.events;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 1454 $
 */
public interface PersonResources extends ClientBundle
{
    // -------------------------------------------------------------- constants

    int COUNT = 3;

    // ------------------------------------------------------- deferred binding

    PersonResources INSTANCE = GWT.create(PersonResources.class);


    @Source("persons.json")
    public TextResource personsJson();


    @Source("persons.xml")
    public TextResource personsXml();
}
