package name.pehl.piriti.gxt.client.gwttest.animal;

import name.pehl.piriti.client.gwttest.animal.Gender;
import name.pehl.piriti.gxt.client.json.Json;
import name.pehl.piriti.gxt.client.json.JsonMappings;
import name.pehl.piriti.gxt.client.xml.Xml;
import name.pehl.piriti.gxt.client.xml.XmlMappings;

/**
 * @author $Author$
 * @version $Date$ $Revision$
 */
@JsonMappings(@Json(property = "gender", type = Gender.class))
@XmlMappings(@Xml(property = "gender", type = Gender.class))
public abstract class Mammal extends Animal
{
}
