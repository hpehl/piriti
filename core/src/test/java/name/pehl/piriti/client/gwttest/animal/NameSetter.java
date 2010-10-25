package name.pehl.piriti.client.gwttest.animal;

import static name.pehl.piriti.client.gwttest.animal.AbstractAnimalTest.*;
import name.pehl.piriti.client.property.PropertySetter;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public class NameSetter implements PropertySetter<Animal, String>
{
    @Override
    public void set(Animal model, String value)
    {
        model.name = NAME_PREFIX + value;
    }
}
