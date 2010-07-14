package name.pehl.piriti.client.gwttest.animal;

import name.pehl.piriti.client.json.JsonField;
import name.pehl.piriti.client.xml.XmlField;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public abstract class Animal
{
    @JsonField
    @XmlField
    String name;

    @JsonField
    @XmlField
    int legs;

    @JsonField
    @XmlField
    double intelligence;
}
