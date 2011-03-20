package name.pehl.piriti.client.inheritance;

import static name.pehl.piriti.client.inheritance.AbstractAnimalTest.*;
import name.pehl.piriti.property.client.PropertySetter;

/**
 * @author $Author$
 * @version $Date$ $Revision$
 */
public class NameSetter implements PropertySetter<Animal, String>
{
    @Override
    public void set(Animal model, String value)
    {
        model.name = NAME_PREFIX + value;
    }
}
