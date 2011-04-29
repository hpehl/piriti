package name.pehl.piriti.client.simplejsonpath;

import name.pehl.piriti.commons.client.Path;
import name.pehl.piriti.json.client.JsonReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
// @formatter:off
public class Person
{
    interface PersonReader extends JsonReader<Person> {}
    public static final PersonReader READER = GWT.create(PersonReader.class);
    
    String name;
    @Path("@.address.street") String street;
    @Path("@.address.city") String city;
}
