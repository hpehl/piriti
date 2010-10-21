package name.pehl.piriti.client.gwttest.animal;

import name.pehl.piriti.client.json.Json;
import name.pehl.piriti.client.xml.Xml;

/**
 * @author $Author$
 * @version $Date$ $Revision$
 */
public abstract class Animal
{
    @Json
    @Xml
    String name;

    @Json
    @Xml
    int legs;

    @Json
    @Xml
    double intelligence;
}
