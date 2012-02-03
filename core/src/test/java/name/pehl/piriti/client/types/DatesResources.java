package name.pehl.piriti.client.types;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 1458 $
 */
public interface DatesResources extends ClientBundle
{
    DatesResources INSTANCE = GWT.create(DatesResources.class);


    @Source("dates.json")
    public TextResource datesJson();


    @Source("emptyDates.json")
    public TextResource emptyDatesJson();


    @Source("dates.xml")
    public TextResource datesXml();
}
