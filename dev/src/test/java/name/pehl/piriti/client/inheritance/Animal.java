package name.pehl.piriti.client.inheritance;

import name.pehl.piriti.commons.client.Order;
import name.pehl.piriti.property.client.Getter;
import name.pehl.piriti.property.client.Setter;

/**
 * @author $Author$
 * @version $Date$ $Revision:
 *          1478 $
 */
public abstract class Animal
{
    @Order(3) @Getter(NameGetter.class) @Setter(NameSetter.class) String name;
    @Order(2) int legs;
    @Order(1) double intelligence;
}
