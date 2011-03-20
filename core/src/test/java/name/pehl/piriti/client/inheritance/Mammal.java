package name.pehl.piriti.client.inheritance;

import name.pehl.piriti.json.client.Json;
import name.pehl.piriti.xml.client.Xml;

/**
 * @author $Author$
 * @version $Date$ $Revision$
 */
// @formatter:off
public abstract class Mammal extends Animal
{
    @Json @Xml Gender gender;
}
