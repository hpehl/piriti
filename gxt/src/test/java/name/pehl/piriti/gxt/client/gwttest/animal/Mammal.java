package name.pehl.piriti.gxt.client.gwttest.animal;

import name.pehl.piriti.client.gwttest.animal.Gender;
import name.pehl.piriti.gxt.client.json.JsonField;
import name.pehl.piriti.gxt.client.json.JsonFields;
import name.pehl.piriti.gxt.client.xml.XmlField;
import name.pehl.piriti.gxt.client.xml.XmlFields;

/**
 * @author $Author$
 * @version $Date$ $Revision$
 */
@JsonFields(@JsonField(name = "gender", type = Gender.class))
@XmlFields(@XmlField(name = "gender", type = Gender.class))
public abstract class Mammal extends Animal
{
}
