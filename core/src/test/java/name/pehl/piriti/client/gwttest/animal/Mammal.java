package name.pehl.piriti.client.gwttest.animal;

import name.pehl.piriti.client.json.JsonField;
import name.pehl.piriti.client.xml.XmlField;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public abstract class Mammal extends Animal
{
    @JsonField
    @XmlField
    Gender gender;
}
