package name.pehl.piriti.gxt.client.gwttest.animal;

import name.pehl.piriti.client.gwttest.animal.Gender;
import name.pehl.piriti.gxt.client.json.JsonField;
import name.pehl.piriti.gxt.client.json.JsonModel;
import name.pehl.piriti.gxt.client.xml.XmlField;
import name.pehl.piriti.gxt.client.xml.XmlModel;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
@JsonModel(@JsonField(property = "gender", type = Gender.class))
@XmlModel(@XmlField(property = "gender", type = Gender.class))
public abstract class Mammal extends Animal
{
}
