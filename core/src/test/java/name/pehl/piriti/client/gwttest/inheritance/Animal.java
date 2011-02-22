package name.pehl.piriti.client.gwttest.inheritance;

import name.pehl.piriti.client.json.Json;
import name.pehl.piriti.client.xml.Xml;

/**
 * @author $Author$
 * @version $Date$ $Revision$
 */
// @formatter:off
public abstract class Animal
{
    @Json(order = 3, getter = NameGetter.class, setter = NameSetter.class) 
    @Xml(order = 3, getter = NameGetter.class, setter = NameSetter.class)
    String name;
    
    @Json(order = 2) @Xml(order = 2) 
    int legs;
    
    @Json(order = 1) @Xml(order = 1) 
    double intelligence;
}
