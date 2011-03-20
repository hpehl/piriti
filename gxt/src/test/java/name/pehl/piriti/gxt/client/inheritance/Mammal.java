package name.pehl.piriti.gxt.client.inheritance;

import name.pehl.piriti.client.inheritance.Gender;
import name.pehl.piriti.gxt.json.client.Json;
import name.pehl.piriti.gxt.json.client.JsonMappings;
import name.pehl.piriti.gxt.xml.client.Xml;
import name.pehl.piriti.gxt.xml.client.XmlMappings;

/**
 * @author $Author$
 * @version $Date$ $Revision$
 */
@JsonMappings(@Json(property = "gender", type = Gender.class))
@XmlMappings(@Xml(property = "gender", type = Gender.class))
public abstract class Mammal extends Animal
{
}
