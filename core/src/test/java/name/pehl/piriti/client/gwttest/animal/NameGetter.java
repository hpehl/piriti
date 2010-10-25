package name.pehl.piriti.client.gwttest.animal;

import static name.pehl.piriti.client.gwttest.animal.AbstractAnimalTest.*;
import name.pehl.piriti.client.property.PropertyGetter;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public class NameGetter implements PropertyGetter<Animal, String>
{
    @Override
    public String get(Animal model)
    {
        return model.name.substring(NAME_PREFIX.length());
    }
}
