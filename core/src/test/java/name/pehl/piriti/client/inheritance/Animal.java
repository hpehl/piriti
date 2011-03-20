package name.pehl.piriti.client.inheritance;

import name.pehl.piriti.json.client.Json;
import name.pehl.piriti.xml.client.Xml;

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
