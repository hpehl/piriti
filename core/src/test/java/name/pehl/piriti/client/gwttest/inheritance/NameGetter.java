package name.pehl.piriti.client.gwttest.inheritance;

import static name.pehl.piriti.client.gwttest.inheritance.AbstractAnimalTest.*;
import name.pehl.piriti.client.property.PropertyGetter;

/**
 * @author $Author$
 * @version $Date$ $Revision$
 */
public class NameGetter implements PropertyGetter<Animal, String>
{
    @Override
    public String get(Animal model)
    {
        return model.name.substring(NAME_PREFIX.length());
    }
}
