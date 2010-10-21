package name.pehl.piriti.client.gwttest.animal;

import name.pehl.piriti.client.json.Json;
import name.pehl.piriti.client.xml.Xml;

/**
 * @author $Author$
 * @version $Date$ $Revision$
 */
public abstract class Mammal extends Animal
{
    @Json
    @Xml
    Gender gender;
}
